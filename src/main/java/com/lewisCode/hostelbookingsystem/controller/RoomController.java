package com.lewisCode.hostelbookingsystem.controller;

import com.lewisCode.hostelbookingsystem.entity.Room;
import com.lewisCode.hostelbookingsystem.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/room/")
@AllArgsConstructor
@Validated
public class RoomController {

    private RoomService roomService;

    @PostMapping("/hostel/{hostelId}/create")
    public ResponseEntity<?> createRoom(@PathVariable Long hostelId,
                                          @Valid @RequestBody Room room){
        roomService.createRoom(hostelId, room);
        return ResponseEntity.ok(room.getName() +" was add successfully");
    }
    @PutMapping("/update/{name}")
    public ResponseEntity<?> updateRoom(@Valid @RequestBody Room room,
                                          @PathVariable String name){
        roomService.updateRoom(room, name);
        return ResponseEntity.ok(room.getName() +" was update successfully");
    }
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteRoom(@PathVariable String name){
        roomService.deleteRoom(name);
        return ResponseEntity.ok(name +" was successfully deleted");
    }
    @GetMapping("/get/{name}")
    public Room getRoom(@PathVariable String name){

        return roomService.findRoom(name);
    }
    @GetMapping("/hostel/{name}")
    public List<Room> getAllRoom(
            @PathVariable String name ){
        return roomService.findAllRoomByHostelName(name);
    }

}
