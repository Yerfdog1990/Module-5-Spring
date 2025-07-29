package jdbc.rowmapper;

import jdbc.batchinsert.PersonEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonMapperRepositoryImpl implements IPersonMapperRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PersonMapperRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<PersonEntity> findAll(){
    return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(PersonEntity.class));
    }
    @Override
    public List<PersonEntity> findByName(String name) {
        String sql = "SELECT id AS id, name AS name FROM person WHERE LOWER(name) = LOWER(:name)"; // Case-insensitive query
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", name);

        return namedParameterJdbcTemplate.query(sql, parameterSource, new BeanPropertyRowMapper<>(PersonEntity.class));
    }
    @Override
    public int[] batchInsert(List<PersonEntity> people) {
        String sql = "INSERT INTO person (id, name) VALUES (?, ?)";
        BatchPreparedStatementSetter statementSetter = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                PersonEntity personEntity = people.get(i);
                ps.setInt(1, personEntity.getId());
                ps.setString(2, personEntity.getName());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        };
        return jdbcTemplate.batchUpdate(sql, statementSetter);
    }
}
