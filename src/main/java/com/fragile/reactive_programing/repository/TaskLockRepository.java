package com.fragile.reactive_programing.repository;

import com.fragile.reactive_programing.entities.TaskLock;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

public interface TaskLockRepository extends JpaRepository<TaskLock, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "1000")
    })
//    Optional<TaskLock> findByTaskIdAndLastExecutionLessThan(String taskId, long timestamp);
    Optional<TaskLock> findByTaskIdAndLastExecutionLessThanAndDeactivatedUntilLessThan(
            String taskId, long lastExecution, long deactivatedUntil
    );

}
