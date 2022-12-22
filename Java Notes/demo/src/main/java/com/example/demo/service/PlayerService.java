package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Player;
import com.example.demo.exceptions.EntityNotFound;
import com.example.demo.repository.PlayerDao;

@Service
public class PlayerService {

    @Autowired
    private PlayerDao dao;

    public Player getPlayerById(int id) {
        Optional<Player> possiblePlayer = this.dao.findById(id);

        if (possiblePlayer.isPresent()) {
            return possiblePlayer.get();
        } else {
            throw new EntityNotFound("Player with ID: " + id + " not Found!");
        }
    }

    public Player getPlayerByName(String name) {
        Optional<Player> possiblePlayer = this.dao.findByPlayerName(name);

        if (possiblePlayer.isPresent()) {
            return possiblePlayer.get();
        } else {
            throw new EntityNotFound("Player with Name: " + name + " not Found!");
        }
    }

    public List<Player> getAllPlayers() {
        System.out.println("getAllPlayers");
        List<Player> players = this.dao.findAll();

        if (!players.isEmpty()) {
            return players;
        } else {
            throw new EntityNotFound("No players found in the database.");
        }
    }

    public Player createPlayer(Player player) {
        this.dao.createPlayer(player.getPlayerName(), player.getTeamId());
        Optional<Player> possiblePlayer = this.dao.findByPlayerName(player.getPlayerName());

        if (possiblePlayer.isPresent()) {
            return possiblePlayer.get();
        } else {
            throw new EntityNotFound("Couldn't create the player");
        }
    }

    public String updatePlayer(Player player) {
        if (this.dao.updatePlayer(player.getPlayerName(), player.getTeamId(), player.getPlayerId()) != 0) {
            return "Update Successful!";
        } else {
            throw new EntityNotFound("No players to update.");
        }
    }

    public String deletePlayerById(int id) {
        this.dao.deleteById(id);
        return "Deleted player with id of " + id;
    }
}
