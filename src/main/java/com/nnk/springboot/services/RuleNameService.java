package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.DataNotFoundException;

import java.util.List;

public interface RuleNameService {

    List<RuleName> getAllRuleNames();
    RuleName getRuleNameById(Integer  id) throws DataNotFoundException;
    RuleName saveRuleName(RuleName ruleName);
    void deleteRuleName(Integer  id);

}
