package com.platform.registration.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import java.util.UUID;

@Entity
@Data
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "emailid")
        })
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String emailid;

    @NotBlank
    private String password;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String phonenumber;

    public User() {
    }

    public User(String userName, String emailId, String password, String firstName, String lastName, String phoneNumber) {
        this.username = userName;
        this.emailid = emailId;
        this.password = password;
        this.firstname = firstName;
        this.lastname = lastName;
        this.phonenumber = phoneNumber;
    }

}
