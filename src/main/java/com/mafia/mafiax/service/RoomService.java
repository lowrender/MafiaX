package com.mafia.mafiax.service;

import com.mafia.mafiax.dto.RoomDTO;
import com.mafia.mafiax.entity.Room;
import com.mafia.mafiax.entity.RoomStatus;
import com.mafia.mafiax.entity.Users;
import com.mafia.mafiax.exceptions.RoomNotFoundException;
import com.mafia.mafiax.exceptions.UserNotFoundException;
import com.mafia.mafiax.mapper.RoomMapper;
import com.mafia.mafiax.repository.RoomRepository;
import com.mafia.mafiax.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final RoomMapper roomMapper;

    public RoomDTO createRoom(String code, Long hostId, int maxPlayers) {
        Users host = userRepository.findById(hostId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + hostId + " not found"));

        Room room = Room.builder()
                .code(code)
                .host(host)
                .maxPlayers(maxPlayers)
                .status(RoomStatus.WAITING)
                .build();

        return roomMapper.toDTO(roomRepository.save(room));
    }

    public RoomDTO getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room with id " + roomId + " not found"));
        return roomMapper.toDTO(room);
    }

    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }
}
