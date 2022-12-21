package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Team;
import com.example.demo.exceptions.EntityNotFound;
import com.example.demo.repository.TeamDao;

@Service
public class TeamService {

    @Autowired
    private TeamDao dao;

    public Team getTeamById(int id) {
        Optional<Team> possibleTeam = this.dao.findById(id);

        if (possibleTeam.isPresent()) {
            return possibleTeam.get();
        } else {
            throw new EntityNotFound("Team with ID: " + id + " not Found!");
        }
    }

    public Team getTeamByName(String name) {
        Optional<Team> possibleTeam = this.dao.findByTeamName(name);

        if (possibleTeam.isPresent()) {
            return possibleTeam.get();
        } else {
            throw new EntityNotFound("Team with Name: " + name + " not Found!");
        }
    }

    public List<Team> getAllTeams() {
        List<Team> teams = this.dao.findAll();

        if (!teams.isEmpty()) {
            return teams;
        } else {
            throw new EntityNotFound("No teams found in the database.");
        }
    }

    public Team createTeam(Team team) {
        this.dao.createTeam(team.getTeamName());
        Optional<Team> possibleTeam = this.dao.findByTeamName(team.getTeamName());

        if (possibleTeam.isPresent()) {
            return possibleTeam.get();
        } else {
            throw new EntityNotFound("Couldn't create the team");
        }
    }

    public String updateTeam(String teamName, int id) {
        int result = this.dao.updateTeam(teamName, id);

        if (result != 0) {
            return "Update Successful!";
        } else {
            throw new EntityNotFound("No players to update.");
        }
    }

    public String deleteTeamById(int id) {
        this.dao.deleteById(id);
        return "Deleted team with id of " + id;
    }
}
