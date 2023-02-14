package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.DataNotFoundException;

import java.util.List;

public interface CurvePointService {

    List<CurvePoint> getAllCurvePoints();
    CurvePoint getCurvePointById(Integer  id) throws DataNotFoundException;
    CurvePoint saveCurvePoint(CurvePoint curvePoint);
    void deleteCurvePoint(Integer  id);

}
