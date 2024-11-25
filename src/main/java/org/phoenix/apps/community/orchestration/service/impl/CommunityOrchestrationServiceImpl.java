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
            return webClientBuilder.build().put()
                    .uri(new URI(postServiceUrl + updatePostPath))
                    .header("Authorization", "Bearer MY_SECRET_TOKEN")
                    .bodyValue(post)
                    .retrieve()
                    .bodyToMono(Post.class)
                    .block();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Comment addPostComments(Comment comment) {
        return null;
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
    public List<Post> getAllPostsForCommunity(String communityId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(new URI(postServiceUrl + getCommunityPostsPath + communityId))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Post>>() {})
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getAllPostsForGroup(String groupId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(new URI(postServiceUrl + getGroupPostsPath + groupId))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Post>>() {})
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
