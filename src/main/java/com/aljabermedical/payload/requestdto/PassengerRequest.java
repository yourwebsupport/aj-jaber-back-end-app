package com.aljabermedical.payload.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerRequest {

    private String registrationNo;
    private String name;
    private String fatherName;
    private String motherName;
    private Long maritalStatusId;
    private Long bloodGroupId;
    private Long countryId;
    private Date dateOfBirth;
    private Long genderId;
    private String weight;
    private String height;
    private String profession;
    private String mobileNo;
    private String email;
    private String passportNo;
    private String visaNo;
    private Date visaIssueDate;
    private String passengerPhoto;
    private String passportPhoto;
    private String visaPhoto;
    private String agencyName;
    private String executiveName;
    private String remarks;

}