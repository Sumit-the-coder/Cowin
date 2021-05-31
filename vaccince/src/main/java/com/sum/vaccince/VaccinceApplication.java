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
	public void postConstract(){
		if(appContext.containsBean("apiCall")){
			System.out.println("We got the bean");
		}
	}

//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.build();
//	}
//
//	@Bean
//	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//		System.out.println("Scheduler is starting");
//		return args -> {
//			String quote = restTemplate.getForObject( BY_DISTRICT, String.class);
//			Map<String, Sessions> map = new HashMap<>();
//			map = new Gson().fromJson(quote, Map.class);
//
//			ArrayList<Sessions> listdata = new ArrayList<Sessions>();
//			JSONObject jsonObject = new JSONObject(quote);
//			JSONArray jsonArray = jsonObject.getJSONArray("sessions");
//			if(jsonArray != null) {
//				for (int i = 0; i < jsonArray.length(); i++) {
//					JSONObject jobj = (JSONObject) jsonArray.get(i);
//					Sessions s = new Sessions();
//					s.setFee(jobj.getString("fee"));
//					s.setPincode(jobj.getString("pincode"));
//					s.setFee_type(jobj.getString("fee_type"));
//					s.setAddress(jobj.getString("address"));
//					s.setAvailable_capacity(jobj.getInt("available_capacity"));
//					s.setAvailable_capacity_dose1(jobj.getString("available_capacity_dose1"));
//					s.setAvailable_capacity_dose2(jobj.getString("available_capacity_dose2"));
//					s.setBlock_name(jobj.getString("block_name"));
//					s.setCenter_id(jobj.getString("center_id"));
//					s.setDate(jobj.getString("date"));
//					s.setDistrict_name(jobj.getString("district_name"));
//					s.setFrom(jobj.getString("from"));
//					s.setMin_age_limit(jobj.getString("min_age_limit"));
//					s.setSession_id(jobj.getString("session_id"));
//					//s.setSlots(jobj.getString(""));
//					s.setState_name(jobj.getString("state_name"));
//					s.setTo(jobj.getString("to"));
//					s.setVaccine(jobj.getString("vaccine"));
//
//
//					listdata.add(s);
//				}
//
//				List<Sessions> availableSessions = listdata.stream().filter(sessions -> sessions.getFee().equals("0"))
//						.filter(sessions -> sessions.getMin_age_limit().equals("18"))
//						.filter(sessions -> sessions.getAvailable_capacity() > 0)
//						.collect(Collectors.toList());
//
//				if(availableSessions!=null || availableSessions.size()>0){
//					availableSessions.stream().forEach(sessions->{
//						System.out.println("Vaccine available at " + sessions.getPincode());
//					});
//				} else {
//					System.out.println("No vaccine available");
//				}
//			}
//
//		};
//	}
}
