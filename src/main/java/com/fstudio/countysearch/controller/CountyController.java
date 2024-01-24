package com.fstudio.countysearch.controller;

import com.fstudio.countysearch.dto.County;
import com.fstudio.countysearch.service.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class CountyController {
    @Autowired
    private CountyService countyService;
    @GetMapping("/suggest")
    public List<County> findCountyBySearchQuery(@RequestParam(name="q") String searchQuery) throws Exception {
        return countyService.findCountyBySearchQuery(searchQuery);
    }
}
