package service;

import dbop.DatabaseOperations;
import domain.Jumper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JumperService {


    private final String CREATE_JUMPER_TABLE = "CREATE TABLE jumper (\n" +
            "   id INT NOT NULL,\n" +
            "   name VARCHAR(50) NOT NULL,\n" +
            "   surname VARCHAR(50) NOT NULL,\n" +
            "   date_of_birth DATE NOT NULL,\n" +
            "   personal_best float , \n" +
            "   carrer_wins INT DEFAULT 0 NOT NULL , \n" +
            "   team_id INT ,\n" +
            "   FOREIGN KEY (team_id) REFERENCES team(id) ON DELETE CASCADE, \n" +
            "   PRIMARY KEY (id) \n" +
            ");";

    private final String DROP_TABLE = "DROP TABLE IF EXISTS jumper;";

    private final String CREATE_JUMPER = "INSERT INTO jumper (id,name,surname,date_of_birth,personal_best,carrer_wins,team_id) VALUES (?,?,?,?,?,?,?);";

    private final String DELETE_JUMPER = "DELETE FROM jumper where id=(?);";

    private final String UPDATE_JUMPER = "UPDATE jumper SET name=(?), surname=(?),date_of_birth=(?),personal_best=(?),carrer_wins=(?),team_id=(?) WHERE id=(?);";

    private final String READ_JUMPER = "SELECT * FROM jumper where id=(?);";

    private final String READ_ALL_JUMPERS = "SELECT * FROM jumper ;";

    private final String READ_BY_PERSONAL_BEST = "SELECT * FROM jumper ORDER BY personal_best DESC;";

    private final String OLDEST_JUMPER = "SELECT * FROM jumper ORDER BY date_of_birth limit 1;";

    private final String JUMPER_WITH_MOST_WINS = "SELECT * FROM jumper ORDER BY carrer_wins DESC limit 1";

    private final String JUMPER_AGE = "SELECT DATEDIFF(YEAR,date_of_birth, CURRENT_DATE) AS AGE FROM jumper WHERE ID=(?)";

    private final String WINS_BY_TEAM = "SELECT SUM(carrer_wins) FROM jumper where team_id = (?);";

    PreparedStatement preparedStatement = null;

    public JumperService() throws SQLException {
        try {
            DatabaseOperations.connection.setAutoCommit(false);
            preparedStatement = DatabaseOperations.getPrepraredStatement(CREATE_JUMPER_TABLE);
            DatabaseOperations.executeUpdate(preparedStatement);
            DatabaseOperations.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB problem");
            DatabaseOperations.connection.rollback();
        }

    }

    public int create(Jumper jumper) throws SQLException {
        int r = 0;
        try {
            preparedStatement = DatabaseOperations.getPrepraredStatement(CREATE_JUMPER);
            preparedStatement.setInt(1, jumper.getId());
            preparedStatement.setString(2, jumper.getName());
            preparedStatement.setString(3, jumper.getSurname());
            preparedStatement.setDate(4, jumper.getDate_of_birth());
            preparedStatement.setDouble(5, jumper.getPersonal_best());
            preparedStatement.setInt(6, jumper.getCarrer_wins());
            preparedStatement.setInt(7, jumper.getTeam());
            r = DatabaseOperations.executeUpdate(preparedStatement);
            DatabaseOperations.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB problem");
            DatabaseOperations.connection.rollback();
        }
        return r;
    }

    public int delete(int id) throws SQLException {
        int r = 0;
        try {
            preparedStatement = DatabaseOperations.getPrepraredStatement(DELETE_JUMPER);
            preparedStatement.setInt(1, id);
            r = DatabaseOperations.executeUpdate(preparedStatement);
            DatabaseOperations.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB problem");
            DatabaseOperations.connection.rollback();
        }
        return r;
    }

    public int update(Jumper jumper) throws SQLException {
        int r = 0;
        try {
            preparedStatement = DatabaseOperations.getPrepraredStatement(UPDATE_JUMPER);
            preparedStatement.setString(1, jumper.getName());
            preparedStatement.setString(2, jumper.getSurname());
            preparedStatement.setDate(3, jumper.getDate_of_birth());
            preparedStatement.setDouble(4, jumper.getPersonal_best());
            preparedStatement.setInt(5, jumper.getCarrer_wins());
            preparedStatement.setInt(6, jumper.getTeam());
            preparedStatement.setInt(7, jumper.getId());
            r = DatabaseOperations.executeUpdate(preparedStatement);
            DatabaseOperations.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB problem");
            DatabaseOperations.connection.rollback();
        }
        return r;
    }

    public Jumper read_jumper(int id) throws SQLException {
        Jumper jumper = new Jumper();
        ;
        try {
            preparedStatement = DatabaseOperations.getPrepraredStatement(READ_JUMPER);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            DatabaseOperations.connection.commit();

            if (!rs.next()) {
                System.out.println("Jumper with id = " + id + " not exists");
                return null;
            }

            do {
                jumper.setId(rs.getInt("id"));
                jumper.setName(rs.getString("name"));
                jumper.setSurname(rs.getString("surname"));
                jumper.setDate_of_birth(rs.getDate("date_of_birth"));
                jumper.setPersonal_best(rs.getDouble("personal_best"));
                jumper.setCarrer_wins(rs.getInt("carrer_wins"));
                jumper.setTeam(rs.getInt("team_id"));
            } while (rs.next());
            return jumper;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return null;
    }

    public List<Jumper> read_all_jumpers() throws SQLException {
        List<Jumper> jumpers = new ArrayList<Jumper>();

        try {
            ResultSet rs = DatabaseOperations.executeQuery(READ_ALL_JUMPERS);
            DatabaseOperations.connection.commit();

            if (!rs.next()) {
                System.out.println("We have not jumpers yet");
                return null;
            }

            do {
                jumpers.add(new Jumper(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDate("date_of_birth"),
                        rs.getDouble("personal_best"),
                        rs.getInt("carrer_wins"),
                        rs.getInt("team_id")));
            } while (rs.next());
            return jumpers;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return null;
    }

    public List<Jumper> show_by_personal_best() throws SQLException {
        List<Jumper> jumpers = new ArrayList<Jumper>();

        try {
            ResultSet rs = DatabaseOperations.executeQuery(READ_BY_PERSONAL_BEST);
            DatabaseOperations.connection.commit();

            if (!rs.next()) {
                System.out.println("We have not jumpers yet");
                return null;
            }

            do {
                jumpers.add(new Jumper(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDate("date_of_birth"),
                        rs.getDouble("personal_best"),
                        rs.getInt("carrer_wins"),
                        rs.getInt("team_id")));
            } while (rs.next());
            return jumpers;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return null;
    }

    public Jumper read_oldest_jumper() throws SQLException {
        Jumper jumper = new Jumper();
        try {
            ResultSet rs = DatabaseOperations.executeQuery(OLDEST_JUMPER);
            DatabaseOperations.connection.commit();

            if (!rs.next()) {
                System.out.println("No jumpers");
                return null;
            }

            do {
                jumper.setId(rs.getInt("id"));
                jumper.setName(rs.getString("name"));
                jumper.setSurname(rs.getString("surname"));
                jumper.setDate_of_birth(rs.getDate("date_of_birth"));
                jumper.setPersonal_best(rs.getDouble("personal_best"));
                jumper.setCarrer_wins(rs.getInt("carrer_wins"));
                jumper.setTeam(rs.getInt("team_id"));
            } while (rs.next());
            return jumper;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return null;
    }

    public Jumper most_titled_jumper() throws SQLException {
        Jumper jumper = new Jumper();
        try {
            ResultSet rs = DatabaseOperations.executeQuery(JUMPER_WITH_MOST_WINS);
            DatabaseOperations.connection.commit();

            if (!rs.next()) {
                System.out.println("No jumpers");
                return null;
            }

            do {
                jumper.setId(rs.getInt("id"));
                jumper.setName(rs.getString("name"));
                jumper.setSurname(rs.getString("surname"));
                jumper.setDate_of_birth(rs.getDate("date_of_birth"));
                jumper.setPersonal_best(rs.getDouble("personal_best"));
                jumper.setCarrer_wins(rs.getInt("carrer_wins"));
                jumper.setTeam(rs.getInt("team_id"));
            } while (rs.next());
            return jumper;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return null;
    }

    public int getJumperAge(int id) throws SQLException {
        int age = 0;
        try {
            preparedStatement = DatabaseOperations.getPrepraredStatement(JUMPER_AGE);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            DatabaseOperations.connection.commit();

            if (!rs.next()) {
                System.out.println("Jumper with id = " + id + " not exists");
                return -1;
            }

            do {
                age = rs.getInt(1);
                ;
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return age;
    }

    public int getTeamWins(int id) throws SQLException {
        int wins = 0;
        try {
            preparedStatement = DatabaseOperations.getPrepraredStatement(WINS_BY_TEAM);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            DatabaseOperations.connection.commit();

            if (!rs.next()) {
                System.out.println("Team with id = " + id + " not exists");
                return -1;
            }

            do {
                wins = rs.getInt(1);
                ;
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return wins;
    }

}
