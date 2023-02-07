package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

import java.util.List;

public interface BidListService  {

    public List<BidList> getAllBidLists();
    public BidList getBidListById(Integer  id);
    public BidList saveBid(BidList bidlist);
    public BidList updateBidList(BidList bidlist);
    public void deleteBidList(Integer  id);

}
