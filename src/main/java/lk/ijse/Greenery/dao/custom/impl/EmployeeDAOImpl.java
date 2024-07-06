package lk.ijse.Greenery.dao.custom.impl;


import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.custom.EmployeeDAO;
import lk.ijse.Greenery.dto.EmployeeDTO;
import lk.ijse.Greenery.dto.StockDTO;
import lk.ijse.Greenery.entity.Employee;
import lk.ijse.Greenery.model.StockDTo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployee = new ArrayList<>();

        ResultSet rst = SQLUtil.execute( "SELECT * FROM employee");
        while (rst.next()) {

            allEmployee.add(new Employee(rst.getString("employeeId"),rst.getString("employeeName"),rst.getString("employeeAddress"),rst.getString("employeeContact"),rst.getString("description"),rst.getString("employeeNIC")));
        }
        return allEmployee;
    }

    public  boolean delete(String employeeId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM employee WHERE employeeId=?",employeeId);

    }


    public Employee search(String employeeId) throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("SELECT * FROM employee WHERE employeeId=?",employeeId+"");
        rst.next();

        return new Employee();
    }

    public  boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)",entity.getEmployeeId(),entity.getEmployeeName(),entity.getEmployeeAddress(),entity.getEmployeeContact(),entity.getDescription(),entity.getEmployeeNIC());
    }

    public  boolean update(Employee  entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE employee SET employeeName=?, employeeAddress=?, employeeContact=?,description=?,employeeNIC=? WHERE employeeId=?",entity.getEmployeeName(),entity.getEmployeeAddress(),entity.getEmployeeContact(),entity.getDescription(),entity.getEmployeeNIC(),entity.getEmployeeId());
    }

}
