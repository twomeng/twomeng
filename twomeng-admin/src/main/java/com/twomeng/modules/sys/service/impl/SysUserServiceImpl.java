package com.twomeng.modules.sys.service.impl;

import com.twomeng.modules.sys.domain.SysUser;
import com.twomeng.modules.sys.mapper.SysUserMapper;
import com.twomeng.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<SysUser> list() {

        List<SysUser> sysUsers = userMapper.selectAll();
        return sysUsers;
    }
}
