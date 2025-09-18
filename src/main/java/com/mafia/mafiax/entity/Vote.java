package com.mafia.mafiax.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
@Getter
@Setter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player voter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "target_id")
    private Player target;

    @Column(name = "round_number")
    private int roundNumber;

    @Column(name = "created_at")
    private LocalDateTime voteDate = LocalDateTime.now();


}
