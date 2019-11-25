package service;

import dbop.DatabaseOperations;
import domain.Jumper;
import domain.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamService {
    private final String CREATE_TEAM_TABLE = "CREATE TABLE team (\n" +
            "   id INT NOT NULL,\n" +
            "   name VARCHAR(50) NOT NULL,\n" +
            "   sponsor VARCHAR(50) NOT NULL,\n" +
            "   budget float NOT NULL, \n" +
            "   PRIMARY KEY (id) \n" +
            ");";

    private final String DROP_TABLE = "DROP TABLE IF EXISTS team;";

    private final String CREATE_TEAM = "INSERT INTO team (id,name,sponsor,budget) VALUES (?,?,?,?);";

    private final String UPDATE_TEAM = "UPDATE team SET name=(?), sponsor=(?), budget=(?) WHERE id=(?); ";

    private final String DELETE_TEAM = "DELETE FROM team WHERE id = (?); ";

    private final String READ_TEAM = "SELECT * FROM team WHERE id = (?);";

    private final String READ_ALL_TEAMS = "SELECT * FROM team; ";

    private final String READ_RICHEST_TEAM = "SELECT * FROM team ORDER BY budget DESC limit 1 ;";

    private final String COUNT_JUMPERS = "SELECT COUNT(*) FROM jumper WHERE team_id = (?); ";

    private final String LIST_JUMPERS = "SELECT * FROM jumper where team_id = (?);";

    PreparedStatement preparedStatement = null;

    public TeamService() throws SQLException {
        try{
            DatabaseOperations.connection.setAutoCommit(false);
            preparedStatement = DatabaseOperations.getPrepraredStatement(CREATE_TEAM_TABLE);
            DatabaseOperations.executeUpdate(preparedStatement);
            DatabaseOperations.connection.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("DB problem");
            DatabaseOperations.connection.rollback();
        }

    }

    public int create(Team team) throws SQLException{
        int r=0;
        try {
            preparedStatement = DatabaseOperations.getPrepraredStatement(CREATE_TEAM);
            preparedStatement.setInt(1, team.getId());
            preparedStatement.setString(2, team.getName());
            preparedStatement.setString(3, team.getSponsor());
            preparedStatement.setDouble(4, team.getBudget());
      //      System.out.println(preparedStatement);
            r = DatabaseOperations.executeUpdate(preparedStatement);
            DatabaseOperations.connection.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("DB problem");
            DatabaseOperations.connection.rollback();
        }
        return r;
    }
    public int delete(int id) throws SQLException{
        int r=0;
        try{
            preparedStatement = DatabaseOperations.getPrepraredStatement(DELETE_TEAM);
            preparedStatement.setInt(1,id);
            r = DatabaseOperations.executeUpdate(preparedStatement);
            DatabaseOperations.connection.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("DB problem");
            DatabaseOperations.connection.rollback();
        }
        return r;
    }
    public int update(Team team) throws SQLException{
        int r=0;
        try {

            preparedStatement = DatabaseOperations.getPrepraredStatement(UPDATE_TEAM);
            preparedStatement.setString(1, team.getName());
            preparedStatement.setString(2, team.getSponsor());
            preparedStatement.setDouble(3, team.getBudget());
            preparedStatement.setInt(4, team.getId());
           // System.out.println(preparedStatement);
            r = DatabaseOperations.executeUpdate(preparedStatement);
            DatabaseOperations.connection.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("DB problem");
            DatabaseOperations.connection.rollback();
        }
        return r;
    }
    public Team read_team(int id) throws SQLException{
        try{
            Team team = new Team() ;
            preparedStatement = DatabaseOperations.getPrepraredStatement(READ_TEAM);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            DatabaseOperations.connection.commit();

            if ( !rs.next()) {
                System.out.println("Team with id = " + id + " not exists");
                return null;
            }
            do{
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
                team.setSponsor(rs.getString("sponsor"));
                team.setBudget(rs.getDouble("budget"));
            }while (rs.next());
            return team;
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return null;
    }
    public List<Team> read_all_teams() throws SQLException{
        List<Team> teams = new ArrayList<Team>();
        try{
            ResultSet rs = DatabaseOperations.executeQuery(READ_ALL_TEAMS);
            DatabaseOperations.connection.commit();

            if ( !rs.next() ) {
                System.out.println("No teams yet");
                return null;
            }

            do{
                teams.add(new Team(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("sponsor"),
                rs.getDouble("budget")));
            }while (rs.next());
            return teams;
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return null;
    }
    public Team read_richest_team() throws SQLException{
        Team team = new Team();
        try{
            ResultSet rs = DatabaseOperations.executeQuery(READ_RICHEST_TEAM);
            DatabaseOperations.connection.commit();

            if ( !rs.next() ) {
                System.out.println("Teams not exists");
                return null;
            }

            do{
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
                team.setSponsor(rs.getString("sponsor"));
                team.setBudget(rs.getDouble("budget"));
            }while (rs.next());
            return team;
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return null;
    }

    public int count_jumpers_of_team(int id) throws SQLException{
        int numberOfJumpers = 0;
        try{
            preparedStatement = DatabaseOperations.getPrepraredStatement(COUNT_JUMPERS);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            DatabaseOperations.connection.commit();

            while (rs.next())
                numberOfJumpers=rs.getInt(1);
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return numberOfJumpers;
    }
    public List<Jumper> jumpers_in_team(int id) throws SQLException{
        List<Jumper> jumpers = new ArrayList<Jumper>();

        try{
            preparedStatement = DatabaseOperations.getPrepraredStatement(LIST_JUMPERS);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            DatabaseOperations.connection.commit();

            if ( !rs.next() ) {
                System.out.println("We have not jumpers yet");
                return null;
            }

            do{
                jumpers.add(new Jumper(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDate("date_of_birth"),
                        rs.getFloat("personal_best"),
                        rs.getInt("carrer_wins"),
                        rs.getInt("team_id")));
            }while (rs.next());
            return jumpers;
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Cannot read jumper for some reason");
            DatabaseOperations.connection.rollback();
        }
        return null;
    }

}
