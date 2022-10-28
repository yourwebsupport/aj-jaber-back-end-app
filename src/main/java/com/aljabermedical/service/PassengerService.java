package com.aljabermedical.service;

import com.aljabermedical.payload.requestdto.PassengerRequest;

public interface PassengerService {
    void createPassenger(PassengerRequest request);
    long getTotalCountPassenger();
    Boolean existsByRegistrationNo(String registrationNo);

}