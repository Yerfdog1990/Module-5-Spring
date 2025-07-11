package method3.model.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import method3.model.entity.Students;

public class InMemoryStudentRepo implements IStudentRepo {
  private final Collection<Students> students = new ArrayList<>();

  public List<Students> getAllStudents() {
    return List.copyOf(students);
  }

  @Override
  public Students getStudentByEmail(String email) {
    List<Students> studentsList =
        students.stream().filter(s -> s.getEmail().equals(email)).toList();
    if (studentsList.size() > 1) {
      throw new IllegalStateException("Duplicate email found");
    }
    if (studentsList.size() == 1) {
      return studentsList.get(0);
    }
    return null;
  }

  @Override
  public void addStudent(String name, String email) {
    students.add(new Students(name, email));
  }
}
