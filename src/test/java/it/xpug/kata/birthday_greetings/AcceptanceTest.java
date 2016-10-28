package it.xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import it.xpug.kata.birthday_greetings.adapter.FileEmployeeRepository;
import it.xpug.kata.birthday_greetings.adapter.SmtpMessageSender;
import it.xpug.kata.birthday_greetings.domain.BirthdayService;
import it.xpug.kata.birthday_greetings.domain.XDate;
import org.junit.*;

import javax.mail.Address;
import javax.mail.internet.MimeMessage;

public class AcceptanceTest {

	private static final int NONSTANDARD_PORT = 3025;
	private BirthdayService birthdayService;
	@Rule
	public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.SMTP);

	@Before
	public void setUp() throws Exception {
		birthdayService = new BirthdayService(new FileEmployeeRepository("employee_data.txt"),
			new SmtpMessageSender("localhost", NONSTANDARD_PORT, "sender@here.com"));
	}


	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

		birthdayService.sendGreetings(new XDate("2008/10/08"));

		MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
		assertEquals("message not sent?", 1, receivedMessages.length);
		MimeMessage message = receivedMessages[0];
		assertEquals("Happy Birthday, dear John!", GreenMailUtil.getBody(message));
		assertEquals("Happy Birthday!", message.getSubject());
		Address[] recipients = message.getRecipients(MimeMessage.RecipientType.TO);
		assertEquals(1, recipients.length);
		assertEquals("john.doe@foobar.com", recipients[0].toString());
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		birthdayService.sendGreetings(new XDate("2008/01/01"));

		assertEquals("what? messages?", 0, greenMail.getReceivedMessages().length);
	}
}
