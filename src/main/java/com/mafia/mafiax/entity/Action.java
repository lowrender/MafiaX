package com.mafia.mafiax.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "actions")
@Getter
@Setter
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "action_type")
    private String action;

    @ManyToOne()
    @JoinColumn(name = "target_id")
    private Player target;

    @Column(name = "round_number")
    private int roundNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
