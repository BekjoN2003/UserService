package com.example.userservice.repository;

import com.example.userservice.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    List<Application> findByUser_id(Integer userId);

    List<Application> findByJob_id(Integer jobId);





}
