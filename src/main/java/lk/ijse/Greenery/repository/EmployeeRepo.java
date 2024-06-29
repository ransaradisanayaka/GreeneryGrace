package lk.ijse.Greenery.repository;


import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.model.Employee;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static Object employeeId;


    public static List<Employee>getAll() throws SQLException {
        String sql = "SELECT * FROM employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String employeeId = resultSet.getString(1);
            String employeeName = resultSet.getString(2);
            String employeeAddress = resultSet.getString(3);
            String employeeContact = resultSet.getString(4);
            String description =resultSet.getString(5);
            String employeeNIC = resultSet.getString(6);



            Employee employee = new Employee(employeeId, employeeName, employeeAddress, employeeContact,description,employeeNIC);
            employeeList.add(employee);
        }
        return employeeList;
    }
            public static boolean delete(String employeeId) throws SQLException {
                String sql = "DELETE FROM employee WHERE employeeId=?";

                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);

                pstm.setObject(1, employeeId);

                return pstm.executeUpdate() > 0;

            }





            public static Employee searchById (String employeeId) throws SQLException {
                String sql = "SELECT * FROM employee WHERE employeeId=?";

                PreparedStatement pstm = DbConnection.getInstance().getConnection()
                        .prepareStatement(sql);

                pstm.setObject(1, employeeId);
                ResultSet resultSet = pstm.executeQuery();

                Employee employee = null;


                if (resultSet.next()) {
                    String Id = resultSet.getString(1);
                    String employeeName = resultSet.getString(2);
                    String employeeAddress = resultSet.getString(3);
                    String employeeContact = resultSet.getString(4);
                    String description = resultSet.getString(5);
                    String employeeNIC = resultSet.getString(6);




                    employee = new Employee(Id, employeeName, employeeAddress, employeeContact,description,employeeNIC);
                }
                return employee;
            }

    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, employee.getEmployeeId());
        pstm.setObject(2, employee.getEmployeeName());
        pstm.setObject(3, employee.getEmployeeAddress());
        pstm.setObject(4, employee.getEmployeeContact());
        pstm.setObject(5, employee.getDescription());
        pstm.setObject(6, employee.getEmployeeNIC());



        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET employeeName=?, employeeAddress=?, employeeContact=?,description=?,employeeNIC=? WHERE employeeId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);


        pstm.setObject(1, employee.getEmployeeName());
        pstm.setObject(2, employee.getEmployeeAddress());
        pstm.setObject(3, employee.getEmployeeContact());
        pstm.setObject(4, employee.getDescription());
        pstm.setObject(5, employee.getEmployeeNIC());
        pstm.setObject(6, employee.getEmployeeId());



        return pstm.executeUpdate() > 0;
    }


}

