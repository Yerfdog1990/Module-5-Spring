package jdbc.batchinsert;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonBatchRepositoryImpl implements IPersonBatchRepository {
    private final JdbcTemplate jdbcTemplate;

    public PersonBatchRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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