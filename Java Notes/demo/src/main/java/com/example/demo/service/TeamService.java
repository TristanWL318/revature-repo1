package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Team;
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
            return new Team();
        }
    }

    public Team getTeamByName(String name) {
        Optional<Team> possibleTeam = this.dao.findByTeamName(name);
        if (possibleTeam.isPresent()) {
            return possibleTeam.get();
        } else {
            return new Team();
        }
    }

    public List<Team> getAllTeams() {
        return this.dao.findAll();
    }

    public Team createTeam(Team team) {
        this.dao.createTeam(team.getTeamName());
        Optional<Team> possibleTeam = this.dao.findByTeamName(team.getTeamName());

        if (possibleTeam.isPresent()) {
            return possibleTeam.get();
        } else {
            return new Team();
        }
    }

    public String updateTeam(String teamName, int id) {
        int result = this.dao.updateTeam(teamName, id);
        if (result != 0) {
            return "Update Successful!";
        } else {
            return "Update Failed.";
        }
    }
}
