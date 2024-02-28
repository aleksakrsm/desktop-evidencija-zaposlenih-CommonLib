/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.domain;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Objects;
import psproject_v5.exception.RepositoryException;

/**
 *
 * @author aleks
 */
public class EmployeeAcademicTitle implements IEntity {

    private Employee employee;
    private AcademicTitle academicTitle;
    private LocalDate beginDate;
    private LocalDate endDate;

    public EmployeeAcademicTitle() {
    }
    public EmployeeAcademicTitle(Employee employee, AcademicTitle academicTitle,
            LocalDate beginDate, LocalDate endDate) {
        this.employee = employee;
        this.academicTitle = academicTitle;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public String getTableName() {
        return AcademicTitle.class.getSimpleName();
    }

    public EmployeeAcademicTitle(Employee employee, AcademicTitle academicTitle, LocalDate beginDate) {
        this.employee = employee;
        this.academicTitle = academicTitle;
        this.beginDate = beginDate;
    }


    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return employee.getFirstname() + " is " + academicTitle + " Start date:" + beginDate + " - " + endDate+ "\n";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeAcademicTitle other = (EmployeeAcademicTitle) obj;
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        return Objects.equals(this.academicTitle, other.academicTitle);
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO employeeacademictitle(employee,academicTitle,beginDate,endDate) VALUES(?,?,?,?)";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT employeeacademictitle.employee,employeeacademictitle.academicTitle,"
                + "employeeacademictitle.beginDate,employeeacademictitle.endDate, academicTitle.name, "
                + "employee.firstname, employee.lastname, employee.birthday,employee.department, employee.educationTitle, employee.status, "
                + "educationTitle.name, department.name, department.shortName "
                + "FROM employeeacademictitle "
                + "INNER JOIN academicTitle "
                + "ON  employeeacademictitle.academicTitle = academictitle.id "
                + "INNER JOIN employee ON employeeacademictitle.employee = employee.id "
                + "LEFT JOIN educationTitle ON employee.educationTitle = educationTitle.id "
                + "INNER JOIN department ON employee.department = department.id ";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE employeeAcademicTitle SET employee = ?,academicTitle = ?,beginDate = ?, endDate = ? ";
    }

    @Override
    public String getWhereCondition() {
        return "WHERE employeeacademictitle.employee = " + employee.getId() + " AND "
                + "employeeacademictitle.academicTitle = " + academicTitle.getId();
    }

    @Override
    public IEntity getDomainObjectFromRS(ResultSet rs) throws SQLException {
        EmployeeAcademicTitle eat = null;
        AcademicTitle academicTitle = new AcademicTitle(rs.getLong("employeeacademictitle.academicTitle"), rs.getString("academicTitle.name"));
        EducationTitle educationTitle = new EducationTitle(rs.getLong("employee.educationTitle"), rs.getString("educationTitle.name"));
        Department department = new Department(rs.getLong("employee.department"), rs.getString("department.name"), rs.getString("department.shortName"));
        Employee e = new Employee(rs.getLong("employeeacademictitle.employee"), rs.getString("employee.firstname"),
                rs.getString("employee.lastname"), rs.getDate("employee.birthday").toLocalDate(),
                department, academicTitle, educationTitle, Status.valueOf(rs.getString("employee.status")));
        Date beginD = rs.getDate("employeeacademictitle.beginDate");
        LocalDate begin = null;
        if (beginD != null) {
            begin = beginD.toLocalDate();
        } else {
            begin = null;
        }
        Date endD = rs.getDate("employeeacademictitle.endDate");
        LocalDate end = null;
        if (endD != null) {
            end = endD.toLocalDate();
        } else {
            end = null;
        }
        eat = new EmployeeAcademicTitle(e, academicTitle, begin, end);
        return eat;
    }

    @Override
    public void fillPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setLong(1, this.getEmployee().getId());
        ps.setLong(2, this.getAcademicTitle().getId());
        if (this.getBeginDate() != null) {
            ps.setDate(3, Date.valueOf(this.getBeginDate()));
        } else {
            ps.setNull(3, Types.DATE);
        }
        if (this.getEndDate() != null) {
            ps.setDate(4, Date.valueOf(this.getEndDate()));
        } else {
            ps.setNull(4, Types.DATE);
        }
    }

    @Override
    public void setId(Object id) {
        return;
    }
    
    @Override
    public Object getId() {
        return null;
    }

    @Override
    public Object getAutoGeneratedID(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public void checkSimpleValueRestrictions() throws Exception {

    }

    @Override
    public void checkComplexValueRestrictions() throws Exception {

    }

    @Override
    public void checkStructuralRestrictions() throws Exception {
        if(employee == null || academicTitle == null)
            throw new RepositoryException("Nisu zadovoljena strukturna ogranicenja!");
    }

}
