package demo;

public class MainClass {
    // Demonstrate intersection
    static <T extends IStudent & ITeacher & IParent> void demonstrateIntersection(T entity){
        entity.consultationWithStudents();
        entity.doHomework();
        entity.consultationWithParents();
        entity.study();
        entity.payTuition();
        entity.takeChildToSchool();
    }
    // Demonstrate union
    static <T> void demonstrateUnion(T entity){
        if(entity instanceof IStudent){
            ((IStudent) entity).study();
            ((IStudent) entity).doHomework();
        }
        if(entity instanceof ITeacher){
            ((ITeacher) entity).consultationWithParents();
            ((ITeacher) entity).consultationWithStudents();
        }
        if(entity instanceof IParent){
            ((IParent) entity).payTuition();
            ((IParent) entity).takeChildToSchool();
        }
    }
    // Main method
    public static void main(String[] args) {
        System.out.println("Calling methods from Parent class");
        demonstrateUnion(new StudentImpl());
        System.out.println("Calling methods from Teacher class");
        demonstrateUnion(new TeacherImpl());
        System.out.println("Calling methods from Parent and Teacher class");
        demonstrateUnion(new ParentImpl());
        System.out.println("Calling methods from School class");
        demonstrateIntersection(new School());
    }
}
