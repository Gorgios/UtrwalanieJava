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
class CoachRepositoryTests {

    private CoachService cs;
    private JumperService js;
    private TeamService ts;

    @Autowired
    public CoachRepositoryTests(CoachService cs, JumperService js, TeamService ts) {
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
    public void testAddCoach(){
        Coach coach = new Coach("Long","Ammann","Physic Coach");
        cs.add(coach);
        Assert.assertNotNull(cs.findById(coach.getId()));
    }
    @Test
    public void testUpdateCoach(){
        Coach coach = new Coach("Long","Ammann","Physic Coach");
        cs.add(coach);
        Coach coach2 = new Coach("Long","Prick","Physic Coach");
        cs.update(coach2,coach.getId());
        Assert.assertEquals(cs.findById(coach.getId()).getLastName(),"Prick");
        Assert.assertNull(cs.findById(coach2.getId()));
    }
    @Test
    public void testDeleteCoach(){
        Coach coach = new Coach("Long","Ammann","Physic Coach");
        cs.add(coach);
        Assert.assertNotNull(cs.findById(coach.getId()));
        cs.delete(coach.getId());
        Assert.assertNull(cs.findById(coach.getId()));
    }

}
