package com.example.driveFileSystem.repository;

public interface NodeRepository extends JpaRepository<Node, Long> {
    List<Node> findByUserAndParent(User user, Node parent);
}
