package com.example.demo.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.aggregation.VariableOperators.Map;
// import java.util.Map;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Document(collection="routeData")
public class RouteData {
    @Id
    public String id;
    public HashMap values;
    // public int B;
    // public int C;
    // public int D;

    // public int cabs;
    // public int autos;
    // public int bikes;

    // public float cabprice;
    // public float bikeprice;
    // public float autoprice;

}
