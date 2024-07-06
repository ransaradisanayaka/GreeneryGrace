package lk.ijse.Greenery.bo;


import lk.ijse.Greenery.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBo extends SuperBO {
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    public  boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException;

  //  public  Employee searchById (String employeeId) throws SQLException, ClassNotFoundException;

    public  boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

}
