package demo1;

public class TeacherImpl implements ITeacher{
    @Override
    public void consultationWithParents() {
        System.out.println("Teacher met a parent for consultation");
    }

    @Override
    public void consultationWithStudents() {
        System.out.println("Teacher met a student for consultation");
    }
}
