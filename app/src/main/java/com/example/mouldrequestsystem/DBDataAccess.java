package com.example.mouldrequestsystem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBDataAccess {

    checkConnection _checkConnection;

    CallableStatement _callableStatement;

    public Boolean isSuccess = false;

    public String z = "";

    public String RequestIDs = "",PartCode= "",StepCode= "",Location= "",MachineNo= "",DateRequest= "",Requestby= "";

    public Integer recCounter;

    ArrayList<ClassMouldRequestModel> datas = new ArrayList<ClassMouldRequestModel>();

    public String[] array;

    ClassMouldRequestDT classMouldRequestDT = new ClassMouldRequestDT();


    public boolean SelectMouldRequest() {
        try {
            _checkConnection = new checkConnection();

            Connection con = _checkConnection.Conn();

            String query = "call SelectMouldRequest";

            CallableStatement callableStatement = con.prepareCall(query);

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {

                ClassMouldRequestModel itemPanel = new ClassMouldRequestModel();

                itemPanel.setRequestID(rs.getString("RequestID"));

                itemPanel.setPartCode(rs.getString("PartCode"));

                itemPanel.setStepCode(rs.getString("StepCode"));

                itemPanel.setLocation(rs.getString("MachineLocation"));

                itemPanel.setMachineNo(rs.getString("MachineNo"));

                itemPanel.setDateRequest(rs.getString("DateRequest"));

                itemPanel.setRequestby(rs.getString("Requestby"));

                datas.add(itemPanel);

                classMouldRequestDT.setMouldRequestList(datas);

                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }


    public boolean CountRequests() {
        try {
            _checkConnection = new checkConnection();

            Connection con = _checkConnection.Conn();

            String query = "call CountAllRequest";

            CallableStatement callableStatement = con.prepareCall(query);

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {

                recCounter = rs.getInt("RecCounter");

                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }



    public boolean SelectMouldRequestbyID(String RequestID) {
        try {
            _checkConnection = new checkConnection();

            Connection con = _checkConnection.Conn();

            String query = "{call SelectMouldRequestbyID(?)}";

            CallableStatement callableStatement = con.prepareCall(query);

            callableStatement.setString(1,RequestID);

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {


                PartCode = rs.getString("PartCode");

                StepCode = rs.getString("StepCode");

                Location = rs.getString("Location");

                MachineNo = rs.getString("MachineNo");

                DateRequest = rs.getString("DateRequest");

                Requestby = rs.getString("Requestby");

                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean updatemouldrequesttoOngoing(String Acceptby,String RequestID) {

        try {

            _checkConnection = new checkConnection();

            Connection con = _checkConnection.Conn();

            String query = "{call UpdateMouldRequesttoOngoing(?,?)}";

            CallableStatement callableStatement = con.prepareCall(query);

            callableStatement.setString(1, Acceptby);

            callableStatement.setString(2, RequestID);

            callableStatement.executeUpdate();

            con.setAutoCommit(true);

            isSuccess = true;

            z = "Request Accepted";

        } catch (Exception ex) {
            z = ex.getMessage().toString();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean updatemouldrequesttoCompleted(String RequestID) {

        try {

            _checkConnection = new checkConnection();

            Connection con = _checkConnection.Conn();

            String query = "{call UpdateMouldRequesttoCompleted(?)}";

            CallableStatement callableStatement = con.prepareCall(query);

            callableStatement.setString(1, RequestID);

            callableStatement.executeUpdate();

            con.setAutoCommit(true);

            isSuccess = true;

            z = "Request Completed";

        } catch (Exception ex) {
            z = ex.getMessage().toString();
            isSuccess = false;
        }
        return isSuccess;
    }



}
