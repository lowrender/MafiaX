package com.mafia.mafiax.exceptions;

public class RoomIsFullException extends RuntimeException {
    public RoomIsFullException(String message) {
        super(message);
    }
}
