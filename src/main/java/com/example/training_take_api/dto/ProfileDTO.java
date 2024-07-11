package com.example.training_take_api.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class ProfileDTO {
        private Long id;
        private String firstname;
        private String lastname;
        private String email;
        private String telephone;
        private String image;
        private String password;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
        private List<AddressesDTO> addresses;
        private List<Long> listdeletekey;
}
