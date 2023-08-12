package com.example.driveFileSystem.controller;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam Long userId, @RequestParam Long folderId, @RequestParam MultipartFile file) {
        String message = fileService.uploadFile(userId, folderId, file);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/create-folder")
    public ResponseEntity<String> createFolder(@RequestParam Long userId, @RequestParam Long parentFolderId, @RequestParam String folderName) {
        String message = fileService.createFolder(userId, parentFolderId, folderName);
        return ResponseEntity.ok(message);
    }
}
