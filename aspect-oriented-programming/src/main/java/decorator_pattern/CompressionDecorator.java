package decorator_pattern;

import javax.imageio.IIOException;
import java.io.*;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionDecorator extends DataSourceDecorator{
    public CompressionDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void writeData(String data) {
        String compressed = compress(data);
        super.writeData(compressed);
    }

    @Override
    public String readData() {
        String data = super.readData();
        return decompress(data);
    }

    // Method to compress data
    private String compress(String stringData) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(bos)) {
            // Write data and ensure GZIP stream is properly flushed
            gzip.write(stringData.getBytes());
            gzip.finish();
            return Base64.getEncoder().encodeToString(bos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Compression failed: ", e);
        }
    }

    // Method to decompress data
    private String decompress(String compressedData) {
        byte[] data = Base64.getDecoder().decode(compressedData);
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             GZIPInputStream gzip = new GZIPInputStream(bis);
             InputStreamReader isr = new InputStreamReader(gzip);
             BufferedReader br = new BufferedReader(isr);
             StringWriter sw = new StringWriter()) {

            // Read all decompressed lines and build the string
            String line;
            while ((line = br.readLine()) != null) {
                sw.write(line); // Add the line to the StringWriter
            }
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("Decompression failed", e);
        }
    }

}
