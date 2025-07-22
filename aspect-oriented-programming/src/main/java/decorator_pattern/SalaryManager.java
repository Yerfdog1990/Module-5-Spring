package decorator_pattern;

public class SalaryManager {
    private DataSource dataSource;

    public SalaryManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(String salaryRecords){
        dataSource.writeData(salaryRecords);
    }

    public String load(){
        return dataSource.readData();
    }
}
