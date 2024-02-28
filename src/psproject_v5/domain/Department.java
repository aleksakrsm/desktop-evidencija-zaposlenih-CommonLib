/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import psproject_v5.exception.RepositoryException;
import psproject_v5.exception.ValidatorException;

/**
 *
 * @author aleks
 */
public class Department implements IEntity {

    private Long id;
    private String name;
    private String shortName;

    public Department() {
    }
    public Department(Long id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
    }
    
    
    public Department(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return shortName;
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
        final Department other = (Department) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO department (name,shortname) VALUES (?,?)";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id,name,shortName FROM department";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE department SET name = ?, shortName = ?";
    }

    @Override
    public String getWhereCondition() {
        return "WHERE department.id = "+id;
    }

    @Override
    public IEntity getDomainObjectFromRS(ResultSet rs) throws SQLException {
        return new Department(rs.getLong("id"), rs.getString("name"), rs.getString("shortName"));
    }

    @Override
    public void fillPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, this.getName());
        ps.setString(2, this.getShortName());
    }

    @Override
    public void setId(Object id) {
        if (id instanceof Long) {
            setId((Long) id);
        }
    }

    @Override
    public void checkSimpleValueRestrictions() throws Exception {
        Pattern pattern = Pattern.compile("[[A-Za-z]{1,15}\\s]+");
        Matcher matcher = pattern.matcher(name);
        boolean matchFound = matcher.matches();
        if (matchFound) {
            System.out.println("unos je ispravan: " + name);
        } else {
            throw new RepositoryException("Unos nije ispravan!!! [[A-Za-z]{1,15}\\s]+");
        }
        pattern = Pattern.compile("[A-Za-z]{2,10}");
        matcher = pattern.matcher(shortName);
        matchFound = matcher.matches();
        if (matchFound) {
            System.out.println("unos je ispravan: " + shortName);
        } else {
            throw new RepositoryException("Unos nije ispravan!!! [A-Za-z]{2,10}");
        }
        
    }

    @Override
    public void checkComplexValueRestrictions() throws Exception {

    }

    @Override
    public void checkStructuralRestrictions() throws Exception {
        
    }

}
