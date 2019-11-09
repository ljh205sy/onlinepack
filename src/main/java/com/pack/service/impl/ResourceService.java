package com.pack.service.impl;

import com.pack.entity.Resource;
import com.pack.repository.BaseRepository;
import com.pack.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: liujinhui
 * @Date: 2019年11月9日19:33:21
 */
@Service
@Transactional
public class ResourceService extends BaseServiceImpl<Resource, String> {
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public BaseRepository<Resource, String> getRepository() {
        return resourceRepository;
    }
}
