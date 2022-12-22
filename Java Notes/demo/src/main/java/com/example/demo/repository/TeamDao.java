package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Team;

public interface TeamDao extends JpaRepository<Team, Integer> {

    Optional<Team> findByTeamName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into teams values (default, :teamName)", nativeQuery = true)
    void createTeam(@Param("teamName") String teamName);

    @Transactional
    @Modifying
    @Query(value = "update teams set team_name = :teamName where team_id = :teamId", nativeQuery = true)
    int updateTeam(@Param("teamName") String teamName, @Param("teamId") int teamId);
}
