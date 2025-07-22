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

        // Writing encrypted + compressed data
        SalaryManager manager = new SalaryManager(source);
        manager.save(salaryData);

        // Reading back
        String result = manager.load();
        System.out.println("Decrypted & Decompressed Data: " + result);
    }
}
