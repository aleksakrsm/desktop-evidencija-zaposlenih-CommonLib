/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.communication;

import java.io.Serializable;

/**
 *
 * @author aleks
 */
public enum Operation implements Serializable{
    GET_ALL_EMPLOYEES,
    GET_ALL_EMPLOYEES_FILTERED,
    GET_ALL_DEPARTMENTS,
    GET_ALL_ACADEMIC_TITLES,
    GET_ALL_EDUCATION_TITLES,
    GET_EMPLOYEE_TITLE_HISTORY,
    
    ADD_ENTITY,
    UPDATE_ENTITY,
    DELETE_ENTITY,
    SAVE_OR_UPDATE_ELEMENTS,
    
    NEW_ENTITY_NOTIFICATION,
    DELETE_ENTITY_NOTIFICATION,
    UPDATE_ENTITY_NOTIFICATION,
    SAVE_OR_UPDATE_ELEMENTS_NOTIFICATION,
    
    TERMINATE,  //server se gasi
    EXIT //klijent se odjavljuje
}
