package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.AppUser;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    @RequestMapping("/user/list")
    public String home(Model model)
    {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("name", name);
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(Model model, AppUser user) {
        log.info("Controller ---> display one user without data ");
        model.addAttribute("user", user);
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid AppUser appUser, BindingResult result, Model model) {
       //
        String exists=null;
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            appUser.setPassword(encoder.encode(appUser.getPassword()));
            userService.saveUser(appUser);
            model.addAttribute("appUser", new AppUser());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("exists", exists);
            return "redirect:/user/list";
        }
        log.error("Controller ---> error while creating one user ");
        exists = "pbData";
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model)  {
        AppUser user = userService.getUserById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        log.info("display form to update user");
        return "user/update";


    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid AppUser user,
                             BindingResult result, Model model) throws DataNotFoundException {
        if (result.hasErrors()) {
            log.error("User - impossible to update : validation error data");

            return "redirect:/user/update/{id}";
        }
     //   model.addAttribute("user", user);

    //    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      //  user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.updateUser(id, user);
        model.addAttribute("users", userService.getAllUsers());

        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
       // AppUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.deleteUser(id);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/user/list";
    }
}
