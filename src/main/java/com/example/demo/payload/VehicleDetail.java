package com.example.demo.payload;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.payload.RideDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class VehicleDetail {

    public String vehicletype;
    public String vehicleNo;
    public String chargeperkm;
    public DriverDetail driver;

}