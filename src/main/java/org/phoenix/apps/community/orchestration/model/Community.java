package org.phoenix.apps.community.orchestration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    private int communityId;
    private String name;
    private String description;
    private List<Group> communityGroups;
}
