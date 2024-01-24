package com.fstudio.countysearch;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fstudio.countysearch.dto.County;
import com.fstudio.countysearch.service.CountyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
//@EnableSwagger2
@Slf4j
public class CountySuggestionsApplication {

	public static void main(String[] args) {

		SpringApplication.run(CountySuggestionsApplication.class, args);
		log.info("...................Starting county-search-service.....................");
	}

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.fstudio.countysearch.controller"))
//				.paths(PathSelectors.any())
//				.build()
//				.apiInfo(apiInfo());
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfo(
//				"Portfolio Management Service",
//				"PM Rest Service API",
//				"API V1",
//				"T & C",
//				new Contact("Fleet Studio", "https://fleetstudio.com/", "info@fleetstudio.com"),
//				"API", "API URL", Collections.emptyList());
//	}
	@Bean
	CommandLineRunner commandLineRunner(CountyService countyService){
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<County>> typeReference = new TypeReference<List<County>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data/data.json");
			try {
				List<County> countyList = mapper.readValue(inputStream,typeReference);
				countyService.saveAll(countyList);
				log.info("<== all data persisted successfully!!! ==>");
			} catch (Exception e){
				log.error("<== all data persist failed :) ==>");
				throw new IOException(e.getMessage());
			}
		};
	}
}