package com.example.EmployeeManagement.EmployeeManagement.Exceptions;

public class EmptyRecordException extends RuntimeException
{
    public EmptyRecordException(String message) {
        super(message);
    }
}
