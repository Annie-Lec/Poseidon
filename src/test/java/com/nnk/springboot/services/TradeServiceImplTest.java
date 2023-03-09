package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

    private static TradeService tradeService;

    @Mock
    private TradeRepository tradeRepositoryMock;

    @BeforeEach
    void setUp() {
        tradeService = new TradeServiceImpl(tradeRepositoryMock);
    }

    @Test
    void getAllTrades() {
        //GIVEN
        List<Trade> trades = new ArrayList<>();
        trades.add(new Trade("account1", "type1", new BigDecimal(11.1)));
        trades.add(new Trade("account2", "type2", new BigDecimal(22)));
        trades.add(new Trade("account3", "type3", new BigDecimal(333)));
        when(tradeRepositoryMock.findAll()).thenReturn(trades);
        //WHEN
        List<Trade> result = tradeService.getAllTrades();
        //THEN
        assertThat(result.get(1).getAccount()).isEqualTo("account2");
        verify(tradeRepositoryMock, times(1)).findAll();


    }

    @Test
    void getTradeById() {

        //GIVEN
        int id = 1;
        Trade obj1 = new Trade("account1", "type1", new BigDecimal(11.1));
        Optional<Trade> opt = Optional.of(obj1);
        when(tradeRepositoryMock.findById(id)).thenReturn(opt);
        //WHEN
        Trade result = null;
        try {
            result = tradeService.getTradeById(id);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
        //THEN
        assertThat(result.getAccount()).isEqualTo("account1");
        verify(tradeRepositoryMock).findById(id);

    }

    @Test
    void getTradeById_withException() throws DataNotFoundException{
        //GIVEN
        int idBidon = 99;
        //WHEN
        assertThrows(DataNotFoundException.class, () -> tradeService.getTradeById(idBidon));
    }


    @Test
    void saveTrade() {
        //GIVEN
        Trade toSave = new Trade("account1", "type1", new BigDecimal(11.1));
        when(tradeRepositoryMock.save(toSave)).thenReturn(toSave);
        //WHEN
        Trade result = tradeService.saveTrade(toSave);
        //THEN
        assertThat(result.getBuyQuantity()).isEqualTo(new BigDecimal(11.1));
        verify(tradeRepositoryMock).save(toSave);

    }

    @Test
    void deleteTrade() {
        //GIVEN

        //WHEN
        tradeService.deleteTrade(1);
        //THEN
        verify(tradeRepositoryMock).deleteById(1);

    }
}