package org.ug.project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ug.project2.model.Coach;
import org.ug.project2.model.Jumper;
import org.ug.project2.model.Team;
import org.ug.project2.repository.CoachRepository;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {
    private CoachRepository coachRepository;

    @Autowired
    public CoachServiceImpl(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }
    @Override
    public void addAll(List<Coach> coaches){
        for (Coach c : coaches)
            coachRepository.save(c);
    }
    @Override
    public void add(Coach coach){
        coachRepository.save(coach);
    }
    @Override
    public void update(Coach coach, Integer id){
        Coach coachUp = coachRepository.findById(id);
        coachUp.setFirstName(coach.getFirstName());
        coachUp.setLastName(coach.getLastName());
        coachUp.setTeam(coach.getTeam());
        coachUp.setPostion(coach.getPostion());
        coachRepository.save(coachUp);
    }
    @Override
    public void delete(Integer id){
        coachRepository.deleteById(id);
    }
    @Override
    public List<Coach> findAll(){
        return coachRepository.findAll();
    }
    @Override
    public Coach findById(Integer id){
        return coachRepository.findById(id);
    }
    @Override
    public List<Coach> findAllByTeam(Team team){
        Integer id = team.getId();
        return coachRepository.findAllCoachesFromTeam(id);
    }
    @Override
    public List<Coach> findAllJumperCoaches(Jumper jumper){
        Integer id = jumper.getId();
        return coachRepository.findAllJumprChoaches(id);
    }
    @Override
    public List<Coach> findAllByFristName(String firstName){
        return coachRepository.findAllByFirstName(firstName);
    }
}
