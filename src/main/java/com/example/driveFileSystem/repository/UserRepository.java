package com.example.driveFileSystem.repository;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);

}
