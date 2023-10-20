package com.example.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.payload.CustomerDetail;
import com.example.demo.payload.RideDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// @Builder
@Document(collection = "ridebook")
public class BookingDetail {

    @Id
    private String id;
    public String pickuppoint = "A";
    public String dropPoint;
    public String vehicletype;

    public float price;
    public String VehicleAssigned;

    // public String vehicleId;
    public String customerId;
    public String bookingStatus = "WAITING";
    public Date bookedAt;

    public BookingDetail(RideDetail rideDetail) {
        this.dropPoint = rideDetail.dropPoint;
        this.vehicletype = rideDetail.vehicletype;
        this.bookedAt = new Date();
    }

}