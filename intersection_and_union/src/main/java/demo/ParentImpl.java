package demo;

public class ParentImpl implements IParent{
    @Override
    public void takeChildToSchool() {
        System.out.println("Parent took the child to school!");
    }

    @Override
    public void payTuition() {
        System.out.println("Parent paid tuition successfully");
    }
}
