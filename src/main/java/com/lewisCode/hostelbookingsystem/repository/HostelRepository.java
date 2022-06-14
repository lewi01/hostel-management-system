package com.lewisCode.hostelbookingsystem.repository;

import com.lewisCode.hostelbookingsystem.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelRepository extends JpaRepository<Hostel,Long> {
    Hostel findByName(String hostelName);
    Hostel findHostelByAdminPhoneNumber(String phoneNumber);
}
