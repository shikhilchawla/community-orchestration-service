package org.phoenix.apps.community.orchestration.resource;

import org.phoenix.apps.community.orchestration.model.Post;
import org.phoenix.apps.community.orchestration.service.CommunityOrchestrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-community")
public class CommunityOrchestrationResource {

    private final CommunityOrchestrationService communityOrchestrationService;

    public CommunityOrchestrationResource(CommunityOrchestrationService communityOrchestrationService) {
        this.communityOrchestrationService = communityOrchestrationService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Orchestration service is healthy.");
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> addNewPost(@RequestBody Post request) {
        Post response = this.communityOrchestrationService.addNewPost(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/posts")
    public ResponseEntity<Post> updatePost(@RequestBody Post request) {
        Post response = this.communityOrchestrationService.updatePost(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/community/{communityId}/posts")
    public ResponseEntity<List<Post>> getAllPostsForCommunity(@PathVariable String communityId) {
        List<Post> response = this.communityOrchestrationService.getAllPostsForCommunity(communityId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/group/{groupId}/posts")
    public ResponseEntity<List<Post>> getAllPostsForGroup(@PathVariable String groupId) {
        List<Post> response = this.communityOrchestrationService.getAllPostsForGroup(groupId);
        return ResponseEntity.ok().body(response);
    }
}
