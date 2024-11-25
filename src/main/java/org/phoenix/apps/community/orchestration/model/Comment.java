package org.phoenix.apps.community.orchestration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int commentId;
    private String comment;
    private int postId;
    private int userId;
}
