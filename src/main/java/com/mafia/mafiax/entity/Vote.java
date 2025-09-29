package com.mafia.mafiax.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player voter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id", nullable = false)
    private Player target;

    @Column(name = "round_number", nullable = false)
    private int roundNumber;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime voteDate;

    @PrePersist
    protected void onCreate() {
        this.voteDate = LocalDateTime.now();
    }
}
