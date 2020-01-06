package org.ug.project2.service;

import org.ug.project2.model.Team;

import java.util.List;

public interface TeamService {
    void addAll(List<Team> teams);

    void add(Team team);

    void delete(Integer id);

    void update(Team team, Integer id);

    Team findByName(String name);

    Team findById(Integer id);

    List<Team> findAll();

    List<Team> findAllWithoutCoachAndNameEndsBy(String end);

    Integer countJumpersWithPersonalBest(Double personalBest, Team team);
}
