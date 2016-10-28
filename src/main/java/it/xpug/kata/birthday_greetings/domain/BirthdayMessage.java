package it.xpug.kata.birthday_greetings.domain;

public class BirthdayMessage
{
  private final Employee employee;

  public BirthdayMessage(Employee employee)
  {
    this.employee = employee;
  }

  public String getRecipient()
  {
    return employee.getEmail();
  }

  public String getBody()
  {
    return "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
  }

  public String getSubject()
  {
    return "Happy Birthday!";
  }
}
