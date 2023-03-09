package com.nnk.springboot.controllers;

import com.nnk.springboot.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@AllArgsConstructor
//@RequestMapping("app")
public class LoginController {


    private UserService userService;

    @GetMapping("loginWithUserPwd")
    public ModelAndView loginWithUserPwd() {
        log.info("Display login page");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("loginWithUserPwd");
        return mav;
    }

    @GetMapping("login")
    public ModelAndView login() {
        log.info("Display login Oauth2 page");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }



    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }


    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        log.info("Display logout page ");
        return "redirect:/login?logout";
    }

}
