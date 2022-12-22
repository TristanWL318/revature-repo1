package com.example.demo.controller;

import java.util.List;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Team;
import com.example.demo.exceptions.EntityNotFound;
import com.example.demo.service.TeamService;

@RestController
public class TeamController {

    private static Logger teamLogger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService tService;

    @GetMapping("/team/id/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable int id) {
        return new ResponseEntity<>(this.tService.getTeamById(id), HttpStatus.OK);
    }

    @GetMapping("/team/{name}")
    public ResponseEntity<Team> getTeamById(@PathVariable String name) {
        return new ResponseEntity<>(this.tService.getTeamByName(name), HttpStatus.OK);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams() {
        return new ResponseEntity<>(this.tService.getAllTeams(), HttpStatus.OK);
    }

    @PostMapping("/team")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        return new ResponseEntity<>(this.tService.createTeam(team), HttpStatus.CREATED);
    }

    @PatchMapping("/team")
    public ResponseEntity<String> updateTeam(@RequestBody Team team) {
        return new ResponseEntity<>(this.tService.updateTeam(team.getTeamName(), team.getTeamId()), HttpStatus.OK);
    }

    @DeleteMapping("team/{id}")
    public ResponseEntity<String> deleteTeamById(@PathVariable int id) {
        return new ResponseEntity<>(this.tService.deleteTeamById(id), HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> entityNotFound(EntityNotFound e) {
        teamLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> sqlIssue(PSQLException e) {
        teamLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> deleteFailed(EmptyResultDataAccessException e) {
        teamLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
