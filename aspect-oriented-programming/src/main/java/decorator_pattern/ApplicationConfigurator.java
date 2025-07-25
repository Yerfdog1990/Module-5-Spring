package decorator_pattern;

public class ApplicationConfigurator {
    public static void main(String[] args) {
        boolean enabledEncryption = true;
        boolean enabledCompression = true;

        String salaryData = "Name:John;Salary:50000";

        DataSource source = new FileDataSource("salary.txt");

        if (enabledEncryption) {
            source = new EncryptionDecorator(source);
        }

        if (enabledCompression) {
            source = new CompressionDecorator(source);
        }

        SalaryManager manager = getSalaryManager(source, salaryData);

        extracted(manager);
    }

    private static void extracted(SalaryManager manager) {
        // Reading back
        String result = manager.load();
        System.out.println("Decrypted & Decompressed Data: " + result);
    }

    private static SalaryManager getSalaryManager(DataSource source, String salaryData) {
        // Writing encrypted + compressed data
        SalaryManager manager = new SalaryManager(source);
        manager.save(salaryData);
        return manager;
    }
}
