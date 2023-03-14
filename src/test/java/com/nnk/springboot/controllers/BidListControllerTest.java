package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BidListControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @Autowired
    public BidListRepository bidListRepository;

    @Test
    @WithMockUser("user")
    void homeBid() throws Exception {
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(content().string(containsString("Bid List")));
    }

    @Test
    @WithMockUser("user")
    void addBidForm() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"))
                .andExpect(content().string(containsString("Add New Bid")));
    }

    @Test
    @WithMockUser("user")
    void validate() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                        .contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
                        .param("account", "account2")
                        .param("type", "type2")
                        .param("bidQuantity", "2.2")
                        .with(csrf()))
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser("user")
    void showUpdateForm() throws Exception {

        BidList bid = bidListRepository.save(new BidList("accountN", "type N",new BigDecimal("10")));
        mockMvc.perform(get("/bidList/update?id=" + bid.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"))
                .andExpect(content().string(containsString("Update Bid")));
    }

    @Test
    @WithMockUser("user")
    void update_HasError() throws Exception {
        BidList bid = bidListRepository.save(new BidList("accountN", "type N",new BigDecimal("10")));
        mockMvc.perform(post("/bidList/update?id=" + bid.getId())
                .contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
                .param("account", "")
                .param("type", "")
                .param("bidQuantity", "")
                .with(csrf()))
		        .andExpect(view().name("bidList/update"))
                .andExpect(content().string(containsString("Be Careful : please verify your tape!")));
    }

    @Test
    @WithMockUser("user")
    void update_OK() throws Exception {
        BidList bid = bidListRepository.save(new BidList("accountN", "type N",new BigDecimal("10")));
        mockMvc.perform(post("/bidList/update?id=" + bid.getId())
                        .contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
                        .param("account", "account111")
                        .param("type", "typeModified")
                        .param("bidQuantity", "100000")
                        .with(csrf()))
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().isFound());


    }

    @Test
    @WithMockUser("user")
    void delete() throws Exception {
        BidList bid = bidListRepository.save(new BidList("accountN", "type N",new BigDecimal("10")));
        mockMvc.perform(get("/bidList/delete?id=" + bid.getId()))
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().isFound());
    }
}