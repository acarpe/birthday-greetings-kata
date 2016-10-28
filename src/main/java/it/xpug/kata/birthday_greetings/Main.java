package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.adapter.FileEmployeeRepository;
import it.xpug.kata.birthday_greetings.adapter.SmtpMessageSender;
import it.xpug.kata.birthday_greetings.domain.BirthdayService;
import it.xpug.kata.birthday_greetings.domain.XDate;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;
import javax.mail.internet.*;

public class Main {

	public static void main(String[] args) throws AddressException, IOException, ParseException, MessagingException {
		BirthdayService service = new BirthdayService(new FileEmployeeRepository("employee_data.txt"),
			new SmtpMessageSender("localhost", 25, "sender@here.com"));
		service.sendGreetings(new XDate());
	}

}
