package org.ug.project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ug.project2.dao.CoachDao;
import org.ug.project2.model.Coach;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    CoachDao coachDao;
    @Override
    public void add(Coach coach) {
        coachDao.add(coach);
    }

    @Override
    public List<Coach> coachList() {
        return coachDao.coachList();
    }
}
