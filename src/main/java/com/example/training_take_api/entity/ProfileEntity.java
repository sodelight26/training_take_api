package com.example.training_take_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PROFILE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="SEQ_PROFILE")
    @SequenceGenerator(name = "SEQ_PROFILE", sequenceName = "SEQ_PROFILE",   allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private  String lastname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "IMAGE_BASE64")
    private String image;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATE_AT")
    @CreationTimestamp
    private LocalDateTime create;

    @Column(name = "UPDATE_AT")
    @UpdateTimestamp
    private LocalDateTime update;


}
