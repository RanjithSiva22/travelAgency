package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.payload.DriverDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class Driver {
   
    @Id
    private String id;
    public String name;
    public String age;
    public String phoneNo;

  

   
    


    public Driver(DriverDetail driverDetail) {
        UUID uniqueId = UUID.randomUUID();
        this.id = uniqueId.toString();
        this.name=driverDetail.name;
        this.age=driverDetail.age;
        this.phoneNo=driverDetail.phoneNo;                   
    }
    // Getters and setters
}