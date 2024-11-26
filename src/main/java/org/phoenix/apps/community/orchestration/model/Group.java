package org.phoenix.apps.community.orchestration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private int groupId;
    private String name;
    private int communityId;
    private List<Post> groupPosts;
}
