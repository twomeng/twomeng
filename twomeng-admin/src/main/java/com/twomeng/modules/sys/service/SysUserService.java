package com.twomeng.modules.sys.service;

import com.twomeng.modules.sys.domain.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SysUserService {

    List<SysUser> list();
}
