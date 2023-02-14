package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.DataNotFoundException;

import java.util.List;

public interface TradeService {

    List<Trade> getAllTrades();
    Trade getTradeById(Integer  id) throws DataNotFoundException;
    Trade saveTrade(Trade trade);
    void deleteTrade(Integer  id);

}
