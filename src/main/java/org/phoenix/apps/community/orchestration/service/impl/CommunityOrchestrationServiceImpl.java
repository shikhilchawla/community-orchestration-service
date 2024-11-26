package org.phoenix.apps.community.orchestration.service.impl;

import org.phoenix.apps.community.orchestration.model.Comment;
import org.phoenix.apps.community.orchestration.model.Community;
import org.phoenix.apps.community.orchestration.model.Group;
import org.phoenix.apps.community.orchestration.model.Post;
import org.phoenix.apps.community.orchestration.service.CommunityOrchestrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityOrchestrationServiceImpl implements CommunityOrchestrationService {
    @Value("${user.post.service.url}")
    private String postServiceUrl;
    @Value("${user.post.service.addPost.path}")
    private String addPostPath;
    @Value("${user.post.service.updatePost.path}")
    private String updatePostPath;
    @Value("${user.post.service.getPostsByCommunityId.path}")
    private String getCommunityPostsPath;
    @Value("${user.post.service.getPostsByGroupId.path}")
    private String getGroupPostsPath;
    @Value("${user.comment.service.url}")
    private String commentServiceURL;
    @Value("${user.comment.service.addComment.path}")
    private String addCommentPath;
    @Value("${user.comment.service.updateComment.path}")
    private String updateCommentPath;
    @Value("${user.comment.service.getCommentsByPostId.path}")
    private String getCommentsByPostIdPath;

    private final WebClient.Builder webClientBuilder;

    public CommunityOrchestrationServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Post addNewPost(Post post) {
        try {
            return webClientBuilder.build()
                    .post()
                    .uri(new URI(postServiceUrl + addPostPath))
                    .header("Authorization", "Bearer MY_SECRET_TOKEN")
                    .bodyValue(post)
                    .retrieve()
                    .bodyToMono(Post.class)
                    .block();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post updatePost(Post post) {
        try {
            Post result = webClientBuilder.build()
                    .put()
                    .uri(new URI(postServiceUrl + updatePostPath))
                    .header("Authorization", "Bearer MY_SECRET_TOKEN")
                    .bodyValue(post)
                    .retrieve()
                    .bodyToMono(Post.class)
                    .block();
            if (result != null)
                result.setComments(getCommentsByPostId(result.getPostId()));
            return result;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Comment addPostComments(Comment comment) {
        try {
            return webClientBuilder.build()
                    .post()
                    .uri(new URI(commentServiceURL + addCommentPath))
                    .bodyValue(comment)
                    .retrieve()
                    .bodyToMono(Comment.class)
                    .block();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment updatePostComment(Comment comment) {
        try {
            return webClientBuilder.build()
                    .put()
                    .uri(new URI(commentServiceURL + updateCommentPath))
                    .bodyValue(comment)
                    .retrieve()
                    .bodyToMono(Comment.class)
                    .block();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Community addCommunity(Community community) {
        return null;
    }

    @Override
    public Group addCommunityGroup(Group group) {
        return null;
    }

    @Override
    public List<Post> getAllPostsForCommunity(int communityId) {
        try {
            List<Post> results = webClientBuilder.build()
                    .get()
                    .uri(new URI(postServiceUrl + getCommunityPostsPath + communityId))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Post>>() {})
                    .block();

            assert results != null;
            return results.stream()
                    .peek(post -> post.setComments(getCommentsByPostId(post.getPostId()))
            ).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getAllPostsForGroup(int groupId) {
        try {
            List<Post> results = webClientBuilder.build()
                    .get()
                    .uri(new URI(postServiceUrl + getGroupPostsPath + groupId))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Post>>() {})
                    .block();
            assert results != null;
            return results.stream()
                    .peek(post -> post.setComments(getCommentsByPostId(post.getPostId()))
                    ).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletPostComment(int commentId) {
        try {
            webClientBuilder.build()
                    .delete()
                    .uri(new URI(commentServiceURL + addCommentPath + "/" + commentId))
                    .retrieve().bodyToMono(String.class)
                    .block();
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    private List<Comment> getCommentsByPostId(int postId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(new URI(commentServiceURL + getCommentsByPostIdPath + postId))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Comment>>() {
                    })
                    .block();
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }
}
