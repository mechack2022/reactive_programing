package com.fragile.reactive_programing.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "task_lock")
@Data
public class TaskLock {
    @Id
    @Column(name = "task_id", length = 64, nullable = false)
    private String taskId;

    @Column(name = "last_execution", nullable = false, columnDefinition = "bigint DEFAULT 0")
    private Long lastExecution;
    @Column(nullable = false, columnDefinition = "bigint DEFAULT 0")
    private long deactivatedUntil;

}

