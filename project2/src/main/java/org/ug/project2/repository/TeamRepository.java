package org.ug.project2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.ug.project2.model.Coach;
import org.ug.project2.model.Jumper;
import org.ug.project2.model.Team;

import java.util.List;

public interface TeamRepository extends Repository<Team,Integer> {
    void save(Team team);
    List<Team> findAll();
    Team findById(Integer id);
    Team deleteById(Integer id);
    List<Team> findAllByName(String name);
    @Query(value="SELECT COUNT(*) FROM Team T JOIN Jumper J ON T.id=J.id_team WHERE J.personal_best > ?1 AND T.id=?2",nativeQuery = true)
    Integer countJumpersWithSelectedPersonalBestInTeam(Double personalBest, Integer teamId);
    List<Team> findAllByCoaches_EmptyAndNameEndsWith(String end);

}
