package org.phoenix.apps.community.orchestration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int postId;
    private int userId;
    private int communityId;
    private int groupId;
    private String postMessage;
}
