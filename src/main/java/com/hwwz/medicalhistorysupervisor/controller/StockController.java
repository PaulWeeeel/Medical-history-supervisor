package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.domain.Stock;
import com.hwwz.medicalhistorysupervisor.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 13:30
 */
@RestController
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping(value = "/list")
    public List<Stock> list() {
        return stockService.getAllStocks();
    }

    @PostMapping(value = "/add")
    public List<Stock> add(@Valid Stock stock) {
        stockService.add(stock);
        return list();
    }

    @PutMapping(value = "/edit")
    public List<Stock> edit(@Valid Stock stock) {
        stockService.update(stock);
        return list();
    }


    @DeleteMapping(value = "/delete")
    public List<Stock> delete(@RequestParam("id") Integer id) {
        stockService.delete(id);
        return list();
    }
}
