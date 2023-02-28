package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("name", name);
        System.out.println("username: " + auth.getName());
        List<Trade> trades = tradeService.getAllTrades();
        log.info("Controller ---> display all trades ");
        model.addAttribute("trades", trades);

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Model model, Trade trade) {

        log.info("Controller ---> display one trzde without data ");
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        String exists=null;
        if (!result.hasErrors()) {
            log.info("Controller ---> create one bidLists  ");

            tradeService.saveTrade(trade);
            model.addAttribute("trade", new Trade());
            model.addAttribute("trades", tradeService.getAllTrades());
            model.addAttribute("exists", exists);
            return "redirect:/trade/list";
        }
        log.error("Controller ---> error while creating one trade ");

        exists = "pbData";
        return "trade/add";
    }

    @GetMapping("/trade/update")
    public String showUpdateForm( Integer id, Model model) {
        log.info("Controller ---> display one trade already created ");
        String exists = null;
        Trade trade = null;
        try {
            trade = tradeService.getTradeById(id);
        } catch (DataNotFoundException e) {
            exists = "pbData";
            e.getMessage();
        }
        if(trade !=null) {
            model.addAttribute("trade",trade );
        }

        model.addAttribute("exists",exists );
        return "trade/update";
    }

    @PostMapping("/trade/update")
    public String updateTrade( @Valid Trade trade,
                             BindingResult result, Model model) {
      //  String exists=null;

        if (result.hasErrors()) {
            log.error("Controller ---> update one trade in error ");
            return "trade/update";
        }
        else{
            tradeService.saveTrade(trade);
            log.info("Controller ---> update one trade with success ");
            return "redirect:/trade/list";
        }

    }

    @GetMapping("/trade/delete")
    public String deleteTrade( Integer id) {
        tradeService.deleteTrade(id);
        log.info("Controller ---> remove one trade ");
        return "redirect:/trade/list";
    }
}
