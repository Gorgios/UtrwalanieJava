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
import java.util.List;

import static org.junit.Assert.fail;

public class JumperServiceTest {

    TeamService teamService = null;
    JumperService jumperService = null;

    @Before
    public void startDB() throws SQLException, ClassNotFoundException {
        DatabaseOperations.getConnection();
        DatabaseOperations.executeUpdate(DatabaseOperations.getPrepraredStatement("DROP TABLE IF EXISTS jumper;"));
        DatabaseOperations.executeUpdate(DatabaseOperations.getPrepraredStatement("DROP TABLE IF EXISTS team"));


        teamService = new TeamService();
        jumperService = new JumperService();

        Team team1 = new Team(1,"Poland","Lotos",20.2);
        Team  team2 = new Team (2,"Japan","Samurajska firma",12.9);
        Team  team3 = new Team(3,"Germany","Milka",94.3);

        teamService.create(team1);
        teamService.create(team2);
        teamService.create(team3);

        Jumper jumper1 = new Jumper(1,"Kamil","Stoch", Date.valueOf("1987-03-02"),251.5,25,1);
        Jumper jumper2 = new Jumper(2,"Stefan","Hula", Date.valueOf("1983-01-02"),221.5,2,1);
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
    public void testCreateValidJumper() throws SQLException {
        Jumper jumper = new Jumper(5,"Adam","Malysz",Date.valueOf("1977-03-29"),230.5,39,1);
        jumperService.create(jumper);
        Jumper jumper2 = jumperService.read_jumper(5);
        Assert.assertEquals(jumper, jumper2);
    }
    @Test(expected = NullPointerException.class)
    public void testCreateNonValidJumper() throws SQLException {
        Jumper jumper = null;
        fail(String.valueOf( jumperService.create(jumper)));
    }
    @Test(expected = AssertionError.class)
    public void testCreateJumperIdExists() throws SQLException {
        Jumper jumper = new Jumper(3,"Adam","Malysz",Date.valueOf("1977-03-29"),230.5,39,1);
        fail(String.valueOf(jumperService.create(jumper)));
    }
    @Test
    public void testUpdateSuccesfullyJumper() throws SQLException {
        Jumper jumper = jumperService.read_jumper(3);
        jumper.setCarrer_wins(100);
        jumperService.update(jumper);
        Jumper jumper2 = jumperService.read_jumper(3);
        Assert.assertEquals(jumper,jumper2);
    }
    @Test
    public void testDeleteJumper() throws SQLException{
        jumperService.delete(3);
        Jumper jumper = jumperService.read_jumper(3);
        Assert.assertNull(jumper);
    }
    @Test
    public void testReadAllJumpers() throws SQLException{
        List<Jumper> jumpers = jumperService.read_all_jumpers();
        Assert.assertEquals(jumpers.size(),4);
        Assert.assertNotNull(jumpers.get(0));
    }
    @Test
    public void testSortJumpersByPersonalBest() throws  SQLException{
        boolean isOk=true;
        List<Jumper> jumpers = jumperService.show_by_personal_best();
        for (int i=0; i<jumpers.size()-1; i++){
            if (jumpers.get(i).getPersonal_best() < jumpers.get(i+1).getPersonal_best())
                isOk=false;
        }
        Assert.assertTrue(isOk);
    }
    @Test
    public void testReadOldestJumper() throws SQLException{
        Jumper jumper = jumperService.read_oldest_jumper();
        Jumper jumper2 = jumperService.read_jumper(3);
        Assert.assertEquals(jumper,jumper2);
    }
    @Test
    public void testMostTitledJumper() throws SQLException{
        Jumper jumper = jumperService.most_titled_jumper();
        Jumper jumper2 = jumperService.read_jumper(1);
        Assert.assertEquals(jumper,jumper2);
    }
    @Test
    public void testJumperAge() throws  SQLException{
        int age = jumperService.getJumperAge(1);
        Assert.assertEquals(age,32);
    }
    @Test
    public void testDeleteBadId() throws  SQLException{
        Assert.assertEquals(0,jumperService.delete(13));
    }
    @Test
    public void testTeamWins() throws  SQLException{
        Assert.assertEquals(27,jumperService.getTeamWins(1));
    }

}
