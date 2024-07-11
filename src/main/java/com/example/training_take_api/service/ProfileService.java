package com.example.training_take_api.service;

import com.example.training_take_api.dto.AddressesDTO;
import com.example.training_take_api.dto.ProfileDTO;
import com.example.training_take_api.entity.AddressEntity;
import com.example.training_take_api.entity.ProfileEntity;
import com.example.training_take_api.repository.AddressRepository;
import com.example.training_take_api.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import org.hibernate.query.Page;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    public List<ProfileEntity> getAllProfile() {
        return profileRepository.findAll();
    }

    public ProfileDTO getProfileByiD(Long id) {
        ProfileEntity ProfileId = profileRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Profile not found")
        );
        List<AddressEntity> addressList = addressRepository.findByProfileId(ProfileId.getId());

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setId(ProfileId.getId());
        profileDTO.setFirstname(ProfileId.getFirstname());
        profileDTO.setLastname(ProfileId.getLastname());
        profileDTO.setEmail(ProfileId.getEmail());
        profileDTO.setTelephone(ProfileId.getTelephone());
        profileDTO.setPassword(ProfileId.getPassword());
        profileDTO.setImage(ProfileId.getImage());
        profileDTO.setCreateAt(ProfileId.getCreate());
        profileDTO.setUpdateAt(ProfileId.getUpdate());
        List<AddressesDTO> addressDtos = new ArrayList<>();
        for (AddressEntity address : addressList) {
            AddressesDTO addressDto = new AddressesDTO();

            addressDto.setId(address.getId());
            addressDto.setProfileID(address.getProfileId());
            addressDto.setHouseNo(address.getHouseNo());
            addressDto.setMoo(address.getMoo());
            addressDto.setSoi(address.getSoi());
            addressDto.setRoad(address.getRoad());
            addressDto.setDistrictName(address.getDistrictName());
            addressDto.setSubdistrictName(address.getSubdistrictName());
            addressDto.setProvinceName(address.getProvinceName());
            addressDto.setZipcode(address.getZipcode());
            addressDto.setCreateAt(address.getCreateAt());
            addressDto.setUpdateAt(address.getUpdateAt());
            addressDtos.add(addressDto);
        }
        addressDtos.sort(Comparator.comparing(AddressesDTO::getId));
        profileDTO.setAddresses(addressDtos);

        return profileDTO;


    }


    public ProfileEntity saveProfile(ProfileEntity entity) {
        return profileRepository.save(entity);
    }

//    @Transactional
//    public void deleteProfileAndAddress(Long profileId) {
//        addressReposito.deleteByProfileID(profileId);
//        profileRepository.deleteById(profileId);
//    }

    public void saveProfileAddress(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = new ProfileEntity();
        BeanUtils.copyProperties(profileDTO, profileEntity);
        ProfileEntity savedProfileEntity = saveProfile(profileEntity);

        if (profileDTO.getAddresses() != null) {
            for (AddressesDTO addressesDTO : profileDTO.getAddresses()) {
                AddressEntity addressEntity = new AddressEntity();
                BeanUtils.copyProperties(addressesDTO, addressEntity);
                addressEntity.setProfileId(savedProfileEntity.getId());
                addressService.saveEntity(addressEntity);
            }
        }
    }


    @Transactional
    public void updateProfileAndAddress(ProfileDTO profileDTO) {
        ProfileEntity existingProfileEntity = profileRepository.findById(profileDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found with ID: " + profileDTO.getId()));

        BeanUtils.copyProperties(profileDTO, existingProfileEntity);
        existingProfileEntity.setUpdate(LocalDateTime.now());

        // Save the updated profile entity
        profileRepository.save(existingProfileEntity);
        // Update or add addresses
        if (profileDTO.getAddresses() != null) {
            for (AddressesDTO addressesDTO : profileDTO.getAddresses()) {
                AddressEntity addressEntity;

                if (addressesDTO.getId() != null) {
                    // Update existing address
                    addressEntity = addressRepository.findById(addressesDTO.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Address not found with ID: " + addressesDTO.getId()));
                } else {
                    // Create new address
                    addressEntity = new AddressEntity();
                }

                // Copy properties from addressesDTO to addressEntity
                BeanUtils.copyProperties(addressesDTO, addressEntity);
                addressEntity.setProfileId(existingProfileEntity.getId());

                // Save the address entity
                addressService.saveEntity(addressEntity);
            }
        }
        // Delete addresses in listdeletekey
        if (profileDTO.getListdeletekey() != null) {
            for (Long addressId : profileDTO.getListdeletekey()) {
                addressRepository.deleteById(addressId);
            }
        }
    }

    @Transactional
    public void deleteProfileAndAddressById(Long id) {
        List<AddressEntity> addresses = addressRepository.findByProfileId(id);
        for (AddressEntity address : addresses) {
            addressRepository.delete(address);
        }
        // Now delete the Profile
        profileRepository.deleteById(id);
    }

    @Transactional
    public void deleteaddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }





//end
}





