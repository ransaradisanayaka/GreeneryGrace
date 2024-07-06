package lk.ijse.Greenery.bo.impl;

import lk.ijse.Greenery.bo.EmployeeBo;
import lk.ijse.Greenery.dao.DAOFactory;
import lk.ijse.Greenery.dao.custom.EmployeeDAO;
import lk.ijse.Greenery.dto.EmployeeDTO;
import lk.ijse.Greenery.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBo {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException{
        ArrayList<Employee> allEmployeeData = employeeDAO.getAll();
        ArrayList<EmployeeDTO> allDTOData= new ArrayList<>();
        for (Employee employee: allEmployeeData) {
            allDTOData.add(new EmployeeDTO(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAddress(),employee.getEmployeeContact(),employee.getDescription(),employee.getEmployeeNIC()));
        }
        return allDTOData;
    }

    public  boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException{
        return employeeDAO.delete(employeeId);
    }

   /*  public  Employee searchById (String employeeId) throws SQLException, ClassNotFoundException{
        return
     }*/

    public  boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException{
        return employeeDAO.save(new Employee(dto.getEmployeeId(), dto.getEmployeeName(), dto.getEmployeeAddress(), dto.getEmployeeContact(), dto.getDescription(), dto.getEmployeeNIC()));
    }

    public  boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException{
       return employeeDAO.update(new Employee(dto.getEmployeeId(), dto.getEmployeeName(), dto.getEmployeeAddress(), dto.getEmployeeContact(), dto.getDescription(), dto.getEmployeeNIC()));
    }

}
