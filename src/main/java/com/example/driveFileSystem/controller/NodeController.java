package com.example.driveFileSystem.controller;
@RestController
@RequestMapping("/api")

public class NodeController {
    @Autowired
    private NodeService nodeService;

    @GetMapping("/fetch")
    public ResponseEntity<List<NodeResponse>> fetchItems(
            @RequestParam Long userId,
            @RequestParam Long folderId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortField) {

        List<NodeResponse> items = nodeService.getItemsInFolder(userId, folderId, page, size, sortField);
        return ResponseEntity.ok(items);
    }
}
