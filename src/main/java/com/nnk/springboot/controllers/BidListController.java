package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {
    private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String homeBid(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("name", name);
        System.out.println("username: " + auth.getName());
        List<BidList> bidLists = bidListService.getAllBidLists();
        log.info("Controller ---> display all bidLists ");
        model.addAttribute("bidLists", bidLists);

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model, BidList bid) {
        log.info("Controller ---> display one bidLists without data ");
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        //
        String exists=null;
        if (!result.hasErrors()) {
            log.info("Controller ---> create one bidLists  ");

            bidListService.saveBid(bid);
            model.addAttribute("bidList", new BidList());
            model.addAttribute("bidLists", bidListService.getAllBidLists());
            model.addAttribute("exists", exists);
            return "redirect:/bidList/list";
        }
        log.error("Controller ---> error while creating one bidLists ");

        exists = "pbData";
        return "bidList/add";
    }

    @GetMapping("/bidList/update")
    public String showUpdateForm(Integer id, Model model) {
        log.info("Controller ---> display one bidLists already created ");
        String exists = null;
        BidList bidList = null;
        try {
            bidList = bidListService.getBidListById(id);
        } catch (DataNotFoundException e) {
            exists = "pbData";
            e.getMessage();
        }
        if(bidList !=null) {
            model.addAttribute("bidList",bidList );
        }

        model.addAttribute("exists",exists );

        return "bidList/update";
    }

    @PostMapping("/bidList/update")
    public String update(@Valid BidList bidList,
                             BindingResult result, Model model)  {
        // TODO: check required fields, if valid call service to update Bid and return list Bid

        String exists=null;

        if (result.hasErrors()) {
            return "bidList/update";
        }
        else{
            bidListService.saveBid(bidList);
            return "redirect:/bidList/list";
        }
/*
        if (!result.hasErrors()) {
            log.info("Controller ---> update one bidList ");

            bidListService.saveBid(bidList);
            model.addAttribute("bidList", bidListService.getBidListById(id));
            model.addAttribute("bidLists", bidListService.getAllBidLists());
            model.addAttribute("exists", exists);
            return "redirect:/bidList/list";
        } else {
            log.error("BidList - impossible to update : validation error data");
            exists = "pbData";
            return "bidList/update";
        }
        */

    }

    @GetMapping("/bidList/delete")
    public String delete(Integer id) {

        bidListService.deleteBidList(id);
        log.info("Controller ---> remove one bidList ");

        return "redirect:/bidList/list";
    }
}
