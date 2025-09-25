package service;

import dto.StudentDTO;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserDetailsServiceImpl(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        String sql = "SELECT email, password, role FROM students WHERE LOWER(email) = LOWER(?)";
        try {
            return Objects.requireNonNull(jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> User.withUsername(rs.getString("email"))
                            .password(rs.getString("password"))
                            .roles(rs.getString("role"))
                            .build(),
                    usernameOrEmail
            ));
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public void createUser(StudentDTO dto) {
        // encode the password
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        // default role if not provided via RegistrationDTO (RegistrationDTO has no role field)
        String role = "USER";
        // generate a new id since the table has NOT NULL id without default/sequence
        Integer nextId = jdbcTemplate.queryForObject("SELECT COALESCE(MAX(id), 0) + 1 FROM students", Integer.class);
        // determine email: use provided or auto-generate from username, then lowercase
        String email = (dto.getEmail() == null || dto.getEmail().trim().isEmpty())
                ? (dto.getUsername() + "@example.com")
                : dto.getEmail();
        email = email.toLowerCase();
        String sql = "INSERT INTO students (id, username, email, password, role) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, nextId, dto.getUsername(), email, encodedPassword, role);
    }
}
