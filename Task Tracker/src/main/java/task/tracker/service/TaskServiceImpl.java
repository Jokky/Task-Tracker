package task.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.tracker.dao.TaskDao;
import task.tracker.model.Task;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public Task getTask(Long id) {
        return taskDao.getOne(id);
    }

    @Override
    public void editTask(Task task) {
        taskDao.saveAndFlush(task);
    }

    @Override
    public Task addTask(Task task) {
        return taskDao.saveAndFlush(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskDao.delete(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDao.findAll();
    }
}
