package com.pack.service.impl;

import com.pack.entity.UploadModule;
import com.pack.repository.BaseRepository;
import com.pack.repository.UploadModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 10:03
 */
@Service
@Transactional
public class UploadModuleService extends BaseServiceImpl<UploadModule, String> {
    @Autowired
    private UploadModuleRepository uploadModuleRepository;

    @Override
    public BaseRepository<UploadModule, String> getRepository() {
        return uploadModuleRepository;
    }
}
