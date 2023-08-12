package com.example.driveFileSystem.service;

@service
public class NodeService {
    @Autowired
    private NodeRepository nodeRepository;

    public List<NodeResponse> getItemsInFolder(Long userId, Long folderId, int page, int size, String sortField) {
        User user = userRepository.findById(userId).orElse(null);
        Node parent = nodeRepository.findById(folderId).orElse(null);

        if (user == null || parent == null || !parent.getUser().equals(user)) {
            throw new NotFoundException("User or folder not found.");
        }


        Pageable pageable = PageRequest.of(page, size, Sort.by(sortField));
        Page<Node> nodePage = nodeRepository.findByUserAndParent(user, parent, pageable);

        return nodePage.getContent().stream()
                .map(node -> new NodeResponse(node.getId(), node.getName(), node.isFolder(), node.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
