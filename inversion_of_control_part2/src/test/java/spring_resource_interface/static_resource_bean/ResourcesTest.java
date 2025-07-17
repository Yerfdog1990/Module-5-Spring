package spring_resource_interface.static_resource_bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

public class ResourcesTest {
    private ResourceLoader resourceLoader;

    @BeforeEach
    void setResourceLoader() {
        this.resourceLoader = new AnnotationConfigApplicationContext("spring_resource_interface");
    }
    @Test
    void loadClassPathResource() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:file.txt");
        assertNotNull(resource);

        // Read the resource file
        String fileContent = resource.getContentAsString(Charset.defaultCharset());
        String expected = "What is Lorem Ipsum?\n" +
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n" +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n" +
                "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\n" +
                "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        assertEquals(expected, fileContent);
        System.out.println(fileContent);
    }
    @Test
    void loadFileSystemResource() throws IOException{
        Resource resource = resourceLoader.getResource("file:src/test/resources/file.txt");
        assertNotNull(resource);
        String contentAsString = resource.getContentAsString(Charset.defaultCharset());
        System.out.println(contentAsString);
    }
    @Test
    void loadInternetUrlResource() throws IOException{
        Resource resource = resourceLoader.getResource("https://www.lipsum.com/");
        assertInstanceOf(UrlResource.class, resource);
        String contentAsString = resource.getContentAsString(Charset.defaultCharset());
        System.out.println(contentAsString);
    }
    @Test
    void loadFileUrlResource() throws IOException{
        Resource resource = resourceLoader.getResource("file:/Users/godfrey/IdeaProjects/Module-5-Courses/Module-5-Spring/inversion_of_control_part2/src/test/resources/file.txt");
        assertInstanceOf(FileUrlResource.class, resource);
        String contentAsString = resource.getContentAsString(Charset.defaultCharset());
        System.out.println(contentAsString);
    }
    @Test
    void loadNonExistingResource() throws IOException{
        Resource resource = resourceLoader.getResource("file:/non-existing-resource");
        assertInstanceOf(UrlResource.class, resource);
        assertThrows(IOException.class, () -> resource.getContentAsString(Charset.defaultCharset()));
    }
}
