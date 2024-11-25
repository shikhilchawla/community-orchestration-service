package org.phoenix.apps.community.orchestration.service;

import org.phoenix.apps.community.orchestration.model.Comment;
import org.phoenix.apps.community.orchestration.model.Community;
import org.phoenix.apps.community.orchestration.model.Group;
import org.phoenix.apps.community.orchestration.model.Post;

import java.util.List;

public interface CommunityOrchestrationService {

    Post addNewPost(Post post);

    Post updatePost(Post post);

    Comment addPostComments(Comment comment);

    Community addCommunity(Community community);

    Group addCommunityGroup(Group group);

    List<Post> getAllPostsForCommunity(String communityId);

    List<Post> getAllPostsForGroup(String groupId);


}
