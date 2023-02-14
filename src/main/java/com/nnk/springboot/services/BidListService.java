package com.nnk.springboot.services;

import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.domain.BidList;

import java.util.List;

public interface BidListService  {

    List<BidList> getAllBidLists();
    BidList getBidListById(Integer  id) throws DataNotFoundException;
    BidList saveBid(BidList bidlist);
    void deleteBidList(Integer  id);

}
