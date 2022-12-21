package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "player_team_id")
    private int teamId;
    
}
