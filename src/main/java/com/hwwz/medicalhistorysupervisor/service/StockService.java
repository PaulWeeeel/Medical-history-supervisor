package com.hwwz.medicalhistorysupervisor.service;

import com.hwwz.medicalhistorysupervisor.domain.Stock;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/12/012 0:18
 */
public interface StockService {
    public void add(Stock stock);
    public void update(Stock stock);
    public void delete(Integer id);
    public List<Stock> getAllStocks();
    public Stock getById(Integer id);
    Stock getByName(String medicine);
}
