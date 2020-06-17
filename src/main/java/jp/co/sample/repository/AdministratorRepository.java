package jp.co.sample.repository;

import jp.co.sample.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class AdministratorRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMIN_ROW_Mapper = (rs,i) -> {
        Administrator admin = new Administrator();
        admin.setId(rs.getInt("id"));
        admin.setName(rs.getString("name"));
        admin.setMailAddress(rs.getString("mail_address"));
        admin.setPassword(rs.getString("password"));
        return admin;
    };



    public Administrator findAdminByEmailAndPassword(String email, String password){
        String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address = :email AND password = :password";

        List<Administrator> adminList = new ArrayList<>();

        SqlParameterSource param = new MapSqlParameterSource().addValue("email",email).addValue("password",password);

        adminList = template.query(sql, param, ADMIN_ROW_Mapper);

        if(adminList.size() ==0){
            return null;
        }else {
            Administrator admin = adminList.get(0);
            return admin;
        }
    }

}
