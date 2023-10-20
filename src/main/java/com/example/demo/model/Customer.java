
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
@Getter @Setter
// @Builder
@Document(collection = "customer")
public class Customer {
   
    @Id
    private String id;
    public String customerName;
    public String custmailId;
    public String custPhoneNo;


    public Customer(CustomerDetail customerDetail) {
      
        this.customerName=customerDetail.customerName;                      
        this.custmailId=customerDetail.custmailId;
        this.custPhoneNo=customerDetail.custPhoneNo;

    }

}