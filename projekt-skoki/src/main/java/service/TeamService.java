package service;

public class TeamService {
    private final String CREATE_TEAM_TABLE = "CREATE TABLE team (\n" +
            "   id INT IDENTITY NOT NULL,\n" +
            "   name VARCHAR(50) NOT NULL,\n" +
            "   sponsor VARCHAR(50) NOT NULL,\n" +
            "   budget DECIMAL NOT NULL, \n" +
            "   PRIMARY KEY (id) \n" +
            ");";

}
