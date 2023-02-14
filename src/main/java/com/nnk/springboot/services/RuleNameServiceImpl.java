package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Log4j2
@AllArgsConstructor
public class RuleNameServiceImpl implements RuleNameService {
    //injection des dépendances par le constructor
    private RuleNameRepository ruleNameRepository;


    @Override
    public List<RuleName> getAllRuleNames() {
        log.info("Service ---> find all RuleName ");
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName getRuleNameById(Integer id) throws DataNotFoundException {
        log.info("Service ---> find one RuleName ");
        return ruleNameRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("RuleName Id not found in database : " + id));
    }

    @Override
    public RuleName saveRuleName(RuleName ruleName) {
        log.info("Service ---> save one RuleName ");
        return ruleNameRepository.save(ruleName);
    }

    @Override
    public void deleteRuleName(Integer id) {
        log.info("Service ---> delete one RuleName ");
        ruleNameRepository.deleteById(id);
    }
}
