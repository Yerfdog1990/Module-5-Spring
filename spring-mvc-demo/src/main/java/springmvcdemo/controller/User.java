package springmvcdemo.controller;

public class User {
  private String name;
  private int age;
  private String maritalStatus;

  public User(String name, int age, String maritalStatus) {
    this.name = name;
    this.age = age;
    this.maritalStatus = maritalStatus;
  }

  public User() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(String maritalStatus) {
    this.maritalStatus = maritalStatus;
  }
}
