package org.ug.project2.dao;

import org.springframework.data.repository.CrudRepository;
import org.ug.project2.model.Coach;

import java.util.List;

public interface CoachDao {
    void add(Coach coach);
    void delete(Integer id);
    void update(Coach coach);
    List<Coach> coachList();
    Coach findById(Integer id);
}
