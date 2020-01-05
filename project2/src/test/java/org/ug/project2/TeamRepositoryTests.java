package org.ug.project2;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ug.project2.model.Coach;
import org.ug.project2.model.Jumper;
import org.ug.project2.model.Team;
import org.ug.project2.repository.CoachRepository;
import org.ug.project2.repository.JumperRepository;
import org.ug.project2.repository.TeamRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class TeamRepositoryTests {

    private CoachRepository coachRepository;
    private JumperRepository jumperRepository;
    private TeamRepository teamRepository;

    @Autowired
    public TeamRepositoryTests(CoachRepository coachRepository, JumperRepository jumperRepository, TeamRepository teamRepository) {
        this.coachRepository = coachRepository;
        this.jumperRepository = jumperRepository;
        this.teamRepository = teamRepository;
    }

    @Test
    void contextLoads() {

    }
    @Test
    void testCountJumpersWithSelectedPersonalBestInTeam(){
        Team team1 = new Team("Poland");
        teamRepository.save(team1);
        Jumper jumper = new Jumper("Kamil", "Stoch", 251.5, (short) 32);
        Jumper jumper2 = new Jumper("Maciej", "Stoch", 221.5, (short) 3);
        Jumper jumper3 = new Jumper("Wladek", "Stoch", 231.5, (short) 10);
        jumper.setTeam(team1);
        jumper2.setTeam(team1);
        jumper3.setTeam(team1);
        jumperRepository.save(jumper);
        jumperRepository.save(jumper2);
        jumperRepository.save(jumper3);
        Assert.assertEquals(2, (int) teamRepository.countJumpersWithSelectedPersonalBestInTeam(230.0, team1.getId()));
    }
    @Test
    void testFindAllJumpersAndCoachesFromTeam(){
        Team team1 = new Team("Poland");
        teamRepository.save(team1);
        Jumper jumper = new Jumper("Kamil", "Stoch", 251.5, (short) 32);
        Jumper jumper2 = new Jumper("Maciej", "Stoch", 221.5, (short) 3);
        Jumper jumper3 = new Jumper("Wladek", "Stoch", 231.5, (short) 10);
        jumper.setTeam(team1);
        jumper2.setTeam(team1);
        jumper3.setTeam(team1);
        jumperRepository.save(jumper);
        jumperRepository.save(jumper2);
        jumperRepository.save(jumper3);
        Coach coach  = new Coach("Simon","Ammann","Head Coach");
        coach.setTeam(team1);
        coachRepository.save(coach);
        Assert.assertEquals(coachRepository.findAllCoachesFromTeam(team1.getId()).size(),1);
        Assert.assertEquals(jumperRepository.findAllJumpersFromTeam(team1.getId()).size(),3);

    }
    @Test
    void testDeleteTeam(){
        Team team1 = new Team("Israel");
        teamRepository.save(team1);
        teamRepository.deleteById(team1.getId());
        Assert.assertNull(teamRepository.findById(team1.getId()));
    }
    @Test
    void testUpdateTeam(){
        Team team1 = new Team("Poland");
        teamRepository.save(team1);
        team1.setName("Poland new Version");
        Assert.assertNotEquals(teamRepository.findById(team1.getId()).getName(),"Poland");
    }
    @Test
    void testSaveTeam(){
        Assert.assertEquals(teamRepository.findAll().size(),0);
        Team team1 = new Team("Poland");
        teamRepository.save(team1);
        Assert.assertEquals(1, teamRepository.findAll().size());
    }

}
