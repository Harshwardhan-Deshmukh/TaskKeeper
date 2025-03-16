package com.taskforge.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "teams",
        uniqueConstraints = @UniqueConstraint(name = "team_name_unique", columnNames = {"name"})
)
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> tasks;

    @OneToMany(mappedBy = "team")
    private List<UserEntity> users;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TeamEntity teamEntity = (TeamEntity) o;
        return Objects.equals(getId(), teamEntity.getId()) && Objects.equals(getName(), teamEntity.getName()) && Objects.equals(getDescription(), teamEntity.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }
}
