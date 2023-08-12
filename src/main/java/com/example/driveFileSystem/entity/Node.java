package com.example.driveFileSystem.entity;

@Entity
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Entity.User user;
    @ManyToOne
    private Entity.Node parent;
    private String name;
    private boolean isFolder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
