package com.example.training_take_api.controller;


import com.example.training_take_api.entity.AddressEntity;
import com.example.training_take_api.repository.AddressRepository;
import com.example.training_take_api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;


    @GetMapping("/getAllAddress")
    public ResponseEntity<List<AddressEntity>> getAllEntities() {
        return ResponseEntity.ok(addressService.getAllEntities());
    }

    @GetMapping("/getByIdAddress")
    public ResponseEntity<List<AddressEntity>> getAddressEntitiesByProfile(@RequestParam Long id) {
        List<AddressEntity> addressEntities = addressService.getAddressEntitiesByProfile(id);
        return ResponseEntity.ok(addressEntities);
    }

    public List<AddressEntity> getAddressEntitiesByProfileId(Long profileID) {
        return addressRepository.findByProfileId(profileID);
    }

//    @GetMapping("/getByIdAddressProfileId")
//    public ResponseEntity<AddressEntity> getEntityIdAddressProfileID(@RequestParam Long id) {
//        Optional<List<AddressEntity>> addressEntity = addressService.getEntityId(id);
//        return  addressEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @PostMapping("/postAddress")
    public ResponseEntity<AddressEntity> postAddress(@RequestBody AddressEntity addressEntity) {
        try {
            AddressEntity savedEntity = addressService.saveEntity(addressEntity);
            return ResponseEntity.ok(savedEntity);
        } catch (Exception e) {
            System.out.println("err");
        }
        return null;
    }

    @DeleteMapping("/deleteAddress")
    public ResponseEntity<String> deleteAddress(@RequestParam Long id){
        try{
            addressService.deleteEntity(id);
        } catch (Exception e) {
            System.out.print("err");
        }
        return null;
    }


}
