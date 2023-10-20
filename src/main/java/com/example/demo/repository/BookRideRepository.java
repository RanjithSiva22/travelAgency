package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BookingDetail;

@Repository
public interface BookRideRepository extends MongoRepository<BookingDetail, String> {

} 
