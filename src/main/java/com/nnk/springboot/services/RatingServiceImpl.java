package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class RatingServiceImpl implements RatingService{
    //injection des dépendances par le constructor
    private RatingRepository ratingRepository;


    @Override
    public List<Rating> getAllRatings() {
        log.info("Service ---> find all Rating ");
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(Integer id) throws DataNotFoundException {
        log.info("Service ---> find one Rating ");
        return ratingRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Rating Id not found in database : " + id));
    }

    @Override
    public Rating saveRating(Rating rating) {
        log.info("Service ---> save one Rating ");
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(Integer id) {
        log.info("Service ---> delete one Rating ");
        ratingRepository.deleteById(id);
    }
}
