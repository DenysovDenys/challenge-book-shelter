package com.example.automarket.controller;

import com.example.automarket.entity.Role;
import com.example.automarket.entity.User;
import com.example.automarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable String user, Model model) {

        User userFromDb =  userRepository.findById(Integer.parseInt(user));

        model.addAttribute("user", userFromDb);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping
    public String userSave(@RequestParam String username, @RequestParam Map<String, String> form,
                           @RequestParam String userId) {

        User userFromDb =  userRepository.findById(Integer.parseInt(userId));
        userFromDb.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        userFromDb.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                userFromDb.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(userFromDb);
        return "redirect:/user";
    }
}
