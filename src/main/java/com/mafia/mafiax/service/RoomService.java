package com.mafia.mafiax.service;

import com.mafia.mafiax.dto.RoomDTO;
import com.mafia.mafiax.entity.Room;
import com.mafia.mafiax.entity.RoomStatus;
import com.mafia.mafiax.entity.User;
import com.mafia.mafiax.exceptions.RoomNotFoundException;
import com.mafia.mafiax.exceptions.UserNotFoundException;
import com.mafia.mafiax.mapper.RoomMapper;
import com.mafia.mafiax.repository.RoomRepository;
import com.mafia.mafiax.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final RoomMapper roomMapper;

    private static final int CODE_LENGTH = 4;
    private static final int MAX_ATTEMPTS = 10;

    public String generateUniqueCode() {
        Random random = new Random();
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            String code = String.format("%0" + CODE_LENGTH + "d", random.nextInt((int) Math.pow(10, CODE_LENGTH)));
            if (roomRepository.findByCode(code).isEmpty()) {
                return code;
            }
        }
        throw new RuntimeException("Ошибка при генерации кода комнаты после " + MAX_ATTEMPTS + " попыток");
    }

    public RoomDTO createRoom(Long hostId, int maxPlayers) {
        User host = userRepository.findById(hostId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь " + hostId + " не найден"));

        String uniqueCode = generateUniqueCode();

        Room room = Room.builder()
                .code(uniqueCode)
                .host(host)
                .maxPlayers(maxPlayers)
                .status(RoomStatus.WAITING)
                .build();

        return roomMapper.toDTO(roomRepository.save(room));
    }

    @Transactional(readOnly = true)
    public RoomDTO getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Комната с ID:  " + roomId + " не найдена"));
        return roomMapper.toDTO(room);
    }

    @Transactional(readOnly = true)
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }
}