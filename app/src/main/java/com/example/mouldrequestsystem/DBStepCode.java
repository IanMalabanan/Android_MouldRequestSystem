package com.example.mouldrequestsystem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

class DBStepCode {

    checkConnection _checkConnection;

    CallableStatement _callableStatement;

    public Boolean isSuccess = false;

    public String z = "";

    ArrayList<ClassStepCodeModel> data2 = new ArrayList<ClassStepCodeModel>();

    public String[] array;

    ClassStepCodeDT classStepCodeDT = new ClassStepCodeDT();


    public boolean SelectStepCode(String PartCode) {
        try {
            _checkConnection = new checkConnection();

            Connection con = _checkConnection.Conn();

            String query = "{call SelectMouldStep(?)}";

            CallableStatement callableStatement = con.prepareCall(query);

            callableStatement.setString(1,PartCode);

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {

                ClassStepCodeModel itemPanel = new ClassStepCodeModel();

                itemPanel.setT_stepcode(rs.getString("StepCode"));

                itemPanel.setT_location(rs.getString("Location"));

                data2.add(itemPanel);

                classStepCodeDT.setStepCodeList(data2);

                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }
}
