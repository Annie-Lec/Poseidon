package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
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
class CurvePointServiceTest {

    private static CurvePointService curvePointService;

    @Mock
    private CurvePointRepository curvePointRepositoryMock;

    @BeforeEach
    void setUp() {
        curvePointService = new CurvePointServiceImpl(curvePointRepositoryMock);
    }

    @Test
    void getAllCurvePoints() {
        //GIVEN
        List<CurvePoint> curvePoints = new ArrayList<>();
        curvePoints.add(new CurvePoint(1, new BigDecimal(1), new BigDecimal("10.2")));
        curvePoints.add(new CurvePoint(2, new BigDecimal(2), new BigDecimal("10.2")));
        curvePoints.add(new CurvePoint(3, new BigDecimal(3), new BigDecimal("25.2")));
        when(curvePointRepositoryMock.findAll()).thenReturn(curvePoints);

        //WHEN
       List<CurvePoint> result = curvePointService.getAllCurvePoints();
        //THEN
       assertThat(result.get(1).getCurveId()).isEqualTo(2);
       verify(curvePointRepositoryMock, times(1)).findAll();
    }

    @Test
    void getCurvePointById() {

        //GIVEN
        int id = 1;
        CurvePoint obj1 = new CurvePoint(1, new BigDecimal(1), new BigDecimal("10.2"));
        Optional<CurvePoint> opt = Optional.of(obj1);
        when(curvePointRepositoryMock.findById(id)).thenReturn(opt);
        //WHEN
        CurvePoint result = null;
        try {
            result = curvePointService.getCurvePointById(id);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
        //THEN
        assertThat(result.getCurveId()).isEqualTo(1);
        verify(curvePointRepositoryMock).findById(id);

    }

    @Test
    void getCurvePointById_withException() throws DataNotFoundException{
        //GIVEN
        int idBidon = 99;
        //WHEN
        assertThrows(DataNotFoundException.class, () -> curvePointService.getCurvePointById(idBidon));
    }


    @Test
    void saveCurvePoint() {

        //GIVEN
        CurvePoint toSave = new CurvePoint(1, new BigDecimal(1), new BigDecimal("10.2"));
        when(curvePointRepositoryMock.save(toSave)).thenReturn(toSave);
        //WHEN
        CurvePoint result = curvePointService.saveCurvePoint(toSave);
        //THEN
        assertThat(result.getValue()).isEqualTo(new BigDecimal("10.2"));
        verify(curvePointRepositoryMock).save(toSave);
    }

    @Test
    void deleteCurvePoint()  {
        //GIVEN

        //WHEN
        curvePointService.deleteCurvePoint(1);
        //THEN
        verify(curvePointRepositoryMock).deleteById(1);

    }
}