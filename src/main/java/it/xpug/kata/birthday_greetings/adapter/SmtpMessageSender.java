package it.xpug.kata.birthday_greetings.adapter;

import it.xpug.kata.birthday_greetings.domain.BirthdayMessage;
import it.xpug.kata.birthday_greetings.domain.port.MessageSender;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpMessageSender implements MessageSender
{
  private String smtpHost;
  private int smtpPort;
  private String sender;

  public SmtpMessageSender(String smtpHost, int smtpPort, String sender)
  {
    this.smtpHost = smtpHost;
    this.smtpPort = smtpPort;
    this.sender = sender;
  }

  public void sendMessage(BirthdayMessage message) throws MessagingException
  {
    // Create a mail session
    java.util.Properties props = new java.util.Properties();
    props.put("mail.smtp.host", smtpHost);
    props.put("mail.smtp.port", "" + smtpPort);
    Session session = Session.getInstance(props, null);

    // Construct the message
    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(sender));
    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(message.getRecipient()));
    msg.setSubject(message.getSubject());
    msg.setText(message.getBody());

    // Send the message
    Transport.send(msg);
  }
}
