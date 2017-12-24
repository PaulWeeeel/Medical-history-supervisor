package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.Stock;
import com.hwwz.medicalhistorysupervisor.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 13:30
 */
@Controller
@RequestMapping(value = "/stock")
@Authorization//该类方法都需登录
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("stockList", stockService.getAllStocks());
        return "stock";
    }

    @PostMapping(value = "/add")
    public String add(@Valid Stock stock) {
        stockService.add(stock);
        return "redirect:stock";
    }

    @PutMapping(value = "/edit")
    public String edit(@Valid Stock stock) {
        stockService.update(stock);
        return "redirect:stock";
    }


    @DeleteMapping(value = "/delete")
    public String delete(@RequestParam("id") Integer id) {
        stockService.delete(id);
        return "redirect:stock";
    }
}
