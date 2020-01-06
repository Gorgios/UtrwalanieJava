package org.ug.project2.service;

import org.ug.project2.model.Jumper;
import org.ug.project2.model.Team;

import java.util.List;

public interface JumperService {
    void addAll(List<Jumper> jumpers);

    void add(Jumper jumper);

    void update(Jumper jumper, Integer id);

    void delete(Integer id);

    Jumper findById(Integer id);

    List<Jumper> findAll();

    List<Jumper> findAllByTeam(Team team);

    List<Jumper> findAllWithPersonalBest(Double personalBest);

    List<Jumper> findAllWithMoreWinsThan(Integer wins);

    List<Jumper> findAllWithContainNameAndCWandPB(String contain, short carrerWins, Double personalBest);

    Jumper getBestJumperByPersonalBest();
}
