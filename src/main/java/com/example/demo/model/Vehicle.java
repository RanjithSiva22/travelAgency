
package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.payload.RideDetail;
import com.example.demo.payload.VehicleDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
// @Builder
@Document(collection = "vehicle")
public class Vehicle {
   
    @Id
    private String id;
    public String vehicletype;
    public String vehicleNo;
    // public String chargeperkm;
    public Driver driver;
    public String vehicleStatus="AVAILABLE";




    public Vehicle(VehicleDetail vehicalDetail) {
        this.vehicletype=vehicalDetail.vehicletype;
        this.vehicleNo=vehicalDetail.vehicleNo;
        // this.chargeperkm=vehicalDetail.chargeperkm;
        this.driver=new Driver(vehicalDetail.driver);                      
    }
    // Getters and setters
}