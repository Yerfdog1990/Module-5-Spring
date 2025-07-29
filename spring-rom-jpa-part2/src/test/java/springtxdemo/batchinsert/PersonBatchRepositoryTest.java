package springtxdemo.batchinsert;

import jdbc.batchinsert.IPersonBatchRepository;
import jdbc.batchinsert.JdbcConfigDemo;
import jdbc.batchinsert.PersonEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {JdbcConfigDemo.class})
public class PersonBatchRepositoryTest {
    @Autowired
    private IPersonBatchRepository personBatchRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp(){
        jdbcTemplate.execute("DROP TABLE IF EXISTS person");
        jdbcTemplate.execute("CREATE TABLE person (id INT PRIMARY KEY, name VARCHAR(255))");
    }
    @Test
    @Commit
    void testBatchInsert(){
        List<PersonEntity> people = List.of(new PersonEntity(1, "Tom"), new PersonEntity(2, "Jerry"));
        personBatchRepository.batchInsert(people);

        Long numberOfPersons = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM person", Long.class);
        assertEquals(2, numberOfPersons);
    }
}
