package com.example.driveFileSystem.service;

@service
public class FileService {
        @Autowired
        private NodeRepository nodeRepository;

        @Autowired
        private FileRepository fileRepository;

        @Value("${file.storage.location}")
        private String fileStorageLocation;

        public String uploadFile(Long userId, Long folderId, MultipartFile file) {
            User user = userRepository.findById(userId).orElse(null);
            Node parent = nodeRepository.findById(folderId).orElse(null);

            if (user == null || parent == null || !parent.getUser().equals(user)) {
                throw new NotFoundException("User or folder not found.");
            }

            // Generate a unique filename
            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = generateUniqueFilename(originalFilename);

            // Save the file to the file storage location
            String filePath = fileStorageLocation + uniqueFilename;
            saveFile(file, filePath);

            // Create a new File entity and save it to the database
            File newFile = new File();
            newFile.setContentType(file.getContentType());
            newFile.setSize(file.getSize());
            newFile.setStoragePath(filePath);
            newFile.setNode(parent);

            fileRepository.save(newFile);

            return "File uploaded successfully.";
        }

        private String generateUniqueFilename(String originalFilename) {
            String baseName = FilenameUtils.getBaseName(originalFilename);
            String extension = FilenameUtils.getExtension(originalFilename);
            String uniqueFilename = baseName + "_" + System.currentTimeMillis() + "." + extension;
            return uniqueFilename;
        }

        private void saveFile(MultipartFile file, String filePath) {
            try {
                File destinationFile = new File(filePath);
                file.transferTo(destinationFile);
            } catch (IOException e) {
                throw new FileUploadException("Failed to save the uploaded file.");
            }
        }

    public String createFolder(Long userId, Long parentFolderId, String folderName) {
        User user = userRepository.findById(userId).orElse(null);
        Node parent = nodeRepository.findById(parentFolderId).orElse(null);

        if (user == null || parent == null || !parent.getUser().equals(user)) {
            throw new NotFoundException("User or parent folder not found.");
        }

        Node newFolder = new Node();
        newFolder.setUser(user);
        newFolder.setParent(parent);
        newFolder.setName(folderName);
        newFolder.setFolder(true);
        newFolder.setCreatedAt(LocalDateTime.now());

        nodeRepository.save(newFolder);

        return "Folder created successfully.";
    }
    }


