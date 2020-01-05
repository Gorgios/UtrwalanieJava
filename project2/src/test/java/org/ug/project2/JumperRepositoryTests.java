package org.ug.project2;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ug.project2.model.Jumper;
import org.ug.project2.model.Team;
import org.ug.project2.repository.CoachRepository;
import org.ug.project2.repository.JumperRepository;
import org.ug.project2.repository.TeamRepository;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
class JumperRepositoryTests {
    private CoachRepository coachRepository;
    private JumperRepository jumperRepository;
    private TeamRepository teamRepository;

    @Autowired
    public JumperRepositoryTests(CoachRepository coachRepository, JumperRepository jumperRepository, TeamRepository teamRepository) {
        this.coachRepository = coachRepository;
        this.jumperRepository = jumperRepository;
        this.teamRepository = teamRepository;
    }

    @Test
    void contextLoads() {

    }
    @Test
	void testSaveJumper(){
        Jumper jumper = new Jumper("Kamil", "Stoch", 251.5, (short) 32);
        jumperRepository.save(jumper);
        Assert.assertEquals(jumperRepository.findById(jumper.getId()),jumper);
	}
	@Test
    void testUpdateJumper(){
        Jumper jumper = new Jumper("Kamil", "Stoch", 251.5, (short) 32);
        jumperRepository.save(jumper);
        int id = jumper.getId();
        Jumper newJumper = jumperRepository.findById(id);
        newJumper.setFirstName("Maciej");
        jumperRepository.save(newJumper);
        Assert.assertEquals(jumperRepository.findById(id).getFirstName(),"Maciej");
        Assert.assertEquals(jumperRepository.findById(id).getLastName(),"Stoch");
    }
	@Test
    void testDeleteJumper(){
        Jumper jumper = new Jumper("Kamil", "Stoch", 251.5, (short) 32);
        jumperRepository.save(jumper);
        int id = jumper.getId();
        Assert.assertEquals(jumperRepository.findById(id),jumper);
        jumperRepository.deleteById(id);
        Assert.assertEquals(jumperRepository.findAll().size(),0);
    }
    @Test
    void testGetJumpersWithExpectedPersonalBestAndWins(){
        Jumper jumper = new Jumper("Kamil", "Stoch", 251.5, (short) 32);
        Jumper jumper2 = new Jumper("Maciej", "Stoch", 221.5, (short) 3);
        Jumper jumper3 = new Jumper("Wladek", "Stoch", 231.5, (short) 10);
        jumperRepository.save(jumper);
        jumperRepository.save(jumper2);
        jumperRepository.save(jumper3);
        List<Jumper> jumpers = jumperRepository.findAllJumpersWithPersonalBestGreaterThan(230.0);
        Assert.assertEquals(jumpers.size(),2);
        jumpers = jumperRepository.findAllJumpersWithMoreWinsThan(14);
        Assert.assertEquals(jumpers.size(),1);
    }
    @Test
    void testGetAllJumpersFromTeam(){
        Team team1 = new Team("Poland");
        Team team2 = new Team("Germany");
        teamRepository.save(team1);
        teamRepository.save(team2);
        Jumper jumper = new Jumper("Kamil", "Stoch", 251.5, (short) 32);
        Jumper jumper2 = new Jumper("Maciej", "Stoch", 221.5, (short) 3);
        Jumper jumper3 = new Jumper("Wladek", "Stoch", 231.5, (short) 10);
        jumper.setTeam(team1);
        jumper2.setTeam(team2);
        jumper3.setTeam(team1);
        jumperRepository.save(jumper);
        jumperRepository.save(jumper2);
        jumperRepository.save(jumper3);

        List<Jumper> list1 = jumperRepository.findAllByTeam(teamRepository.findById(team1.getId()));
        Assert.assertEquals(list1.size(),2);
        list1 = jumperRepository.findAllByTeam(teamRepository.findById(team2.getId()));
        Assert.assertEquals(list1.size(),1);

    }
    @Test
    void testFindAllWithContaingNameMinWinsAndMaxPersonalBest(){
        Jumper jumper = new Jumper("Kamil", "Stoch", 251.5, (short) 32);
        Jumper jumper2 = new Jumper("Maciej", "Stoch", 221.5, (short) 3);
        jumperRepository.save(jumper);
        jumperRepository.save(jumper2);
        List<Jumper> jumpers =
                jumperRepository.findAllByFirstNameContainingAndCarrerWinsLessThanAndPersonalBestGreaterThan("mil",(short)40,200.0);
        Assert.assertEquals(jumpers.size(),1);
    }
    @Test
    void testFindBestJumperByPersonalBest(){
        Jumper jumper = new Jumper("Kamil", "Stoch", 251.5, (short) 32);
        Jumper jumper2 = new Jumper("Maciej", "Stoch", 221.5, (short) 3);
        jumperRepository.save(jumper);
        jumperRepository.save(jumper2);
        Assert.assertEquals(jumper,jumperRepository.findTopByOrderByPersonalBestDesc());
    }

}
