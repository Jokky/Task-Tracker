package task.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.tracker.dto.RestTaskDto;
import task.tracker.dto.TaskDto;
import task.tracker.model.Task;
import task.tracker.model.User;
import task.tracker.service.TaskService;
import task.tracker.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class TaskRestController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/new-task", method = RequestMethod.POST, produces = "application/json")
    public Task receiveTaskForm(@RequestBody TaskDto taskDto) {
        User user = userService.getCurrentUser();
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setType(taskDto.getType().toString());
        task.setDescription(taskDto.getDescription());
        task.setUser(user);
        taskService.addTask(task);

        return task;
    }

    @RequestMapping(value = "/task-list", method = RequestMethod.GET, produces = "application/json")
    public List<RestTaskDto> getTaskList() {
        List<Task> taskList = taskService.getAllTasks();
        List<RestTaskDto> restTaskDtoList = new ArrayList<>();

        for (Task task : taskList) {
            RestTaskDto restTaskDto = new RestTaskDto();

            restTaskDto.setType(task.getType());
            restTaskDto.setTitle(task.getTitle());
            restTaskDto.setDescription(task.getDescription());
            restTaskDto.setCreatedDate(task.getCreatedDate());
            restTaskDto.setFinishedDate(task.getFinishedDate());
            restTaskDto.setUserId(task.getUser().getId());

            restTaskDtoList.add(restTaskDto);
        }
        return restTaskDtoList;
    }

    @RequestMapping(value = "/delete-task/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
    @RequestMapping(value = "/finish-task/{id}", method = RequestMethod.PUT)
    public void updateFinishedTask(@PathVariable Long id) {

        Task task = taskService.getTask(id);

        task.setFinishedDate(new Date());

        taskService.editTask(task);
    }


    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET, produces = "application/json")
    public RestTaskDto getTask(@PathVariable Long id) {

        Task task = taskService.getTask(id);

        RestTaskDto restTaskDto = new RestTaskDto();

        restTaskDto.setType(task.getType());
        restTaskDto.setTitle(task.getTitle());
        restTaskDto.setDescription(task.getDescription());
        restTaskDto.setCreatedDate(task.getCreatedDate());
        restTaskDto.setFinishedDate(task.getFinishedDate());
        restTaskDto.setUserId(task.getUser().getId());

        return restTaskDto;

    }
}