package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.DataResponse;
import com.example.demo.payload.RideDetail;
import com.example.demo.payload.VehicleDetail;

import com.example.demo.repository.VehicleRepository;

import com.example.demo.service.VehicleService;

@RestController
@RequestMapping("/uber")
public class VehicleController {
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
        
    @PostMapping("/addvehicle")
    public ResponseEntity<?> adddriver(@RequestBody VehicleDetail vehicalDetail){
         try {
            VehicleService vehicleService = new VehicleService(vehicleRepository, mongoTemplate);
            return new ResponseEntity<>(
            DataResponse.builder().data(vehicleService.addVehicle(vehicalDetail)).build(),
            HttpStatus.OK);
            } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(DataResponse.builder().error(ex.getLocalizedMessage()).build(),
            HttpStatus.FORBIDDEN);
            }
    }


    @GetMapping("/getVehicleByType/{vehicletype}")
    public ResponseEntity<?> adddriver(@PathVariable String vehicletype){
         try {
            VehicleService vehicleService = new VehicleService(vehicleRepository, mongoTemplate);
            return new ResponseEntity<>(
            DataResponse.builder().data(vehicleService.getVehicleByType(vehicletype)).build(),
            HttpStatus.OK);
            } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(DataResponse.builder().error(ex.getLocalizedMessage()).build(),
            HttpStatus.FORBIDDEN);
            }
    }

    @GetMapping("/getAllVehicles")
    public ResponseEntity<?> adddriver(){
         try {
            VehicleService vehicleService = new VehicleService(vehicleRepository, mongoTemplate);
            return new ResponseEntity<>(
            DataResponse.builder().data(vehicleService.getAllVehicles()).build(),
            HttpStatus.OK);
            } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(DataResponse.builder().error(ex.getLocalizedMessage()).build(),
            HttpStatus.FORBIDDEN);
            }
    }

    @GetMapping("/getAllDrivers")
    public ResponseEntity<?> getDrivers(){
         try {
            VehicleService vehicleService = new VehicleService(vehicleRepository, mongoTemplate);
            return new ResponseEntity<>(
            DataResponse.builder().data(vehicleService.getAllDrivers()).build(),
            HttpStatus.OK);
            } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(DataResponse.builder().error(ex.getLocalizedMessage()).build(),
            HttpStatus.FORBIDDEN);
            }
    }
}
