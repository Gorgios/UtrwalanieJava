package org.ug.project2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.ug.project2.model.Coach;
import org.ug.project2.model.Team;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CoachRepository extends Repository <Coach,Integer> {
    void save(Coach coach);
    List<Coach> findAll();
    List<Coach> findAllByFirstName(String firstName);
    Coach findById(Integer id);
    void deleteById(Integer id);
    void findAllByTeam(Team team);
    List<Coach> findAllByPostion(String postion);
    @Query(value="SELECT * FROM COACH WHERE id_team = ?1",nativeQuery = true)
    List<Coach> findAllCoachesFromTeam(Integer teamId);

}
