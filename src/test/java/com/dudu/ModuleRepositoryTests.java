package com.dudu;

import com.pack.OnlinepackApplication;
import com.pack.entity.Module;
import com.pack.repository.ModuleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = OnlinepackApplication.class)
public class ModuleRepositoryTests {

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    @Transactional
    @Rollback(false)  //关闭自动回滚
    public void whenCreateModule() {
        Module module = new Module();
        module.setModuleId("api-admin");
        module.setModuleName("综合管理服务");
        module.setModuleGroup("1");
        module.setResponsibilityPerson("liujinhui");
        module.setDependencyModules(null);
        moduleRepository.save(module);
    }


}
