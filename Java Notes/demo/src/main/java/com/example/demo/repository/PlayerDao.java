package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Player;

public interface PlayerDao extends JpaRepository<Player, Integer> {

    Optional<Player> findByPlayerName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into players values (default, :playerName, :playerTeamId)", nativeQuery = true)
    void createPlayer(@Param("playerName") String playerName, @Param("playerTeamId") int playerTeamId);

    @Transactional
    @Modifying
    @Query(value = "update players set player_name = :playerName, player_team_id = :playerTeamId where player_id = :playerId", nativeQuery = true)
    int updatePlayer(@Param("playerName") String playerName, @Param("playerTeamId") int playerTeamId, @Param("playerId") int playerId);
}