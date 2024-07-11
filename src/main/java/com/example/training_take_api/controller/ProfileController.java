package com.example.training_take_api.controller;


import com.example.training_take_api.dto.AddressesDTO;
import com.example.training_take_api.dto.ProfileDTO;
import com.example.training_take_api.entity.AddressEntity;
import com.example.training_take_api.entity.ProfileEntity;
import com.example.training_take_api.service.AddressService;
import com.example.training_take_api.service.ProfileService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private AddressService addressService;


    @GetMapping("/getAllProfile")
    public ResponseEntity<List<ProfileEntity>> getAllProfile() {
        List<ProfileEntity> profiles = profileService.getAllProfile();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/getProfileAndAddresses/id={id}")
    public ResponseEntity<ProfileDTO> getEntityIdProfile(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfileByiD(id));
    }

    @PostMapping("/postProfileAndAddress")
    public ResponseEntity<String> postProfileAndAddress(@RequestBody ProfileDTO profileDTO) {
        try {
            profileService.saveProfileAddress(profileDTO);
//            System.out.println(profileDTO);
            return ResponseEntity.ok("Profile and addresses saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save profile and addresses");
        }
    }

    @PutMapping("updateProfileAndAddress/id={id}")
    public ResponseEntity<String> updateProfileandAddress(@PathVariable Long id, @RequestBody ProfileDTO profileDTO) {
        profileDTO.setId(id);
        try {
            profileService.updateProfileAndAddress(profileDTO);
            return ResponseEntity.ok("Profile and address updated sucessfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update profile and addresses");
        }
    }


//    @GetMapping("/getAddressesByProfileId/{profileId}")
//    public ResponseEntity<List<AddressesDTO>> getAddressesByProfileId(@PathVariable Long profileId) {
//        List<AddressesDTO> addresses = addressService.getAddressesByProfileId(profileId);
//        return ResponseEntity.ok(addresses);
//    }


    @DeleteMapping("deleteProfileAndAddress/id={id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        try {
            profileService.deleteProfileAndAddressById(id);
            return ResponseEntity.ok("Profile deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting profile: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteAddress/id={id}")
    public ResponseEntity<Void> deleteeaddress(@PathVariable Long id) {
        profileService.deleteaddress(id);
        return  ResponseEntity.ok().build();
    }


//    @PutMapping("")
//    @ResponseStatus(HttpStatus.ok)
//    public  Page<ProfileEntity> get
}