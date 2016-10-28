package it.xpug.kata.birthday_greetings.adapter;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.port.EmployeeRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileEmployeeRepository implements EmployeeRepository
{
  private String fileName;

  public FileEmployeeRepository(String fileName)
  {
    this.fileName = fileName;
  }

  public List<Employee> getAllEmployees() throws IOException, ParseException
  {
    List<Employee> employees = new ArrayList<Employee>();
    BufferedReader in = new BufferedReader(new FileReader(fileName));
    String str = "";
    str = in.readLine(); // skip header
    while ((str = in.readLine()) != null) {
String[] employeeData = str.split(", ");
Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
employees.add(employee);
}
    return employees;
  }
}
