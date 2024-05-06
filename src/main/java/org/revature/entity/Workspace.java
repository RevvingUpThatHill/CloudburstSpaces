package org.revature.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Workspace {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;
    public long userId;
    public String lab;
    public String ip;
    public int port;
    public String containerName;
    public Timestamp openDate;
    public Timestamp lastActivity;
    public Timestamp lastOverwrite;
    public Timestamp closeDate;
    public boolean active;
    public Workspace(long userId, String lab, String ip, int port, String containerName){
        this.userId = userId;
        this.lab = lab;
        this.ip = ip;
        this.port = port;
        this.containerName = containerName;
    }
}
