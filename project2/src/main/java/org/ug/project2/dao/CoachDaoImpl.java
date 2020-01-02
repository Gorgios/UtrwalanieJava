package org.ug.project2.dao;

import org.springframework.stereotype.Repository;
import org.ug.project2.model.Coach;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CoachDaoImpl implements CoachDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Coach coach) {
        em.persist(coach);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Coach coach) {

    }

    @Override
    public List<Coach> coachList() {
        CriteriaQuery<Coach> criteriaQuery = em.getCriteriaBuilder().createQuery(Coach.class);
        @SuppressWarnings("unused")
        Root<Coach> root = criteriaQuery.from(Coach.class);
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Coach findById(Integer id) {
        return null;
    }
}
