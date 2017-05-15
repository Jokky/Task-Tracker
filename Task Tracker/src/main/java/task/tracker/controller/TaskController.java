package task.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import task.tracker.dto.TaskDto;
import task.tracker.model.Task;
import task.tracker.model.User;
import task.tracker.service.TaskService;
import task.tracker.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitry Yakushev on 15.05.2017.
 */
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/new-task", method = RequestMethod.GET)
    public String getTaskForm(Model model) {
        TaskDto taskDto = new TaskDto();
        List<Task.Type> options = new ArrayList<>();

        for (Task.Type type : Task.Type.values()) {
            options.add(type);
        }

        model.addAttribute("options", options);

        model.addAttribute(taskDto);
        return "task-form";

    }
    @RequestMapping(value = "/new-task", method = RequestMethod.POST)
    public String receiveTaskForm(@ModelAttribute("taskDto") TaskDto taskDto) {
        User user = userService.getCurrentUser();
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setType(taskDto.getType().toString());
        task.setDescription(taskDto.getDescription());
        task.setUser(user);
        taskService.addTask(task);

        return "redirect:/task-list";
    }

    @RequestMapping(value = {"/task-list", "/"}, method = RequestMethod.GET)
    public String getTaskList(Model model) {

        User user = userService.getCurrentUser();

        if (user == null) {
            return "forward:/login";
        }

        List<Task> taskList = taskService.getAllTasks();

        model.addAttribute("taskList", taskList);
        return "task-list";

    }

    @RequestMapping(value = "/delete-task/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return "redirect:/task-list";
    }
    @RequestMapping(value = "/finish-task/{id}", method = RequestMethod.GET)
    public String updateFinishedTask(@PathVariable Long id) {

        Task task  = taskService.getTask(id);

        task.setFinishedDate(new Date());

        taskService.editTask(task);
        return "redirect:/task-list";
    }


    @RequestMapping(value = "/task/{id}", method =  RequestMethod.GET)
    public String getTask(@PathVariable Long id, Model model) {
        Task task = taskService.getTask(id);

        model.addAttribute("task", task);
        return "task-view";
    }
}

