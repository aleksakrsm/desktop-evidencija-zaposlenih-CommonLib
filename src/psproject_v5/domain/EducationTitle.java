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

/**
 *
 * @author aleks
 */
public class EducationTitle implements IEntity{

    private Long id;
    private String name;

    public EducationTitle() {
    }
    public EducationTitle(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public EducationTitle(String name) {
        this.name = name;
    }


    public String getTableName1() {
        return EducationTitle.class.getSimpleName();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final EducationTitle other = (EducationTitle) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO educationTitle (name) VALUES(?)";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id,name FROM educationTitle";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE educationTitle SET name = ?";
    }

    @Override
    public String getWhereCondition() {
        return "WHERE educationTitle.id = "+id;
    }

    @Override
    public IEntity getDomainObjectFromRS(ResultSet rs) throws SQLException{
        return new EducationTitle(rs.getLong("id"), rs.getString("name"));        
    }

    @Override
    public void fillPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, name);
    }

    @Override
    public void setId(Object id) {
        if(id instanceof Long)
            setId((Long)id);
    }

    @Override
    public void checkSimpleValueRestrictions() throws Exception {
        Pattern pattern = Pattern.compile("[A-Z][a-z]{2,20}(\s[A-Z]{0,1}[a-z]{1,20})*");
        Matcher matcher = pattern.matcher(name);
        boolean matchFound = matcher.matches();
        if (matchFound) {
            System.out.println("unos je ispravan: " + name);
        } else {
            throw new RepositoryException("Unos nije ispravan!!! [A-Z][a-z]{2,20}(\s[A-Z]{0,1}[a-z]{1,20})*");
        }
    }

    @Override
    public void checkComplexValueRestrictions() throws Exception {

    }

    @Override
    public void checkStructuralRestrictions() throws Exception {

    }
    
}
