package com.lewisCode.hostelbookingsystem.repository;

import com.lewisCode.hostelbookingsystem.dto.HostelResponse;
import com.lewisCode.hostelbookingsystem.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostelRepository extends JpaRepository<Hostel,Long> {
    Hostel findByName(String hostelName);
    List<Hostel> findByAdminPhoneNumber(String phoneNumber);

}
