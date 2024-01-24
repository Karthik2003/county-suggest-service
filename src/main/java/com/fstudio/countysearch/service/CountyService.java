package com.fstudio.countysearch.service;

import com.fstudio.countysearch.dto.County;
import com.fstudio.countysearch.repository.CountyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(searchQuery.contains(",")) {
            String[] queryArray = searchQuery.split(",");
            return countyRepository.countyBySearchQuery(queryArray[0].trim(), queryArray[1].trim())
                    .orElseThrow(() -> new Exception("No results found for "+searchQuery));
        } else {
            if(searchQuery.length() == 2) {
                return countyRepository.countyBySearchQueryOnState(searchQuery)
                        .orElseThrow(() -> new Exception("No results found for "+searchQuery));
            } else {
                return countyRepository.countyBySearchQueryOnName(searchQuery)
                        .orElseThrow(() -> new Exception("No results found for "+searchQuery));
            }
        }
    }
}