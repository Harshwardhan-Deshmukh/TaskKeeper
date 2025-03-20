package com.taskforge.app.repositories;

import com.taskforge.app.entities.TaskEntity;
import com.taskforge.app.enums.Priority;
import com.taskforge.app.enums.Status;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByStatus(Status status, Pageable pageable);
    List<TaskEntity> findByPriority(Priority priority, Pageable pageable);
}
