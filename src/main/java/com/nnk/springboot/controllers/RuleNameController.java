package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.services.RuleNameService;
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
public class RuleNameController {
    private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("name", name);
        System.out.println("username: " + auth.getName());
        List<RuleName> ruleNames = ruleNameService.getAllRuleNames();
        log.info("Controller ---> display all ruleNames ");
        model.addAttribute("ruleNames", ruleNames);

        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model, RuleName ruleName) {
        log.info("Controller ---> display one rulename without data ");
        model.addAttribute("ruleName", new RuleName());

        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        String exists=null;
        if (!result.hasErrors()) {
            log.info("Controller ---> create one bidLists  ");

            ruleNameService.saveRuleName(ruleName);
            model.addAttribute("ruleName", new RuleName());
            model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
            model.addAttribute("exists", exists);
            return "redirect:/ruleName/list";
        }
        log.error("Controller ---> error while creating one ruleName ");

        exists = "pbData";
        model.addAttribute("exists", exists);

        return "ruleName/add";
    }

    @GetMapping("/ruleName/update")
    public String showUpdateForm( Integer id, Model model) {
        log.info("Controller ---> display one rulname already created ");
        String exists = null;
        RuleName ruleName = null;
        try {
            ruleName = ruleNameService.getRuleNameById(id);
        } catch (DataNotFoundException e) {
            log.error("Controller ---> error while open update ");
            exists = "pbData";
            log.error("Controller ---> "+e.getMessage());
        }
        if(ruleName !=null) {
            model.addAttribute("ruleName",ruleName );
        }

        model.addAttribute("exists",exists );

        return "ruleName/update";
    }

    @PostMapping("/ruleName/update")
    public String update(@Valid RuleName ruleName,
                             BindingResult result, Model model) {
        String exists=null;

        if (result.hasErrors()) {
            log.error("Controller ---> update one ruleName in error ");
            exists = "pbData";
            model.addAttribute("exists", exists);
            return "bidList/update";
        }
        else{
            ruleNameService.saveRuleName(ruleName);
            log.info("Controller ---> update one ruleName with success ");
            return "redirect:/ruleName/list";
        }

    }

    @GetMapping("/ruleName/delete")
    public String deleteRuleName(Integer id) {
        ruleNameService.deleteRuleName(id);
        log.info("Controller ---> remove one rule ");

        return "redirect:/ruleName/list";
    }
}
