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

import com.example.demo.entities.Player;
import com.example.demo.exceptions.EntityNotFound;
import com.example.demo.service.PlayerService;

@RestController
public class PlayerController {

    private static Logger playerLogger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService pService;

    @GetMapping("/player/id/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int id) {
        return new ResponseEntity<>(this.pService.getPlayerById(id), HttpStatus.OK);
    }

    @GetMapping("/player/{name}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String name) {
        return new ResponseEntity<>(this.pService.getPlayerByName(name), HttpStatus.OK);
    }

    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(this.pService.getAllPlayers(), HttpStatus.OK);
    }

    @PostMapping("/player")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(this.pService.createPlayer(player), HttpStatus.CREATED);
    }

    @PatchMapping("/player")
    public ResponseEntity<String> updatePlayer(@RequestBody Player player) {
        return new ResponseEntity<>(this.pService.updatePlayer(player), HttpStatus.OK);
    }

    @DeleteMapping("player/{id}")
    public ResponseEntity<String> deletePlayerById(@PathVariable int id) {
        return new ResponseEntity<>(this.pService.deletePlayerById(id), HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> entityNotFound(EntityNotFound e) {
        playerLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> sqlIssue(PSQLException e) {
        playerLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> deleteFailed(EmptyResultDataAccessException e) {
        playerLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
