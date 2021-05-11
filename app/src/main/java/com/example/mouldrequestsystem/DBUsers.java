package com.example.mouldrequestsystem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBUsers {

    checkConnection _checkConnection;

    CallableStatement _callableStatement;

    public Boolean isSuccess = false;

    public String z = "";

    public String[] array;

    public String fname = "";


    public boolean UserLogin(String username, String password) {
        try {
            _checkConnection = new checkConnection();

            Connection con = _checkConnection.Conn();

            if (con == null) {
                z = "Error in Network Connection";
            } else {
                String SP = "{call SelectOperator(?,?)}";

                _callableStatement = con.prepareCall(SP);

                _callableStatement.setString(1, username);

               _callableStatement.setString(2, password);

                ResultSet rs = _callableStatement.executeQuery();

                if (rs.next()) {

                    z = "";
                    fname = rs.getString("fname");
                    isSuccess = true;

                } else {
                    z = "Access Denied";
                    isSuccess = false;
                }
            }
        } catch (Exception ex) {
            isSuccess = false;
            z = ex.toString();
        }

        return isSuccess;
    }



    public boolean GetUsernames() {
        try {
            _checkConnection = new checkConnection();

            Connection con = _checkConnection.Conn();

            String query = "Select EmpID from tbl_Users where Usertype != 'employee'";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<String> data = new ArrayList<String>();

            while (rs.next()) {
                String id = rs.getString("EmpID");

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

}
