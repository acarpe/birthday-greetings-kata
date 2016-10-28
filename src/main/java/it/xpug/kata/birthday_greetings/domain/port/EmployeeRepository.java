package it.xpug.kata.birthday_greetings.domain.port;

import it.xpug.kata.birthday_greetings.domain.Employee;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface EmployeeRepository
{
  List<Employee> getAllEmployees() throws IOException, ParseException;
}
