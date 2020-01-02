package org.ug.project2.service;

import org.ug.project2.model.Coach;

import java.util.List;

public interface CoachService {
    void add(Coach coach);
    List<Coach> coachList();
}
