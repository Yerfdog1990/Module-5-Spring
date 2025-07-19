package demo;

public class StudentImpl implements IStudent{
    @Override
    public void study() {
        System.out.println("Student studied for exam.");
    }

    @Override
    public void doHomework() {
        System.out.println("Student completed homework");
    }
}
