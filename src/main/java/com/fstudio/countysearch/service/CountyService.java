package com.fstudio.countysearch.service;

import com.fstudio.countysearch.dto.County;
import com.fstudio.countysearch.repository.CountyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class CountyService {
    @Autowired
    private CountyRepository countyRepository;
    public void saveAll(List<County> countyList) {
        log.info("<== inside save for persisting all ==>");
        countyRepository.saveAll(countyList);
    }
    public List<County> findCountyBySearchQuery(String searchQuery) throws Exception {
        log.info("county search query {}", searchQuery);
        String[] queryArray = searchQuery.split(",");
        List<County> countyList = new ArrayList<>();
        for(String query : queryArray) {
            if(query.length() == 2) {
                countyList = countyRepository.countyBySearchQueryOnState(searchQuery)
                        .orElseThrow(() -> new Exception("No results found for "+searchQuery));
            } else {
                countyList = countyRepository.countyBySearchQueryOnName(searchQuery)
                        .orElseThrow(() -> new Exception("No results found for "+searchQuery));
            }
        }
        return countyList;
    }
}
