package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
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
class RuleNameServiceImplTest {

    private static RuleNameService ruleNameService;

    @Mock
    private RuleNameRepository ruleNameRepositoryMock;


    @BeforeEach
    void setUp() {
        ruleNameService = new RuleNameServiceImpl(ruleNameRepositoryMock);
    }

    @Test
    void getAllRuleNames() {
        //GIVEN
        List<RuleName> ruleNames = new ArrayList<>();
        ruleNames.add(new RuleName("name1", "description1", "json1", "template1", "sql1", "sqlP1"));
        ruleNames.add(new RuleName("name2", "description2", "json2", "template2", "sql2", "sqlP2"));
        ruleNames.add(new RuleName("name3", "description3", "json3", "template3", "sql3", "sqlP3"));
        when(ruleNameRepositoryMock.findAll()).thenReturn(ruleNames);

        //WHEN
        List<RuleName> result = ruleNameService.getAllRuleNames();
        //THEN
        assertThat(result.get(1).getName()).isEqualTo("name2");
        verify(ruleNameRepositoryMock, times(1)).findAll();


    }

    @Test
    void getRuleNameById() {
        //GIVEN
        int id = 1;
        RuleName obj1 = new RuleName("name1", "description1", "json1", "template1", "sql1", "sqlP1");
        Optional<RuleName> opt = Optional.of(obj1);
        when(ruleNameRepositoryMock.findById(id)).thenReturn(opt);
        //WHEN
        RuleName result = null;
        try {
            result = ruleNameService.getRuleNameById(id);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
        //THEN
        assertThat(result.getName()).isEqualTo("name1");
        verify(ruleNameRepositoryMock).findById(id);

    }

    @Test
    void getRuleNameById_withException() throws DataNotFoundException{
        //GIVEN
        int idBidon = 99;
        //WHEN
        assertThrows(DataNotFoundException.class, () -> ruleNameService.getRuleNameById(idBidon));
    }

    @Test
    void saveRuleName() {

        //GIVEN
        RuleName toSave = new RuleName("name1", "description1", "json1", "template1", "sql1", "sqlP1");
        when(ruleNameRepositoryMock.save(toSave)).thenReturn(toSave);
        //WHEN
        RuleName result = ruleNameService.saveRuleName(toSave);
        //THEN
        assertThat(result.getName()).isEqualTo("name1");
        verify(ruleNameRepositoryMock).save(toSave);

    }

    @Test
    void deleteRuleName() {
        //GIVEN

        //WHEN
        ruleNameService.deleteRuleName(1);
        //THEN
        verify(ruleNameRepositoryMock).deleteById(1);

    }
}