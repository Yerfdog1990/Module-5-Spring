package springxmlconfiguration;

import lombok.Data;

@Data
public class Employee {
  private String firstName = "Godfrey";
  private String lastName = "Ouma";

  public void display() {
    System.out.println("My name is " + firstName + " " + lastName);
  }
}
