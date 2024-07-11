package com.example.training_take_api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class AddressesDTO {
    private Long id;
    private Long profileID;
    private String houseNo;
    private String moo;
    private String soi;
    private String road;
    private String districtName;
    private String subdistrictName;
    private String provinceName;
    private String zipcode;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
