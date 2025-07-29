package springtxdemo.rowmapper;


import jdbc.batchinsert.PersonEntity;
import jdbc.rowmapper.PersonMapperRepositoryImpl;
import jdbc.rowmapper.RowMapperConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {RowMapperConfig.class})
public class PersonMapperRepositoryTest {
    @Autowired
    private PersonMapperRepositoryImpl personMapperRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS person");
        jdbcTemplate.execute("CREATE TABLE person (id INT PRIMARY KEY, name VARCHAR(255))");
    }

    @Test
    @Commit
    void testFindByName() {
        // Insert test data
        List<PersonEntity> people = List.of(
                new PersonEntity(1, "Tom"),
                new PersonEntity(2, "Jerry")
        );
        personMapperRepository.batchInsert(people);

        // Confirm that data is inserted correctly.
        List<PersonEntity> allPeople = personMapperRepository.findAll();
        assertEquals(2, allPeople.size(), "The number of inserted entries should be 2.");

        // Query for "Tom"
        List<PersonEntity> name = personMapperRepository.findByName("Tom");
        assertEquals(1, name.size(), "Should return exactly one record for name 'Tom'.");

        // Validate the first entry
        PersonEntity personEntity = name.get(0);
        assertEquals("Tom", personEntity.getName(), "The name of the retrieved entity should match 'Tom'.");
        assertEquals(1, personEntity.getId(), "The retrieved entity should have the correct ID for 'Tom'.");
    }
}