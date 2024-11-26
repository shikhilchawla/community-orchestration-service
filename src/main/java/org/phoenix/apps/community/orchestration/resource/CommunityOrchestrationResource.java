package org.phoenix.apps.community.orchestration.resource;

import org.phoenix.apps.community.orchestration.model.Comment;
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
    public ResponseEntity<List<Post>> getAllPostsForCommunity(@PathVariable int communityId) {
        List<Post> response = this.communityOrchestrationService.getAllPostsForCommunity(communityId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/group/{groupId}/posts")
    public ResponseEntity<List<Post>> getAllPostsForGroup(@PathVariable int groupId) {
        List<Post> response = this.communityOrchestrationService.getAllPostsForGroup(groupId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> addNewComment(@RequestBody Comment comment) {
        Comment response = this.communityOrchestrationService.addPostComments(comment);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        Comment response = this.communityOrchestrationService.updatePostComment(comment);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/posts/{postId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentId) {
        this.communityOrchestrationService.deletPostComment(commentId);
        return ResponseEntity.ok().body("Comment deleted successfully.");
    }
}
