package service;

public class JumperService {


    private final String CREATE_JUMPER_TABLE = "CREATE TABLE jumper (\n" +
            "   id INT IDENTITY NOT NULL,\n" +
            "   name VARCHAR(50) NOT NULL,\n" +
            "   surname VARCHAR(50) NOT NULL,\n" +
            "   date_of_birth DATE NOT NULL,\n" +
            "   personal_best DECIMAL , \n" +
            "   carrer_wins INT DEFAULT 0 NOT NULL , \n" +
            "   team_id INT ,\n" +
            "   FOREIGN KEY (team_id) REFERENCES team(id) ON DELETE CASCADE, \n" +
            "   PRIMARY KEY (id) \n" +
            ");";

    private  final String DROP_TABLE = "DROP TABLE IF EXISTS jumper;";

    private  final String CREATE_JUMPER = "INSERT INTO jumper (name,surname,date_of_birth,personal_best,carrer_wins,team_id) VALUES (?,?,?,?,?,?);";

    private  final String DELETE_JUMPER = "DELETE FROM jumper where id=(?)";

    private  final String UPDATE_JUMPER = "UPDATE jumper SET personal_best=(?) carrer_wins=(?) team_id=(?) WHERE id=(?)";



}
