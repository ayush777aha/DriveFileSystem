package com.example.driveFileSystem.entity;

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Entity.Node node;
    private String contentType;
    private Long size;
    private String storagePath;
}
