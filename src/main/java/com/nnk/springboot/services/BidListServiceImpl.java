package com.nnk.springboot.services;

import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
public class BidListServiceImpl implements BidListService{
    //injection des dépendances par le constructor
    private BidListRepository bidListRepository;


    @Override
    public List<BidList> getAllBidLists() {
        log.info("Service ---> find all bidLists ");
        return bidListRepository.findAll();
    }

    @Override
    public BidList getBidListById(Integer id) throws DataNotFoundException {
        log.info("Service ---> find one bidList ");
        return bidListRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("BidList Id not found in database : " + id));
    }

    @Override
    public BidList saveBid(BidList bidlist) {
        log.info("Service ---> save one bidList ");
        return bidListRepository.save(bidlist);
    }

    @Override
    public void deleteBidList(Integer id) {
        log.info("Service ---> delete one bidList ");
        bidListRepository.deleteById(id);
    }
}
