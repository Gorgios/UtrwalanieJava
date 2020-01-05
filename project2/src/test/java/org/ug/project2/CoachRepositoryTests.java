package org.ug.project2;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ug.project2.model.Coach;
import org.ug.project2.repository.CoachRepository;
import org.ug.project2.repository.JumperRepository;
import org.ug.project2.repository.TeamRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class CoachRepositoryTests {

    private CoachRepository coachRepository;
    private JumperRepository jumperRepository;
    private TeamRepository teamRepository;

    @Autowired
    public CoachRepositoryTests(CoachRepository coachRepository, JumperRepository jumperRepository, TeamRepository teamRepository) {
        this.coachRepository = coachRepository;
        this.jumperRepository = jumperRepository;
        this.teamRepository = teamRepository;
    }
    @Test
    void contextLoads() {

    }
    @Test
    public void testAddCoach(){
        Coach coach = new Coach("Simon","Ammann","Physic Coach");
        Assert.assertEquals(coachRepository.findAll().size(),0);
        coachRepository.save(coach);
        Assert.assertEquals(coachRepository.findAll().size(),1);
    }
    @Test
    public void testUpdateCoach(){
        Coach coach = new Coach("Simon","Ammann","Physic Coach");
        coachRepository.save(coach);
        Assert.assertEquals(coachRepository.findById(coach.getId()).getFirstName(),"Simon");
        Coach coach2 = coachRepository.findById(coach.getId());
        coach2.setFirstName("Harry");
        coachRepository.save(coach);
        Assert.assertNotEquals(coachRepository.findById(coach.getId()).getFirstName(),"Simon");
    }
    @Test
    public void testDeleteCoach(){
        Coach coach = new Coach("Simon","Ammann","Physic Coach");
        coachRepository.save(coach);
        Assert.assertNotNull(coachRepository.findById(coach.getId()));
        coachRepository.deleteById(coach.getId());
        Assert.assertNull(coachRepository.findById(coach.getId()));
    }

}
