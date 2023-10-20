package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.demo.model.BookingDetail;
import com.example.demo.model.Driver;
import com.example.demo.model.Vehicle;
import com.example.demo.payload.RideDetail;
import com.example.demo.payload.VehicleDetail;
import com.example.demo.repository.BookRideRepository;
import com.example.demo.repository.VehicleRepository;

public class VehicleService {

   @Autowired
   public VehicleRepository vehicleRepository;

   private MongoTemplate mongoTemplate;
    

   public VehicleService(VehicleRepository vehicleRepository,MongoTemplate mongoTemplate){
        this.vehicleRepository=vehicleRepository;
        this.mongoTemplate=mongoTemplate;
    }

    // add vehicle with driver details[ unable to add vechile with same num plate]
    public String addVehicle(VehicleDetail vehicalDetail){
        // Vehical veh = vehicleRepository.findOne({}).orElse(null);
        Query query = new Query();
        query.addCriteria(Criteria.where("vehicleNo").is(vehicalDetail.vehicleNo));
       
        Vehicle vehicle = mongoTemplate.findOne(query, Vehicle.class);
        if(vehicle==null){
            Vehicle v=new Vehicle(vehicalDetail);

            vehicleRepository.save(v);
            return "vehicle added";
        }
       return "vehicle already present";
    }

    // to get vehicles by their type[cab,bike,auto]
    public List<Vehicle> getVehicleByType(String vehicletype){
        
        Query query = new Query();
        query.addCriteria(Criteria.where("vehicletype").is(vehicletype));
       
        List<Vehicle> vehicles = mongoTemplate.find(query, Vehicle.class);

        return vehicles;
    }

    // to get all vehicles in agency
    public List<Vehicle> getAllVehicles(){
      
        List<Vehicle> listOfVehicles=vehicleRepository.findAll();
        return listOfVehicles;
    }


    // to get all drivers
    public List<Driver> getAllDrivers(){
        List<Vehicle> listOfVehicles=vehicleRepository.findAll();
        List<Driver> driversList= listOfVehicles.stream().map(vehicle ->  vehicle.getDriver()).toList();
        return driversList;
    }
}
