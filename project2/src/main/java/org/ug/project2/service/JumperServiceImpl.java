package org.ug.project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ug.project2.model.Jumper;
import org.ug.project2.model.Team;
import org.ug.project2.repository.CoachRepository;
import org.ug.project2.repository.JumperRepository;
import org.ug.project2.repository.TeamRepository;

import java.util.List;

@Service
public class JumperServiceImpl implements JumperService {
    private JumperRepository jumperRepository;

    @Autowired
    public JumperServiceImpl(JumperRepository jumperRepository) {
        this.jumperRepository = jumperRepository;
    }

    @Override
    public void addAll(List<Jumper> jumpers){
        for (Jumper j : jumpers)
            jumperRepository.save(j);
    }
    @Override
    public void add(Jumper jumper){
        jumperRepository.save(jumper);
    }
    @Override
    public void update(Jumper jumper, Integer id){
        Jumper jumperUp = jumperRepository.findById(id);
        jumperUp.setFirstName(jumper.getFirstName());
        jumperUp.setLastName(jumper.getLastName());
        jumperUp.setTeam(jumper.getTeam());
        jumperUp.setCarrerWins(jumper.getCarrerWins());
        jumperUp.setPersonalBest(jumper.getPersonalBest());
        jumperRepository.save(jumperUp);
    }
    @Override
    public void delete(Integer id){
        jumperRepository.deleteById(id);
    }
    @Override
    public Jumper findById(Integer id){
        return jumperRepository.findById(id);
    }
    @Override
    public List<Jumper> findAll(){
        return jumperRepository.findAll();
    }
    @Override
    public List<Jumper> findAllByTeam(Team team){
        return jumperRepository.findAllByTeam(team);
    }
    @Override
    public List<Jumper> findAllWithPersonalBest(Double personalBest){
         return jumperRepository.findAllJumpersWithPersonalBestGreaterThan(personalBest);
    }
    @Override
    public List<Jumper> findAllWithMoreWinsThan(Integer wins){
         return jumperRepository.findAllJumpersWithMoreWinsThan(wins);
    }
    @Override
    public List<Jumper> findAllWithContainNameAndCWandPB(String contain, short carrerWins, Double personalBest){
        return jumperRepository.findAllByFirstNameContainingAndCarrerWinsLessThanAndPersonalBestGreaterThan(contain,carrerWins,personalBest);
    }
    @Override
    public Jumper getBestJumperByPersonalBest(){
        return jumperRepository.findTopByOrderByPersonalBestDesc();
    }

}
