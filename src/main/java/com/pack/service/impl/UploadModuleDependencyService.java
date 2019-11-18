package com.pack.service.impl;

import com.pack.entity.UploadModuleDependency;
import com.pack.repository.BaseRepository;
import com.pack.repository.UploadModuleDependencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 10:03
 */
@Service
@Transactional
public class UploadModuleDependencyService extends BaseServiceImpl<UploadModuleDependency, String> {
    @Autowired
    private UploadModuleDependencyRepository uploadDependencyRepository;

    @Override
    public BaseRepository<UploadModuleDependency, String> getRepository() {
        return uploadDependencyRepository;
    }
}
