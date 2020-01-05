package org.ug.project2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.ug.project2.model.Jumper;
import org.ug.project2.model.Team;

import javax.transaction.Transactional;
import java.util.List;

public interface JumperRepository extends Repository<Jumper,Integer> {
    void save(Jumper jumper);
    List<Jumper> findAll();
    Jumper findById(Integer id);
    void deleteById(Integer id);
    List<Jumper> findAllByTeam(Team team);
    @Query(value="SELECT * FROM JUMPER WHERE PERSONAL_BEST > ?1",nativeQuery = true)
    List<Jumper> findAllJumpersWithPersonalBestGreaterThan(Double personalBest);
    @Query(value="SELECT * FROM JUMPER WHERE CARRER_WINS > ?1", nativeQuery =  true)
    List<Jumper> findAllJumpersWithMoreWinsThan(Integer carrerWins);
    List<Jumper> findAllByFirstNameContainingAndCarrerWinsLessThanAndPersonalBestGreaterThan(String firstName,short carrerWins,Double personalBest);
    Jumper findTopByOrderByPersonalBestDesc();
    @Query(value="SELECT * FROM JUMPER WHERE id_team = ?1",nativeQuery = true)
    List<Jumper> findAllJumpersFromTeam(Integer teamId);
}
