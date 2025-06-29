package com.neucamp.testalliancehubbackend.mapper;

import com.neucamp.testalliancehubbackend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username} and password=#{password}")
    User Login(User user);

    // 获取下一个用户ID
    @Select("SELECT COUNT(*) + 1 FROM user")
    int getNextUserId();

    // 检查企业是否存在
    @Select("SELECT COUNT(*) FROM company WHERE company_id = #{companyId}")
    int checkCompanyExists(int companyId);

    // 注册用户
    @Insert("INSERT INTO user (company_id, username, nickname, phone, email, gender, password,create_time,status,is_super) " +
            "VALUES (#{companyId}, #{username}, #{nickname}, #{phone}, #{email}, 0, #{password}, NOW(),1,0)")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int registerUser(User user);

}
