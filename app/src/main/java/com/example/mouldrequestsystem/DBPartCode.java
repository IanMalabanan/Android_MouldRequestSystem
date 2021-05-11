package com.example.mouldrequestsystem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBPartCode {
    checkConnection _checkConnection;

    CallableStatement _callableStatement;

    public Boolean isSuccess = false;

    public String z = "";

    ArrayList<ClassPartCodeModel> data2 = new ArrayList<ClassPartCodeModel>();

    public String[] array;

    ClassPartCodeDT classPartCodeDT = new ClassPartCodeDT();


    public boolean SelectALLPartcode() {
        try {
            _checkConnection = new checkConnection();

            Connection con = _checkConnection.Conn();

            String query = "call SelectALLPartcode";

            CallableStatement callableStatement = con.prepareCall(query);

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {

                ClassPartCodeModel itemPanel = new ClassPartCodeModel();

                itemPanel.setT_partcode(rs.getString("PartCode"));

                data2.add(itemPanel);

                classPartCodeDT.setPartCodeList(data2);

                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }


    public boolean GetALLPartcode() {
        try {
            _checkConnection = new checkConnection();

            Connection con = _checkConnection.Conn();

            String query = "call SelectALLPartcode";

            CallableStatement callableStatement = con.prepareCall(query);

            ResultSet rs = callableStatement.executeQuery();

//            while (rs.next()) {
////
////                ClassPartCodeModel itemPanel = new ClassPartCodeModel();
////
////                itemPanel.setT_partcode(rs.getString("PartCode"));
////
////                data2.add(itemPanel);
////
////                classPartCodeDT.setPartCodeList(data2);
////
////                isSuccess = true;
////            }

            ArrayList<String> data = new ArrayList<String>();

            while (rs.next()) {
                String id = rs.getString("PartCode");

                data.add(id);

                isSuccess = true;
            }

            array = data.toArray(new String[0]);

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }


    public boolean InsertPartCode(String PartCode) {

        String insertStoreProc = "{call InsertNewPartCode(?)}";

        try {

            Connection con = _checkConnection.Conn();

            _callableStatement = con.prepareCall(insertStoreProc);

            _callableStatement.setString(1, PartCode);

            _callableStatement.executeUpdate();

            con.setAutoCommit(true);

            isSuccess = true;

            z = "Successfully Added";

        } catch (Exception ex) {
            z = ex.getMessage();
            isSuccess = false;
        }

        return isSuccess;
    }



}
