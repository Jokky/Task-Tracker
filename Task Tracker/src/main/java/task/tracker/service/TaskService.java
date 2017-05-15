package task.tracker.service;

import task.tracker.model.Task;

import java.util.List;

public interface TaskService {
    Task getTask(Long id);

    void editTask(Task task);

    Task addTask(Task task);

    void deleteTask(Long id);

    List<Task> getAllTasks();
}
