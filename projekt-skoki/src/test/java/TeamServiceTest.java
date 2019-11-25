import dbop.DatabaseOperations;
import domain.Jumper;
import domain.Team;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.JumperService;
import service.TeamService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class TeamServiceTest {

    TeamService teamService = null;
    JumperService jumperService = null;

    @Before
    public void startDB() throws SQLException, ClassNotFoundException {
        DatabaseOperations.getConnection();
        DatabaseOperations.executeUpdate(DatabaseOperations.getPrepraredStatement("DROP TABLE IF EXISTS jumper;"));
        DatabaseOperations.executeUpdate(DatabaseOperations.getPrepraredStatement("DROP TABLE IF EXISTS team"));


        teamService = new TeamService();
        jumperService = new JumperService();

        Team  team1 = new Team(1,"Poland","Lotos",20.2);
        Team  team2 = new Team (2,"Japan","Samurajska firma",12.9);
        Team  team3 = new Team(3,"Germany","Milka",94.3);

        teamService.create(team1);
        teamService.create(team2);
        teamService.create(team3);

        Jumper jumper1 = new Jumper(1,"Kamil","Stoch", Date.valueOf("1987-03-02"),251.5,25,1);
        Jumper jumper2 = new Jumper(2,"Stefan","Hula", Date.valueOf("1983-01-02"),221.5,0,1);
        Jumper jumper3 = new Jumper(3,"Noriaki","Kasai", Date.valueOf("1972-09-15"),241,24,2);
        Jumper jumper4 = new Jumper(4,"Sven","Hannawald", Date.valueOf("1974-03-11"),224.5,18,3);

        jumperService.create(jumper1);
        jumperService.create(jumper2);
        jumperService.create(jumper3);
        jumperService.create(jumper4);


    }

    @After
    public void closeDB() throws SQLException {
        DatabaseOperations.closeConnection();
    }

    @Test
    public void testCreateValidTeam() throws SQLException {
        Team team = new Team(4,"Norway","Becks",57.32);
        teamService.create(team);
        Team team2 = teamService.read_team(4);
        Assert.assertEquals(team, team2);
    }
    @Test(expected = NullPointerException.class)
    public void testCreateNonValidTeam() throws SQLException {
        Team team = null;
        fail(String.valueOf(      teamService.create(team)));
    }
    @Test(expected = AssertionError.class)
    public void testCreateTeamIdExists() throws SQLException {
        Team team = new Team(3,"Norway","Becks",57.32);
        fail(String.valueOf(teamService.create(team)));
    }
    @Test
    public void testUpdateSuccesfullyTeam() throws SQLException {
        Team team = teamService.read_team(3);
        team.setSponsor("Nowy sponsor");
        teamService.update(team);
        Team team2 = teamService.read_team(3);
        Assert.assertEquals(team, team2);
    }
    @Test
    public void testDeleteTeam() throws SQLException{
        teamService.delete(3);
        Team team = teamService.read_team(3);
        Assert.assertNull(team);
    }
    @Test
    public void testReadAllTeams() throws SQLException{
        List<Team> teams =teamService.read_all_teams();
        Assert.assertEquals(teams.size(),3);
        Assert.assertNotNull(teams.get(0));
    }
    @Test
    public void testSuccesfullReadRichestTeam() throws  SQLException{
        Team team =teamService.read_richest_team();
        Assert.assertEquals(3,team.getId());
    }
    @Test
    public void testSuccesfullCountJumpers() throws SQLException{
        Assert.assertEquals(teamService.count_jumpers_of_team(1),2);
    }
    @Test
    public void testFailReadTeamFromId() throws SQLException{
        Assert.assertNull(teamService.read_team(10));
    }
    @Test
    public void testSuccessReadTeamFromId() throws SQLException{
        Assert.assertEquals(teamService.read_team(1).getName(),"Poland");
    }
    @Test
    public void testSuccessReadJumpersFromTeam() throws SQLException{
        List <Jumper> jumpers = teamService.jumpers_in_team(1);
        boolean check = jumpers.get(0).getName().equals("Kamil") && jumpers.get(1).getName().equals("Stefan");
        Assert.assertTrue(check);
    }




}
