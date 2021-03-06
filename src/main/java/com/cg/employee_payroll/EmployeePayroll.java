package com.cg.employee_payroll;

import java.util.Date;

public class EmployeePayroll {

    int company_id;
    int emp_id;
    String name;
    String phone;
    String address;
    char gender;
    Date start;
    double basic_pay;
    double deductions;
    double taxable_pay;
    double tax;
    double net_pay;

    public EmployeePayroll() {
    }

    @Override
    public String toString() {
        return "EmployeePayroll { company_id : " + company_id + ", " +
                                " emp_id : " + emp_id + ", " +
                                " name : " + name + ", " +
                                " phone : " + phone + ", " +
                                " address : " + address + ", " +
                                " gender : " + gender + ", " +
                                " date : " + start + ", " +
                                " basic_pay : " + basic_pay + ", " +
                                " deductions : " + deductions + ", " +
                                " taxable_pay : " + taxable_pay + ", " +
                                " tax : " + tax + ", " +
                                " net_pay: " + net_pay + " }";
    }
}
