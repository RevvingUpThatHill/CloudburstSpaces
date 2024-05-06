package org.revature.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class WorkspaceData {
    private String id;
    public long userId;
    public String lab;
    public String url;
    public String containerName;
    public Timestamp openDate;
    public Timestamp lastActivity;
    public Timestamp lastOverwrite;
    public Timestamp closeDate;
    public boolean active;
}
