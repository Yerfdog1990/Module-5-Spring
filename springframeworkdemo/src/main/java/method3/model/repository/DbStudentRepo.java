package method3.model.repository;

import method3.model.entity.Students;

public class DbStudentRepo implements IStudentRepo {
  private final String existingStudent;
  private final Students newStudent;

  // Constructor
  public DbStudentRepo() {
    existingStudent = "joe@example.com";
    newStudent = new Students("John", existingStudent);
  }

  @Override
  public void addStudent(String name, String email) {
    System.out.printf("Student with name %s and email %s has been added.", name, email);
  }

  @Override
  public Students getStudentByEmail(String email) {
    if (email.equals(existingStudent)) {
      System.out.printf("Student with email %s found.", email);
      return newStudent;
    }
    return null;
  }
}
