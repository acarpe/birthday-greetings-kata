package it.xpug.kata.birthday_greetings.domain;

import it.xpug.kata.birthday_greetings.domain.port.MessageSender;
import it.xpug.kata.birthday_greetings.domain.port.EmployeeRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;

public class BirthdayService {

	private EmployeeRepository employeeRepository;
	private MessageSender messageSender;

	public BirthdayService(EmployeeRepository employeeRepository, MessageSender messageSender) {
		this.employeeRepository = employeeRepository;
		this.messageSender = messageSender;
	}

	public void sendGreetings(XDate xDate) throws IOException, ParseException, MessagingException {
		List<Employee> employees = employeeRepository.getAllEmployees();

		for (Employee employee: employees)
		{
			if (employee.isBirthday(xDate)) {
				BirthdayMessage message = new BirthdayMessage(employee);
				messageSender.sendMessage(message);

			}
		}
	}

}
