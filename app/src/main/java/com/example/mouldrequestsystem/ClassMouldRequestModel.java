package com.example.mouldrequestsystem;

import java.util.Date;

public class ClassMouldRequestModel {

    private String RequestID;
    private String PartCode;
    private String StepCode;
    private String MachineNo;
    private String DateRequest;
    private String Location;
    private String Requestby;
    private String Acceptby;
    private Integer status;

    public Integer getRecCounter() {
        return RecCounter;
    }

    public void setRecCounter(Integer recCounter) {
        RecCounter = recCounter;
    }

    private Integer RecCounter;


    public String getRequestID() {return RequestID;}

    public void setRequestID(String requestID) { RequestID = requestID;}

    public String getPartCode() {
        return PartCode;
    }

    public void setPartCode(String partCode) {
        PartCode = partCode;
    }

    public String getStepCode() {
        return StepCode;
    }

    public void setStepCode(String stepCode) {
        StepCode = stepCode;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getMachineNo() {
        return MachineNo;
    }

    public void setMachineNo(String machineNo) {
        MachineNo = machineNo;
    }

    public String getDateRequest() {
        return DateRequest;
    }

    public void setDateRequest(String dateRequest) {
        DateRequest = dateRequest;
    }

    public String getRequestby() {
        return Requestby;
    }

    public void setRequestby(String requestby) {
        Requestby = requestby;
    }

    public String getAcceptby() {
        return Acceptby;
    }

    public void setAcceptby(String acceptby) {
        Acceptby = acceptby;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }




}
