package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BidListServiceTest {

    private static BidListService bidListService;

    @Mock
    private BidListRepository bidListRepositoryMock;

    @BeforeEach
    void setUp() {
        bidListService = new BidListServiceImpl(bidListRepositoryMock);
    }

    @Test
    void getAllBidLists() {
        //GIVEN
        List<BidList> bidLists = new ArrayList<>();
        bidLists.add(new BidList("account1", "Type Acccount1", new BigDecimal(14.2)));
        bidLists.add(new BidList("account2", "Type Acccount2", new BigDecimal(10.2)));
        bidLists.add(new BidList("account3", "Type Acccount3", new BigDecimal(25.2)));
        when(bidListRepositoryMock.findAll()).thenReturn(bidLists);

        //WHEN
       List<BidList> result = bidListService.getAllBidLists();
        //THEN
       assertThat(result.get(1).getAccount()).isEqualTo("account2");
       verify(bidListRepositoryMock, times(1)).findAll();
    }

    @Test
    void getBidListById() {

        //GIVEN
        int id = 1;
        BidList bid1 = new BidList("account1", "Type Acccount1", new BigDecimal(14.2));
        Optional<BidList> optipnalBid = Optional.of(bid1);
        when(bidListRepositoryMock.findById(id)).thenReturn(optipnalBid);
        //WHEN
        BidList result = null;
        try {
            result = bidListService.getBidListById(id);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
        //THEN
        assertThat(result.getAccount()).isEqualTo("account1");
        verify(bidListRepositoryMock).findById(id);

    }

    @Test
    void getBidListById_withException() throws DataNotFoundException{
        //GIVEN
        int idBidon = 99;
        //WHEN
        assertThrows(DataNotFoundException.class, () -> bidListService.getBidListById(idBidon));
    }


    @Test
    void saveBid() {

        //GIVEN
        BidList bidToSave = new BidList("account1", "Type Acccount1", new BigDecimal(14.2));
        when(bidListRepositoryMock.save(bidToSave)).thenReturn(bidToSave);
        //WHEN
        BidList result = bidListService.saveBid(bidToSave);
        //THEN
        assertThat(result.getAccount()).isEqualTo("account1");
        verify(bidListRepositoryMock).save(bidToSave);
    }

    @Test
    void deleteBidList() {
        //GIVEN
        List<BidList> bidLists = new ArrayList<>();
        bidLists.add(new BidList("account1", "Type Acccount1", new BigDecimal(14.2)));
        bidLists.add(new BidList("account2", "Type Acccount2", new BigDecimal(10.2)));
        bidLists.add(new BidList("account3", "Type Acccount3", new BigDecimal(25.2)));
        //WHEN
        bidListService.deleteBidList(1);
        //THEN
        verify(bidListRepositoryMock).deleteById(1);

    }
}