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
public class Response implements Serializable{
    private Object result;
    private Exception exception;
    private Operation operation;
    
    public Response(Object result) {
        this.result = result;
    }

    public Response() {
    }

    public Response(Exception exception) {
        this.exception = exception;
    }

    public Response(Object result, Exception exception) {
        this.result = result;
        this.exception = exception;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
    
    
    
    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RESPONSE: "+result+"Operation:"+operation;
    }
    
}
