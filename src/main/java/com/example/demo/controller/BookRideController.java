package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.DataResponse;
import com.example.demo.repository.RouteDataRepository;
import com.example.demo.model.BookingDetail;
import com.example.demo.payload.RideDetail;
import com.example.demo.repository.BookRideRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.BookRideService;

@RestController
@RequestMapping("/uber")
public class BookRideController {

    @Autowired
    BookRideRepository bookRideRepository;

    @Autowired
    RouteDataRepository routeDataRepository;

    @Autowired
    VehicleRepository vehicleRepository;
    
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CustomerRepository customerRepository;


    @PostMapping("/bookride")
    public ResponseEntity<?> bookride(@RequestBody RideDetail rideDetail){
         try {
            BookRideService bookRideService = new BookRideService(bookRideRepository,routeDataRepository,vehicleRepository,customerRepository, mongoTemplate);
            return new ResponseEntity<>(
            DataResponse.builder().data(bookRideService.bookRide(rideDetail)).build(),
            HttpStatus.OK);
            } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(DataResponse.builder().error(ex.getLocalizedMessage()).build(),
            HttpStatus.FORBIDDEN);
            }
    }

    @PutMapping("/updateStatus/{bookingId}/status/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable String bookingId, @PathVariable String status){
         try {
 
            BookRideService bookRideService = new BookRideService(bookRideRepository,routeDataRepository,vehicleRepository,customerRepository, mongoTemplate);
            return new ResponseEntity<>(
            DataResponse.builder().data(bookRideService.updateStatus(bookingId,status)).build(),
            HttpStatus.OK);
            } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(DataResponse.builder().error(ex.getLocalizedMessage()).build(),
            HttpStatus.FORBIDDEN);
            }
    }

    @GetMapping("/getrides")
    public ResponseEntity<?> getallRides(){
           try {
 
            BookRideService bookRideService = new BookRideService(bookRideRepository,routeDataRepository,vehicleRepository, customerRepository, mongoTemplate);
            return new ResponseEntity<>(
            DataResponse.builder().data(bookRideService.getAllRides()).build(),
            HttpStatus.OK);
            } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(DataResponse.builder().error(ex.getLocalizedMessage()).build(),
            HttpStatus.FORBIDDEN);
            }
    }

    @GetMapping("/getCustomers")
    public ResponseEntity<?> getCustomers(){
           try {
 
            BookRideService bookRideService = new BookRideService(bookRideRepository,routeDataRepository,vehicleRepository, customerRepository, mongoTemplate);
            return new ResponseEntity<>(
            DataResponse.builder().data(bookRideService.getAllCustomers()).build(),
            HttpStatus.OK);
            } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(DataResponse.builder().error(ex.getLocalizedMessage()).build(),
            HttpStatus.FORBIDDEN);
            }
    }

    @GetMapping("/getRidesById/{bookedId}")
    public ResponseEntity<?> getRidesById(@PathVariable String bookedId){
       try {
 
            BookRideService bookRideService = new BookRideService(bookRideRepository,routeDataRepository,vehicleRepository,customerRepository, mongoTemplate);
            return new ResponseEntity<>(
            DataResponse.builder().data(bookRideService.getRideById(bookedId)).build(),
            HttpStatus.OK);
            } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(DataResponse.builder().error(ex.getLocalizedMessage()).build(),
            HttpStatus.FORBIDDEN);
            }
    }





}
