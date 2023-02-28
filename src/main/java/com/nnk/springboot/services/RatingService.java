package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.DataNotFoundException;

import java.util.List;

public interface RatingService {

    List<Rating> getAllRatings();
    Rating getRatingById(Integer  id) throws DataNotFoundException;
    Rating saveRating(Rating rating);
    void deleteRating(Integer  id);

}
