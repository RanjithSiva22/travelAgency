package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RouteData;

@Repository
public interface RouteDataRepository extends MongoRepository<RouteData, String> {

} 
