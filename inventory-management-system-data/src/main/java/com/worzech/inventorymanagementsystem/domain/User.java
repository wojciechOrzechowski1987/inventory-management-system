package com.worzech.inventorymanagementsystem.domain;

import com.worzech.inventorymanagementsystem.domain.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "IMSUser")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role userRole;

    public User(String firstName, String lastName, String username, String password, String email, Role userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }
}
