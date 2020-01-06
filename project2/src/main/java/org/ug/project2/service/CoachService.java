package org.ug.project2.service;

import org.ug.project2.model.Coach;
import org.ug.project2.model.Jumper;
import org.ug.project2.model.Team;

import java.util.List;

public interface CoachService {
    void addAll(List<Coach> coaches);

    void add(Coach coach);

    void update(Coach coach, Integer id);

    void delete(Integer id);

    List<Coach> findAll();

    Coach findById(Integer id);

    List<Coach> findAllByTeam(Team team);

    List<Coach> findAllJumperCoaches(Jumper jumper);

    List<Coach> findAllByFristName(String firstName);
}
