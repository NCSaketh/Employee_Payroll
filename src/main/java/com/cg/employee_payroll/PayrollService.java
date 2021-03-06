package com.cg.employee_payroll;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class PayrollService {

    HashMap<Integer, EmployeePayroll> employeePayrollMap = new HashMap<>();

    private static Connection getConnection() throws IOException, SQLException {
        FileInputStream fin = new FileInputStream("C:\\Users\\Nc Saketh\\intellij-workspace\\Employee_Payroll\\config.properties");
        Properties prop = new Properties();
        prop.load(fin);
        return DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
    }
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        try {
            con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from employee");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void getRecordsFromDB(PayrollService payrollService, Statement stmt) throws SQLException {
        String query = "select * from employee e, payroll p " + "where e.emp_id = p.emp_id";
        ResultSet resultSet = stmt.executeQuery(query);
        while (resultSet.next()) {
            EmployeePayroll tempLoopObject = new EmployeePayroll();
            tempLoopObject.company_id = resultSet.getInt("company_id");
            tempLoopObject.emp_id = resultSet.getInt("emp_id");
            tempLoopObject.name = resultSet.getString("name");
            tempLoopObject.phone = resultSet.getString("phone");
            tempLoopObject.address = resultSet.getString("address");
            tempLoopObject.gender = resultSet.getString("gender").charAt(0);
            tempLoopObject.start = resultSet.getDate("start");
            tempLoopObject.basic_pay = resultSet.getDouble("basic_pay");
            tempLoopObject.deductions = resultSet.getDouble("deductions");
            tempLoopObject.taxable_pay = resultSet.getDouble("taxable_pay");
            tempLoopObject.tax = resultSet.getDouble("tax");
            tempLoopObject.net_pay = resultSet.getDouble("net_pay");
            payrollService.employeePayrollMap.put(tempLoopObject.emp_id, tempLoopObject);
            System.out.println(tempLoopObject);
        }
    }
}