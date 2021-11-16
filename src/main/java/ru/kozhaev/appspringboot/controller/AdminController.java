package ru.kozhaev.appspringboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kozhaev.appspringboot.entity.User;
import ru.kozhaev.appspringboot.repository.RoleRepository;
import ru.kozhaev.appspringboot.repository.UserRepository;
import ru.kozhaev.appspringboot.service.UserService;

@Controller
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/index";
    }

    @GetMapping("/users/new")
    public String newUser(Model model, User user) {
        model.addAttribute("roles", roleRepository.findAll());
        return "users/new";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findFirstById(id));
        return "users/show";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findFirstById(id));
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("userRoles", userRepository.findFirstById(id).getRoles());
        return "users/edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@PathVariable("id") long id,
                         @ModelAttribute("user") User user) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }

}
