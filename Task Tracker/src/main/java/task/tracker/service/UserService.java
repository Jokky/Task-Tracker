package task.tracker.service;

import task.tracker.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    User getCurrentUser();
}
