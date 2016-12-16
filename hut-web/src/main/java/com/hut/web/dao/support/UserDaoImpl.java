package com.hut.web.dao.support;

import com.hut.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jared on 2016/12/16.
 */
@Transactional(Isolation="")
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //无参数查询
    @Override
    public int getCount() {
        int count = this.jdbcTemplate.queryForObject("select count(*) from t_actor",Integer.class);
        return count;
    }

    //简单参数查询
    public int getCountByName() {

        //查询String
        String lastName = this.jdbcTemplate.queryForObject("select last_name from t_actor where id = ?",new Object[]{1212L}, String.class)

        //查询int
        int count = this.jdbcTemplate.queryForObject("select count(*) from t_actor where first_name = ?",Integer.class,"joe");
        return count;

        SecurityProperties.User user = this.jdbcTemplate.query("select first_name, last_name from t_actor",
                new RowMapper<Actor>() {
                    public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Actor actor = new Actor();
                        actor.setFirstName(rs.getString("first_name"));
                        actor.setLastName(rs.getString("last_name"));
                        return actor;
                    }
                });
    }
}
