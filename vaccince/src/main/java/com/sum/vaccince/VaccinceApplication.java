package com.sum.vaccince;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class VaccinceApplication {

	@Autowired
	ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(VaccinceApplication.class, args);
	}

	@PostConstruct
	public void postConstract() {
		if (appContext.containsBean("apiCall")) {
			System.out.println("We got the bean");
		}
	}

}
