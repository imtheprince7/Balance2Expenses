package com.b2c.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private  String firstName;
    private  String lastName;
    private  int phoneNumber;
    private  String emailId;
    private  String password;
    private  String confirmPassword;
    private  String userType;
}
