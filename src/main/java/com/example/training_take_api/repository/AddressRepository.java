package com.example.training_take_api.repository;

import com.example.training_take_api.entity.AddressEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
    List<AddressEntity> findByProfileId(Long profileId);




}
