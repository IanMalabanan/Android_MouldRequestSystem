package com.example.mouldrequestsystem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBOperator {

    checkConnection _checkConnection;

    CallableStatement _callableStatement;

    public Boolean isSuccess = false;

    public String z = "";

    ArrayList<ClassOperatorModel> data = new ArrayList<ClassOperatorModel>();

    public String[] array;

    ClassOperatorDT classOperatorDT = new ClassOperatorDT();


    public boolean SelectALLOperator() {
        try {
            _checkConnection = new checkConnection();

            Connection con = _checkConnection.Conn();

            String query = "call SelectALLOperator";

            CallableStatement callableStatement = con.prepareCall(query);

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {

                ClassOperatorModel itemPanel = new ClassOperatorModel();

                itemPanel.setEmpID(rs.getString("empID"));

                itemPanel.setEmpName(rs.getString("empName"));

                data.add(itemPanel);

                classOperatorDT.setOperatorList(data);

                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

}
