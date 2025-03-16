package com.taskforge.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "projects",
        uniqueConstraints = @UniqueConstraint(name = "project_name_unique", columnNames = {"name"})
)
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    // when a project is persisted, updated or deleted, all the associated tasks entities should also be
    // persisted, updated or deleted
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;
}
