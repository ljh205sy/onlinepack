package com.pack.service.impl;

import com.pack.entity.Module;
import com.pack.entity.User;
import com.pack.repository.BaseRepository;
import com.pack.repository.ModuleRepository;
import com.pack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 10:03
 */
@Service
@Transactional
public class ModuleService extends BaseServiceImpl<Module, String> {
    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public BaseRepository<Module, String> getRepository() {
        return moduleRepository;
    }
}
