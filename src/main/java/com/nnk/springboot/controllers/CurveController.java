package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.services.CurvePointService;
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
@AllArgsConstructor
@Slf4j
public class CurveController {
    private CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("name", name);

        List<CurvePoint> curveList = curvePointService.getAllCurvePoints();
        log.info("Controller ---> display all CurvePoint ");
        model.addAttribute("curveList", curveList);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model, CurvePoint bid) {
        log.info("Controller ---> display one curvepoint without data ");
        model.addAttribute("curvePoint", new CurvePoint());

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        String exists=null;
        if (!result.hasErrors()) {
            log.info("Controller ---> create one curvePoint  ");

            curvePointService.saveCurvePoint(curvePoint);
            model.addAttribute("curvePoint", new CurvePoint());
            model.addAttribute("curveList", curvePointService.getAllCurvePoints());
            model.addAttribute("exists", exists);
            return "redirect:/curvePoint/list";
        }
        log.error("Controller ---> error while creating one curvePoint ");

        exists = "pbData";
        model.addAttribute("exists", exists);
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update")
    public String showUpdateForm( Integer id, Model model) {
        log.info("Controller ---> display one curvePoint already created ");
        String exists = null;
        CurvePoint curvePoint = null;
        try {
            curvePoint = curvePointService.getCurvePointById(id);
        } catch (DataNotFoundException e) {
            exists = "pbData";
            log.error("Controller ---> "+e.getMessage());
        }
        if(curvePoint !=null) {
            model.addAttribute("curvePoint",curvePoint );
        }

        model.addAttribute("exists",exists );
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update")
    public String update( @Valid CurvePoint curvePoint, BindingResult result, Model model) {
        String exists=null;

        if (result.hasErrors()) {
            log.error("Controller ---> update one curvePoint in error ");
            exists = "pbData";
            model.addAttribute("exists", exists);
            return "curvePoint/update";
        }
        else{
            curvePointService.saveCurvePoint(curvePoint);
            log.info("Controller ---> final update one curvePoint ");
            return "redirect:/curvePoint/list";
        }

    }

    @GetMapping("/curvePoint/delete")
    public String deleteBid( Integer id) {
        curvePointService.deleteCurvePoint(id);
        log.info("Controller ---> remove one curvePoint ");
        return "redirect:/curvePoint/list";
    }
}
