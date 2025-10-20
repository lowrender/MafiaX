package com.mafia.mafiax.repository;

import com.mafia.mafiax.entity.Player;
import com.mafia.mafiax.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {


    @Query("SELECT p FROM Player p JOIN FETCH p.user WHERE p.room = :room")
    List<Player> findByRoomWithUser(@Param("room") Room room);

    Optional<Player> findByUserIdAndRoomId(Long userId, Long roomId);


    long countByRoomId(Long roomId);
}