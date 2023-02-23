package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class TradeServiceImpl implements TradeService{
    //injection des dépendances par le constructor
    private TradeRepository tradeRepository;


    @Override
    public List<Trade> getAllTrades() {
        log.info("Service ---> find all Trade ");
        return tradeRepository.findAll();
    }

    @Override
    public Trade getTradeById(Integer id) throws DataNotFoundException {
        log.info("Service ---> find one Trade ");
        return tradeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Trade Id not found in database : " + id));
    }

    @Override
    public Trade saveTrade(Trade trade) {
        log.info("Service ---> save one Trade ");
        return tradeRepository.save(trade);
    }

    @Override
    public void deleteTrade(Integer id) {
        log.info("Service ---> delete one Trade ");
        tradeRepository.deleteById(id);
    }
}
