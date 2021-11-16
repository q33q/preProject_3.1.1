package ru.kozhaev.appspringboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kozhaev.appspringboot.repository.UserRepository;

import java.security.Principal;

@Controller
@AllArgsConstructor
@PreAuthorize("hasRole('USER')")
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    @RequestMapping()
    public String userPage(Principal principal, Model model) {
        model.addAttribute("user", userRepository.findFirstByFirstName(principal.getName()));
        return "users/show";
    }

}
