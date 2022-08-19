package com.lewisCode.hostelbookingsystem.repository;

import com.lewisCode.hostelbookingsystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository  extends JpaRepository<Room,Long> {
    Room findByName(String name);
    List<Room> findByHostelName(String name);

    List<Room> findUserById( Long id);

}
