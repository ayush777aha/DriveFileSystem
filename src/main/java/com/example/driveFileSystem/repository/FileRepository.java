package com.example.driveFileSystem.repository;

public interface FileRepository extends JpaRepository<File, Long> {
        List<File> findByNode(Node node);
        }
