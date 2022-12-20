package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Team;
import com.example.demo.service.TeamService;

@RestController
public class TeamController {

    @Autowired
    private TeamService tService;

    @GetMapping("/team/id/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable int id) {
        Team team = this.tService.getTeamById(id);
        System.out.println(team);

        if (team.getTeamId() != 0) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(team, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/team/{name}")
    public ResponseEntity<Team> getTeamById(@PathVariable String name) {
        Team team = this.tService.getTeamByName(name);
        System.out.println(team);

        if (team.getTeamId() != 0) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(team, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = this.tService.getAllTeams();

        if (teams.size() != 0) {
            return new ResponseEntity<>(teams, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(teams, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/team")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team newTeam = this.tService.createTeam(team);

        if (newTeam.getTeamId() != 0) {
            return new ResponseEntity<>(newTeam, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(newTeam, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/team")
    public ResponseEntity<String> updateTeam(@RequestBody Team team) {
        String message = this.tService.updateTeam(team.getTeamName(), team.getTeamId());

        if (message.length() == 18) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_IMPLEMENTED);
        }
    }
}   
