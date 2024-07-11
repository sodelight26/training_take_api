package com.example.training_take_api.repository;

import com.example.training_take_api.dto.ProfileDTO;
import com.example.training_take_api.entity.ProfileEntity;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity,Long>{

}


