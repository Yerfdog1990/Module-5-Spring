package springtest.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springtest.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findUserById(int id) {
        String sql = "SELECT id, name FROM users WHERE id = " + id;
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
        if (users.size() > 1) throw new IllegalStateException("Expected just 1 or 0 users by id");
        if (users.size() == 1) {
            return Optional.of(users.get(0));
        }
        return Optional.empty();
    }

    @Override
    public List<User> findUsersByName(String name) {
        // FIXME Potential SQL injection attack here
        String sql = "SELECT id, name FROM users WHERE name = '" + name + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void newUser(User user) {
        String sql = "INSERT INTO users (id, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getName());
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int countUsers() {
        return jdbcTemplate.queryForObject("SELECT COUNT(id) FROM users", Integer.class);
    }

    @Override
    public void deleteUserByName(String name) {
        String sql = "DELETE FROM users WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    @Override
    public int findNextId() {
        String sql = "SELECT MAX(id) FROM users";
        Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
        return maxId != null ? maxId + 1 : 1;
    }
}
