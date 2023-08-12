package com.example.driveFileSystem.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String passwordHash;
}
