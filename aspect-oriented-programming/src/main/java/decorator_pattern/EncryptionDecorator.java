package decorator_pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class EncryptionDecorator extends DataSourceDecorator{
    public EncryptionDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void writeData(String data) {
        String encrypted = Base64.getEncoder().encodeToString(data.getBytes());
        super.writeData(encrypted);
    }

    @Override
    public String readData() {
        String data = super.readData();
        return new String(Base64.getDecoder().decode(data));
    }
}
