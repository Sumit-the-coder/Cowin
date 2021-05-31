package com.sum.vaccince;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Setter
@Getter
public class Sessions{

    private String center_id;
    private String name;

    @Override
    public String toString() {
        return "Sessions{" +
                "center_id='" + center_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", state_name='" + state_name + '\'' +
                ", district_name='" + district_name + '\'' +
                ", block_name='" + block_name + '\'' +
                ", pincode='" + pincode + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", lat='" + lat + '\'' +
                ", fee_type='" + fee_type + '\'' +
                ", session_id='" + session_id + '\'' +
                ", date='" + date + '\'' +
                ", available_capacity_dose1=" + available_capacity_dose1 +
                ", available_capacity_dose2='" + available_capacity_dose2 + '\'' +
                ", available_capacity=" + available_capacity +
                ", fee='" + fee + '\'' +
                ", min_age_limit='" + min_age_limit + '\'' +
                ", vaccine='" + vaccine + '\'' +
                ", slots=" + slots +
                '}';
    }

    private String address;
    private String state_name;
    private String district_name;
    private String block_name;
    private String pincode;
    private String from;
    private String to;
    private String lat;
    //private String long;
    private String fee_type;
    private String session_id;
    private String date;
    private Integer available_capacity_dose1;
    private String available_capacity_dose2;
    private Integer available_capacity;
    private String fee;
    private String min_age_limit;
    private String vaccine;
    private List<String> slots;

}
