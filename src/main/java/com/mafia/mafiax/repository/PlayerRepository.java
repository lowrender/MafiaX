package com.mafia.mafiax.repository;

import com.mafia.mafiax.entity.Player;
import com.mafia.mafiax.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByRoom(Room room);
}
