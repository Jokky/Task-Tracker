package task.tracker.dto;

import task.tracker.model.Task;

/**
 * Created by Dmitry Yakushev on 15.05.2017.
 */
public class TaskDto {

    private Task.Type type;
    private String title;
    private String description;

    public Task.Type getType() {
        return type;
    }

    public void setType(Task.Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
