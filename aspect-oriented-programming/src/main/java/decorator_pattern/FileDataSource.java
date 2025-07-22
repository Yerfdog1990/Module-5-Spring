package decorator_pattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileDataSource implements DataSource{
    String fileName;

    public FileDataSource(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void writeData(String data) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException("Error writing data to file: " +e.getMessage());
        }
    }

    @Override
    public String readData() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            return bufferedReader.readLine();
        }catch(Exception e){
            throw new RuntimeException("Error reading data from file: " +e.getMessage());
        }
    }
}
