package com.twomeng.modules.sys.mapper;

import com.twomeng.modules.sys.domain.SysUser;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface SysUserMapper extends Mapper<SysUser> {

    public List<SysUser> findAll();
}
