package org.phoenix.apps.community.orchestration.resource;

import org.phoenix.apps.community.orchestration.model.Comment;
import org.phoenix.apps.community.orchestration.model.Community;
import org.phoenix.apps.community.orchestration.model.Group;
import org.phoenix.apps.community.orchestration.model.Post;
import org.phoenix.apps.community.orchestration.service.CommunityOrchestrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my-community")
public class CommunityMutateResource {

    private final CommunityOrchestrationService communityOrchestrationService;

    public CommunityMutateResource(CommunityOrchestrationService communityOrchestrationService) {
        this.communityOrchestrationService = communityOrchestrationService;
    }

    @PostMapping
    public ResponseEntity<Community> addNewCommunity(@RequestBody Community community) {
        return ResponseEntity.ok().body(this.communityOrchestrationService.addCommunity(community));
    }

    @PostMapping("/groups")
    public ResponseEntity<Group> addNewGroup(@RequestBody Group group) {
        return ResponseEntity.ok().body(this.communityOrchestrationService.addCommunityGroup(group));
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
