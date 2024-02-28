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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import psproject_v5.exception.RepositoryException;

/**
 *
 * @author aleks
 */
public class Employee implements IEntity {

    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private Department department;
    private AcademicTitle academicTitle;
    private EducationTitle educationTitle;
    private Status status;

    public Employee() {
    }
    public Employee(String firstname, String lastname, LocalDate birthday, 
            Department department, AcademicTitle academicTitle, EducationTitle educationTitle, Status status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.department = department;
        this.academicTitle = academicTitle;
        this.educationTitle = educationTitle;
        this.status = status;
    }

    public String getTableName() {
        return AcademicTitle.class.getSimpleName();
    }
    public Employee(Long id, String firstname, String lastname, LocalDate birthday, Department department, AcademicTitle academicTitle, EducationTitle educationTitle, Status status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.department = department;
        this.academicTitle = academicTitle;
        this.educationTitle = educationTitle;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }

    public EducationTitle getEducationTitle() {
        return educationTitle;
    }

    public void setEducationTitle(EducationTitle educationTitle) {
        this.educationTitle = educationTitle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
//        return id+" "+firstname+" "+birthday+" "+department+" "+academicTitle+" "+educationTitle+" "+status;
        return id + " " + firstname + " " + birthday + " " + department + " " + academicTitle + " " + educationTitle + " " + status + "";
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Employee other = (Employee) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO employee (firstname,lastname,birthday,department,academicTitle,educationTitle,status)"
                + " VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT employee.id, employee.firstname, employee.lastname, employee.birthday, employee.status, "
                + "academicTitle.id, academicTitle.name,"
                + "educationTitle.id, educationTitle.name,"
                + "department.id, department.name,department.shortName FROM "
                + "employee "
                + "INNER JOIN academicTitle ON employee.academicTitle=academicTitle.id "
                + "LEFT JOIN educationTitle ON employee.educationTitle=educationTitle.id "
                + "INNER JOIN department ON employee.department=department.id";
                  
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE employee SET firstname = ?, lastname = ?, birthday = ?,"
                + "department = ?, academicTitle = ?, educationTitle = ?, status = ? ";
    }

    @Override
    public String getWhereCondition() {
        return "WHERE employee.id = " + id;
    }

    @Override
    public IEntity getDomainObjectFromRS(ResultSet rs) throws SQLException {
        return new Employee(rs.getLong("employee.id"),
                rs.getString("employee.firstname"),
                rs.getString("employee.lastname"),
                rs.getDate("employee.birthday").toLocalDate(),
                new Department(rs.getLong("department.id"), rs.getString("department.name"), rs.getString("department.shortName")),
                new AcademicTitle(rs.getLong("academicTitle.id"), rs.getString("academicTitle.name")),
                new EducationTitle(rs.getLong("educationTitle.id"), rs.getString("educationTitle.name")),
                Status.valueOf(rs.getString("employee.status")));
    }


    @Override
    public void fillPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, this.getFirstname());
        ps.setString(2, this.getLastname());
        ps.setDate(3, Date.valueOf(this.getBirthday()));
        ps.setLong(4, this.getDepartment().getId());
        ps.setLong(5, this.getAcademicTitle().getId());

        if (this.getEducationTitle() != null) {
            ps.setLong(6, this.getEducationTitle().getId());
        } else {
            ps.setNull(6, Types.BIGINT);
        }

        ps.setString(7, this.getStatus().toString());
    }

    @Override
    public void setId(Object id) {
        if (id instanceof Long) {
            setId((Long) id);
        }
    }

    @Override
    public void checkSimpleValueRestrictions() throws Exception {
        Pattern pattern = Pattern.compile("[A-Z][a-z]{2,20}");
        Matcher matcher = pattern.matcher(firstname);
        boolean matchFound = matcher.matches();
        if (matchFound) {
            System.out.println("unos je ispravan: " + firstname);
        } else {
            throw new RepositoryException("Unos nije ispravan!!! [A-Z][a-z]{2,20}");
        }
        pattern = Pattern.compile("[A-Za-z]{2,10}");
        matcher = pattern.matcher(lastname);
        matchFound = matcher.matches();
        if (matchFound) {
            System.out.println("unos je ispravan: " + lastname);
        } else {
            throw new RepositoryException("Unos nije ispravan!!! [A-Za-z]{2,10}");
        }
        
    }

    @Override
    public void checkComplexValueRestrictions() throws Exception {

    }

    @Override
    public void checkStructuralRestrictions() throws Exception {
        if(department == null || academicTitle == null || birthday == null)
            throw new RepositoryException("Nisu zadovoljena strukturna ogranicenja!");
    }

}
