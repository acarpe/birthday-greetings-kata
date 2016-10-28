package it.xpug.kata.birthday_greetings.domain.port;

import it.xpug.kata.birthday_greetings.domain.BirthdayMessage;

import javax.mail.MessagingException;

public interface MessageSender
{
  void sendMessage(BirthdayMessage message) throws MessagingException;
}
