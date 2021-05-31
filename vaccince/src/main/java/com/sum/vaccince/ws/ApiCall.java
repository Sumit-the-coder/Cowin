package com.sum.vaccince.ws;

import com.google.gson.Gson;
import com.sum.vaccince.Sessions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class ApiCall {

    RestTemplate restTemplate;
    private String BY_DISTRICT1 = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id=363&date=19-06-2021";
    private String BY_DISTRICT = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id=363&date=";

    @Scheduled(fixedRate = 5000)
    private void findByDistrict() throws JSONException {
        for(int t=1; t<10; t++){
            String date = "0"+t+"-06-2021";
            String quote = restTemplate.getForObject(BY_DISTRICT + date, String.class);
            //System.out.println(quote);
            Map<String, Sessions> map = new Gson().fromJson(quote, Map.class);

            ArrayList<Sessions> totalSlots = new ArrayList<Sessions>();
            JSONObject jsonObject = new JSONObject(quote);
            JSONArray jsonArray = jsonObject.getJSONArray("sessions");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jobj = (JSONObject) jsonArray.get(i);
                    Sessions s = getSessionData(jobj);
                    totalSlots.add(s);
                }
                System.out.println("Total slots : " + totalSlots.size());
                if(totalSlots.size() == 0){
                    return;
                }
                List<Sessions> availableSessions = addFilter(totalSlots);

                if (availableSessions != null && availableSessions.size() > 0) {
                    availableSessions.stream().forEach(sessions -> {
                        System.out.println("Vaccine available on date " + date + " at pincode " + sessions.getPincode());
                        System.out.println(sessions);
                        System.exit(0);
                    });
                } else {
                    System.out.println("No vaccine available");
                }
            }
        }
    }

    private List<Sessions> addFilter(ArrayList<Sessions> listdata) {
        List<Sessions> availableSessions = listdata.stream()
                //.filter(sessions -> sessions.getFee().equals("0"))
                .filter(sessions -> sessions.getMin_age_limit().equals("18"))
                .filter(sessions -> sessions.getAvailable_capacity() > 0)
                .filter(sessions -> sessions.getAvailable_capacity_dose1() > 0)
                //.filter(sessions -> sessions.getFee_type().equals("Paid"))
                .collect(Collectors.toList());
        return availableSessions;
    }

    private Sessions getSessionData(JSONObject jobj) throws JSONException {
        Sessions s = new Sessions();
        s.setFee(jobj.getString("fee"));
        s.setPincode(jobj.getString("pincode"));
        s.setFee_type(jobj.getString("fee_type"));
        s.setAddress(jobj.getString("address"));
        s.setAvailable_capacity(jobj.getInt("available_capacity"));
        s.setAvailable_capacity_dose1(jobj.getInt("available_capacity_dose1"));
        s.setAvailable_capacity_dose2(jobj.getString("available_capacity_dose2"));
        s.setBlock_name(jobj.getString("block_name"));
        s.setCenter_id(jobj.getString("center_id"));
        s.setDate(jobj.getString("date"));
        s.setDistrict_name(jobj.getString("district_name"));
        s.setFrom(jobj.getString("from"));
        s.setMin_age_limit(jobj.getString("min_age_limit"));
        s.setSession_id(jobj.getString("session_id"));
        //s.setSlots(jobj.getString(""));
        s.setState_name(jobj.getString("state_name"));
        s.setTo(jobj.getString("to"));
        s.setVaccine(jobj.getString("vaccine"));

        return s;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        return restTemplate;
    }
}
