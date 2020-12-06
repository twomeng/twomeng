package com.twomeng.modules.sys.domain;

import lombok.Data;
import lombok.NonNull;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "sys_user")
public class SysUser {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int user_id;

    public SysUser() {
    }

    @NonNull
    private String username;

    private String password;

    private String salt;

    private String  email;

    private String mobile;

    private int status;

    private int deptId;

    private Date createTime;
}
