package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CurvePointServiceImpl implements CurvePointService{
    //injection des dépendances par le constructor
    private CurvePointRepository curvePointRepository;


    @Override
    public List<CurvePoint> getAllCurvePoints() {
        log.info("Service ---> find all CurvePoints ");
        return curvePointRepository.findAll();
    }

    @Override
    public CurvePoint getCurvePointById(Integer id) throws DataNotFoundException {
        log.info("Service ---> find one CurvePoint ");
        return curvePointRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("CurvePoint Id not found in database : " + id));
    }

    @Override
    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        log.info("Service ---> save one CurvePoint ");
        return curvePointRepository.save(curvePoint);
    }

    @Override
    public void deleteCurvePoint(Integer id) {
        log.info("Service ---> delete one CurvePoint ");
        curvePointRepository.deleteById(id);
    }
}
