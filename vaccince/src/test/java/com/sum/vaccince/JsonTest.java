package com.sum.vaccince;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class JsonTest {
    private String JSON_FILE_NAME = "district.json";

    @Test
    public void testJson() throws IOException, JSONException {
        ClassLoader classLoader = getClass().getClassLoader();
        File jsonFile =  new File(classLoader.getResource(JSON_FILE_NAME).getFile());

        String json = new String( Files.readAllBytes(jsonFile.toPath()));

        System.out.println(json);

        Map<String, Sessions> map = new HashMap<>();

        map = new Gson().fromJson(json, Map.class);

        /*System.out.println(map.values());

        List<Sessions> allSessions = (List<Sessions>) map.values();*/
//AbstractMap
        ArrayList<Sessions> listdata = new ArrayList<Sessions>();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("sessions");
        if(jsonArray != null){
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jobj = (JSONObject) jsonArray.get(i);
                Sessions s = new Sessions();
                s.setFee(jobj.getString("fee"));
                s.setPincode(jobj.getString("pincode"));
                s.setFee_type( jobj.getString("fee_type"));
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


                listdata.add(s);
            }
        }

    }

    @Test
    public void testJson2() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File jsonFile =  new File(classLoader.getResource(JSON_FILE_NAME).getFile());


        String json = new String( Files.readAllBytes(jsonFile.toPath()));

        System.out.println(json);

        Type listType = new TypeToken<Map<String,Collection<Sessions>>>() {}.getType();
        Map<String,Collection<Sessions>> sessionsList = new Gson().fromJson(json, listType);


        Type listType1 = new TypeToken<LinkedTreeMap<String, Sessions>>() {}.getType();
        LinkedTreeMap<String, Sessions> sessionsList1 = new Gson().fromJson(json, listType);
        //sessionsList1.values().;

        ArrayList<Sessions> allList = (ArrayList)sessionsList.values();

    }

    @Test
    public void testBell(){
        System.out.print("\007");
        System.out.flush();
    }
}
