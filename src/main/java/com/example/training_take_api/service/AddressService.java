package com.example.training_take_api.service;

import com.example.training_take_api.entity.AddressEntity;
import com.example.training_take_api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;


//    test
    public List<AddressEntity> getAllEntities() {
        return addressRepository.findAll();
    }

    public Optional<AddressEntity> getEntityId(Long id) {
        return addressRepository.findById(id);
    }
    public  AddressEntity saveEntity(AddressEntity entity) {
        return addressRepository.save(entity);
    }

    public  void deleteEntity(Long id) {
        addressRepository.deleteById(id);
    }

    public List<AddressEntity> getAddressEntitiesByProfile(Long profileId) {
        return  addressRepository.findByProfileId(profileId);
    }
//    public List<AddressEntity> getAddressEntitiesByProfileId(Long profileID) {
//        return addressRepository.findByProfileId(profileID);
//    }
}
