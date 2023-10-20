package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.BookingDetail;
import com.example.demo.model.Customer;
import com.example.demo.model.RouteData;
import com.example.demo.model.Vehicle;
import com.example.demo.payload.RideDetail;
import com.example.demo.repository.BookRideRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RouteDataRepository;
import com.example.demo.repository.VehicleRepository;

@Service
public class BookRideService {
    private BookRideRepository bookRideRepository;

    // @Autowired
    private RouteDataRepository routeDataRepository;

    private VehicleRepository vehicleRepository;

    private MongoTemplate mongoTemplate;
        
    private CustomerRepository customerRepository;


    public BookRideService(BookRideRepository bookRideRepository, RouteDataRepository routeDataRepository,
            VehicleRepository vehicleRepository,CustomerRepository customerRepository, MongoTemplate mongoTemplate) {
        this.bookRideRepository = bookRideRepository;
        this.routeDataRepository = routeDataRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public String bookRide(RideDetail rideDetail) {
        // System.out.println("+++++++++++");
        RouteData routeData = routeDataRepository.findAll().get(0);
   

        BookingDetail bookingDetail = new BookingDetail(rideDetail);
        Query query = new Query();
        query.addCriteria(Criteria.where("vehicleStatus").is("AVAILABLE"));
        query.addCriteria(Criteria.where("vehicletype").is(rideDetail.vehicletype));
        Vehicle availableVehicle = mongoTemplate.findOne(query, Vehicle.class);

        Customer customer=new Customer(rideDetail.customerDetail);
        bookingDetail.customerId=customer.getId();

        customerRepository.save(customer);

        if (availableVehicle != null) {

            bookingDetail.VehicleAssigned = availableVehicle.getId();
            bookingDetail.price = (int) routeData.getValues().get(bookingDetail.vehicletype + "price");

            bookingDetail.price *= (int) routeData.getValues().get(bookingDetail.dropPoint);

            availableVehicle.vehicleStatus = "UNAVAILABLE";
            vehicleRepository.save(availableVehicle);
            bookRideRepository.save(bookingDetail);
            return "booked";
        }

        return "no facilities available on your choice";
    }

    public String updateStatus(String bookedId, String status) {
        BookingDetail bookingDetail = bookRideRepository.findById(bookedId).orElse(null);
        if (bookingDetail != null) {
            bookingDetail.bookingStatus = status;
            if (status.equals("DROPPED") || status.equals("CANCELLED")) {
                Vehicle vehicleAssigned = vehicleRepository.findById(bookingDetail.VehicleAssigned).orElse(null);
                vehicleAssigned.vehicleStatus = "AVAILABLE";
                vehicleRepository.save(vehicleAssigned);
            }

            bookRideRepository.save(bookingDetail);
        } else {
            return "not found";
        }

        return "status updated";
    }

    public BookingDetail getRideById(String bookedId) {
        BookingDetail rideById = bookRideRepository.findById(bookedId).orElse(null);
        
        return rideById;
    }

    
    public List<BookingDetail> getAllRides() {
        List<BookingDetail> allRides = bookRideRepository.findAll();
        
        return allRides;
    }

    
    public List<Customer> getAllCustomers() {
        List<Customer> listOfCustomers = customerRepository.findAll();
        
        return listOfCustomers;
    }

}

