package com.fstudio.countysearch.controller;

import com.fstudio.countysearch.dto.County;
import com.fstudio.countysearch.service.CountyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(CountyController.class)
public class CountyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountyService countyService;

    @Test
    public void findCountyBySearchQueryTest() throws Exception {

        List<County> countyList = new ArrayList<>();
        County county1 = new County();
        county1.setFips(1l);
        county1.setState("KS");
        county1.setName("Cowley");
        County county2 = new County();
        county1.setFips(1l);
        county1.setState("WA");
        county1.setName("Cowlitz");

        countyList.add(county1);
        countyList.add(county2);

        when(countyService.findCountyBySearchQuery("cowl")).thenReturn(countyList);
        ResultActions resultActions = mockMvc.perform(get("/suggest"));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().json("[{\"fips\":1,\"state\":\"KS\",\"name\":\"Cowley\"},{\"fips\":2,\"state\":\"WA\",\"name\":\"Cowlitz\"}]"));
    }
}