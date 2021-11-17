package ru.kozhaev.appspringboot.service;

import ru.kozhaev.appspringboot.entity.User;

public interface UserService {
    void save(User user);

    void update(Long id, User user);
}
