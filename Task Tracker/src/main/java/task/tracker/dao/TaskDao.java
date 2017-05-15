package task.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import task.tracker.model.Task;

public interface TaskDao extends JpaRepository<Task, Long> {
}
