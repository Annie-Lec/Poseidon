package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.services.RatingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class RatingController {
    private RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("name", name);
        System.out.println("username: " + auth.getName());
        List<Rating> ratings = ratingService.getAllRatings();
        log.info("Controller ---> display all ratings ");
        model.addAttribute("ratings", ratings);

        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model, Rating rating) {
        log.info("Controller ---> display one rating without data ");
        model.addAttribute("rating", new Rating());
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        String exists=null;
        if (!result.hasErrors()) {
            log.info("Controller ---> create one rating with success  ");

            ratingService.saveRating(rating);
            model.addAttribute("rating", new Rating());
            model.addAttribute("ratings", ratingService.getAllRatings());
            model.addAttribute("exists", exists);
            return "redirect:/rating/list";
        }
        log.error("Controller ---> error while creating one rating ");

        exists = "pbData";
        return "rating/add";
    }

    @GetMapping("/rating/update")
    public String showUpdateForm( Integer id, Model model) {
        log.info("Controller ---> display one rating already created ");
        String exists = null;
        Rating rating = null;
        try {
            rating = ratingService.getRatingById(id);
        } catch (DataNotFoundException e) {
            exists = "pbData";
            e.getMessage();
        }
        if(rating !=null) {
            model.addAttribute("rating",rating );
        }

        model.addAttribute("exists",exists );
        return "rating/update";
    }

    @PostMapping("/rating/update")
    public String updateRating( @Valid Rating rating, BindingResult result, Model model) {
        String exists=null;

        if (result.hasErrors()) {
            log.error("Controller ---> update one rating in error ");
            return "rating/update";
        }
        else{
            ratingService.saveRating(rating);
            log.info("Controller ---> update one rating with success ");
            return "redirect:/rating/list";
        }
    }

    @GetMapping("/rating/delete")
    public String deleteRating( Integer id) {
        ratingService.deleteRating(id);
        log.info("Controller ---> remove one rating ");
        return "redirect:/rating/list";
    }
}
