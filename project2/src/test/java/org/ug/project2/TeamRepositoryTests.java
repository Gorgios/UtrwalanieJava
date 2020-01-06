package org.ug.project2;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.ug.project2.model.Coach;
import org.ug.project2.model.Jumper;
import org.ug.project2.model.Team;
import org.ug.project2.repository.CoachRepository;
import org.ug.project2.repository.JumperRepository;
import org.ug.project2.repository.TeamRepository;
import org.ug.project2.service.CoachService;
import org.ug.project2.service.JumperService;
import org.ug.project2.service.TeamService;

@RunWith(SpringRunner.class)
@ComponentScan("org.ug.project2.service")
@DataJpaTest
class TeamRepositoryTests {

    private CoachService cs;
    private JumperService js;
    private TeamService ts;

    @Autowired
    public TeamRepositoryTests(CoachService cs, JumperService js, TeamService ts) {
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
    void testCountJumpersWithSelectedPersonalBestInTeam(){
        Assert.assertEquals(2, (int) ts.countJumpersWithPersonalBest(230.0,ts.findByName("Poland")));
    }
    @Test
    void testDeleteTeam(){
        Team team1 = new Team("Israel");
        ts.add(team1);
        Assert.assertNotNull(ts.findById(team1.getId()));
        ts.delete(team1.getId());
        Assert.assertNull(ts.findById(team1.getId()));
    }
    @Test
    void testUpdateTeam(){
        Team team1 = new Team("Israel");
        ts.add(team1);
        Team team2 = new Team("Poland");
        ts.update(team2,team1.getId());
        Assert.assertEquals(ts.findById(team1.getId()).getName(),"Poland");
    }
    @Test
    void testSaveTeam(){
        Team team1 = new Team("Israel");
        ts.add(team1);
        Assert.assertNotNull(ts.findById(team1.getId()));
    }
    @Test
    void testFindteamWithCoachEmptyAndNameEnds(){
        Assert.assertEquals(ts.findAllWithoutCoachAndNameEndsBy("ny").size(),1);
    }

}
