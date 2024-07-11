package com.example.training_take_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
    @SequenceGenerator(name = "SEQ_ADDRESS", sequenceName = "SEQ_ADDRESS", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROFILE_ID")
    private Long profileId;

    @Column(name = "HOUSE_NO")
    private String houseNo;

    @Column(name = "MOO")
    private String moo;

    @Column(name = "SOI")
    private String soi;

    @Column(name = "ROAD")
    private String road;

    @Column(name = "DISTRICT_NAME")
    private String districtName;

    @Column(name = "SUBDISTRICT_NAME")
    private String subdistrictName;

    @Column(name = "PROVINCE_NAME")
    private String provinceName;

    @Column(name = "ZIPCODE")
    private String zipcode;

    @Column(name = "CREATE_AT")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "UPDATE_AT")
    @UpdateTimestamp
    private LocalDateTime updateAt;



}
