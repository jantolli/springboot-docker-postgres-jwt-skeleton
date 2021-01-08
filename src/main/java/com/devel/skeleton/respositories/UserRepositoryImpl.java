package com.devel.skeleton.respositories;

import com.devel.skeleton.datasource.CustomGeneratedKeyHolder;
import com.devel.skeleton.domain.User;
import com.devel.skeleton.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    Logger logger = Logger.getLogger(UserRepositoryImpl.class.getName());

    private String LOGIN_USER = "select * from Users where email = ? AND password = ?";

    private String ADD_USER = "insert into Users(id, firstname, lastname, email, password) values (uuid_generate_v1(), ?, ?, ?, ?)";

    private String GET_USER = "select * from Users where email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User login(String email, String password) {
        try {
            User user = jdbcTemplate.queryForObject(LOGIN_USER, userRowMapper, new Object[]{email, password});
            return user;
        } catch (EmptyResultDataAccessException ex) {
            logger.log(Level.WARNING, "Invalid credentials");
            return null;
        }
    }

    @Override
    public String create(String firstName, String lastName, String email, String password) throws UnauthorizedException {
        CustomGeneratedKeyHolder keyHolder = new CustomGeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        ADD_USER, new String[] { "id" });
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, password);
                return ps;
            }
        }, keyHolder);
        return keyHolder.getUUIDKey().toString();
    }

//    @Override
//    public String create(String firstName, String lastName, String email, String password) throws UnauthorizedException {
//        CustomGeneratedKeyHolder keyHolder = new CustomGeneratedKeyHolder();
//        jdbcTemplate.update(ADD_USER, new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                preparedStatement.setString(1, firstName);
//                preparedStatement.setString(2, lastName);
//                preparedStatement.setString(3, email);
//                preparedStatement.setString(4, password);
//            }
//        }, keyHolder);
//        return keyHolder.getUUIDKey().toString();
//    }

    public User get(String email) {
        try {
            return (User) jdbcTemplate.queryForObject(GET_USER, userRowMapper, new Object[]{email});
        } catch (EmptyResultDataAccessException ex) {
            logger.log(Level.WARNING, "User with email {0} not found", new Object[] {email});
            return null;
        }
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(
                rs.getString("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("password"));
    });
}
