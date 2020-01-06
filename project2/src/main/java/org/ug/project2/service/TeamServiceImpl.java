package org.ug.project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ug.project2.model.Team;
import org.ug.project2.repository.TeamRepository;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void addAll(List<Team> teams) {
        for (Team t : teams)
            teamRepository.save(t);
    }

    @Override
    public void add(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void delete(Integer id) {
        teamRepository.deleteById(id);
    }

    @Override
    public void update(Team team, Integer id) {
        Team teamUp = teamRepository.findById(id);
        teamUp.setName(team.getName());
        teamUp.setCoaches(team.getCoaches());
        teamUp.setJumpers(team.getJumpers());
        teamRepository.save(teamUp);
    }

    @Override
    public Team findByName(String name){
        return teamRepository.findAllByName(name).get(0);
    }
    @Override
    public Team findById(Integer Id) {
        return teamRepository.findById(Id);
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> findAllWithoutCoachAndNameEndsBy(String end) {
        return teamRepository.findAllByCoaches_EmptyAndNameEndsWith(end);
    }

    @Override
    public Integer countJumpersWithPersonalBest(Double personalBest, Team team) {
        return teamRepository.countJumpersWithSelectedPersonalBestInTeam(personalBest, team.getId());
    }
}
