package demo1;

public class School implements IParent, IStudent, ITeacher{
    @Override
    public void takeChildToSchool() {
        System.out.println("Parent took the child to school!");
    }

    @Override
    public void payTuition() {
        System.out.println("Parent paid tuition successfully");
    }

    @Override
    public void study() {
        System.out.println("Student studied for exam.");
    }

    @Override
    public void doHomework() {
        System.out.println("Student completed homework");
    }

    @Override
    public void consultationWithParents() {
        System.out.println("Teacher met a parent for consultation");
    }

    @Override
    public void consultationWithStudents() {
        System.out.println("Teacher met a student for consultation");
    }
}
