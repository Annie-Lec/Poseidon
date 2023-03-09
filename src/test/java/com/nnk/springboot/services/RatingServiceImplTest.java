package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest {

    private static RatingService ratingService;

    @Mock
    private RatingRepository ratingRepositoryMock;

    @BeforeEach
    void setUp() {
        ratingService = new RatingServiceImpl(ratingRepositoryMock);
    }

    @Test
    void getAllRatings() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating("moodysRating1","sandPRating1","fitchRating1",1));
        ratings.add(new Rating("moodysRating2","sandPRating2","fitchRating2",2));
        ratings.add(new Rating("moodysRating3","sandPRating3","fitchRating3",3));
        when(ratingRepositoryMock.findAll()).thenReturn(ratings);

        //WHEN
        List<Rating> result = ratingService.getAllRatings();
        //THEN
        assertThat(result.get(1).getOrderNumber()).isEqualTo(2);
        verify(ratingRepositoryMock, times(1)).findAll();

    }

    @Test
    void getRatingById() {

        //GIVEN
        int id = 1;
        Rating obj1 = new Rating("moodysRating1","sandPRating1","fitchRating1",1);
        Optional<Rating> opt = Optional.of(obj1);
        when(ratingRepositoryMock.findById(id)).thenReturn(opt);
        //WHEN
        Rating result = null;
        try {
            result = ratingService.getRatingById(id);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
        //THEN
        assertThat(result.getOrderNumber()).isEqualTo(1);
        verify(ratingRepositoryMock).findById(id);
    }


    @Test
    void getRatingById_withException() throws DataNotFoundException{
        //GIVEN
        int idBidon = 99;
        //WHEN
        assertThrows(DataNotFoundException.class, () -> ratingService.getRatingById(idBidon));
    }


    @Test
    void saveRating() {
        //GIVEN
        Rating toSave = new Rating("moodysRating1","sandPRating1","fitchRating1",1);
        when(ratingRepositoryMock.save(toSave)).thenReturn(toSave);
        //WHEN
        Rating result = ratingService.saveRating(toSave);
        //THEN
        assertThat(result.getMoodysRating().equals("moodysRating1"));
        verify(ratingRepositoryMock).save(toSave);


    }

    @Test
    void deleteRating() {
        //GIVEN

        //WHEN
        ratingService.deleteRating(1);
        //THEN
        verify(ratingRepositoryMock).deleteById(1);

    }
}