package org.ug.project2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.ug.project2.model.Coach;
import org.ug.project2.model.Jumper;
import org.ug.project2.model.Team;
import org.ug.project2.service.*;

import java.util.List;

@RunWith(SpringRunner.class)
@ComponentScan("org.ug.project2.service")
@DataJpaTest
class JumperRepositoryTests {

    private CoachService cs;
    private JumperService js;
    private TeamService ts;

    @Autowired
    public JumperRepositoryTests(CoachService cs, JumperService js, TeamService ts) {
        this.cs = cs;
        this.js = js;
        this.ts = ts;
    }

    @BeforeEach
    void setUp() {
        Team team1 = new Team("Poland");
        Team team2 = new Team("Germany");
        Jumper jumper = new Jumper("Kamil", "Stoch", 251.5, (short) 32);
        Jumper jumper2 = new Jumper("Maciej", "Stoch", 221.5, (short) 3);
        Jumper jumper3 = new Jumper("Wladek", "Stoch", 231.5, (short) 10);
        Jumper jumper4 = new Jumper ("Karl","Geiger",244.5,(short) 3);
        Coach coach = new Coach("Simon","Ammann","Physic Coach");
        Coach coach2 = new Coach("Simon","Hammann","Head Coach");
        jumper.setTeam(team1);
        jumper2.setTeam(team1);
        jumper3.setTeam(team1);
        jumper4.setTeam(team2);
        coach.setTeam(team1);
        coach2.setTeam(team1);
        js.add(jumper);
        js.add(jumper2);
        js.add(jumper3);
        js.add(jumper4);
        ts.add(team1);
        ts.add(team2);
        cs.add(coach);
        cs.add(coach2);

    }


    @Test
    void contextLoads() {

    }
    @Test
    void testOneToMany(){
        Team team1 = new Team("Japan");
        Jumper jumper = new Jumper("Adam", "Malysz", 231.5, (short) 39);
        Jumper jumper2 = new Jumper("Adamek", "Malysz", 231.5, (short) 39);
        jumper.setTeam(team1);
        jumper2.setTeam(team1);
        ts.add(team1);
        Assert.assertEquals(js.findAllByTeam(team1).size(),0);
        js.add(jumper);
        js.add(jumper2);
        Assert.assertEquals(js.findAllByTeam(team1).size(),2);
    }
    @Test
	void testSaveJumper(){
        Jumper jumper = new Jumper("Adam", "Malysz", 231.5, (short) 39);
        js.add(jumper);
        Assert.assertNotNull(js.findById(jumper.getId()));
	}
    @Test
    void testUpdateJumper(){
        Jumper jumper = new Jumper("Adam", "Malysz", 231.5, (short) 39);
        js.add(jumper);
        Assert.assertEquals(js.findById(jumper.getId()).getCarrerWins(),39);
        Jumper jumper2 = new Jumper("Adam", "Malysz", 251.5, (short) 40);
        js.update(jumper2,jumper.getId());
        Assert.assertEquals(js.findById(jumper.getId()).getCarrerWins(),40);
    }
	@Test
    void testDeleteJumper(){
        Jumper jumper = new Jumper("Kamil", "Stoch", 251.5, (short) 32);
        js.add(jumper);
        Assert.assertNotNull(js.findById(jumper.getId()));
        js.delete(jumper.getId());
        Assert.assertNull(js.findById(jumper.getId()));
    }

    @Test
    void testGetJumpersWithExpectedPersonalBestAndWins(){

        List<Jumper> jumpers = js.findAllWithPersonalBest(230.0);
        Assert.assertEquals(jumpers.size(),3);
        jumpers = js.findAllWithMoreWinsThan(14);
        Assert.assertEquals(jumpers.size(),1);
    }
    @Test
    void testGetAllJumpersFromTeam(){
        List<Jumper> jumpers = js.findAllByTeam(ts.findByName("Poland"));
        Assert.assertEquals(jumpers.size(),3);
        List<Jumper> jumpers2 = js.findAllByTeam(ts.findByName("Germany"));
        Assert.assertEquals(jumpers2.size(),1);

    }
    @Test
    void testFindAllJumpers(){
        Assert.assertEquals(js.findAll().size(),4);
    }
    @Test
    void testFindAllWithContaingNameMaxWinsAndMinPersonalBest(){
        List<Jumper> jumpers =js.findAllWithContainNameAndCWandPB("mil",(short)40,230.0);
        Assert.assertEquals(jumpers.size(),1);
    }
    @Test
    void testFindBestJumperByPersonalBest(){
        Jumper jumper = new Jumper("Jurek", "Stoch", 260.5, (short) 32);
        js.add(jumper);
        Assert.assertEquals(jumper,js.getBestJumperByPersonalBest());
    }
}
