package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.Stock;
import com.hwwz.medicalhistorysupervisor.repository.StockRepository;
import com.hwwz.medicalhistorysupervisor.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/12/012 0:24
 */
@Service("StockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public void add(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public void update(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public void delete(Integer id) {
        stockRepository.delete(id);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getById(Integer id) {
        return stockRepository.getOne(id);
    }
}
