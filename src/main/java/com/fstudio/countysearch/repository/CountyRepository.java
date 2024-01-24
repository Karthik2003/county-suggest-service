package com.fstudio.countysearch.repository;

import com.fstudio.countysearch.dto.County;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CountyRepository extends CrudRepository<County, Long> {
    @Query(value = "SELECT FIPS, STATE, NAME FROM COUNTY WHERE NAME = :searchName AND STATE = :searchState LIMIT 5", nativeQuery = true)
    Optional<List<County>> countyBySearchQuery(@Param("searchName") final String searchName, @Param("searchState") final String searchState);
    @Query(value = "SELECT FIPS, STATE, NAME FROM COUNTY WHERE STATE = :searchQuery LIMIT 5", nativeQuery = true)
    Optional<List<County>> countyBySearchQueryOnState(@Param("searchQuery") final String searchQuery);

    @Query(value = "SELECT FIPS, STATE, NAME FROM COUNTY WHERE NAME LIKE CONCAT(:searchQuery,'%') LIMIT 5", nativeQuery = true)
    Optional<List<County>> countyBySearchQueryOnName(@Param("searchQuery") final String searchQuery);
}