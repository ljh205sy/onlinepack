package com.dudu;

import com.pack.entity.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

public class ResourceControllerTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void whenOrElseGet() {
        Resource resource = new Resource();
        resource.setGuid("xxx");
        Resource resource1 = Optional.ofNullable(resource.getPresource()).orElse(createNewResource());
        System.out.println("----------------" + resource1.getGuid());
        logger.debug("Using orElseGet");
        Resource result2 = Optional.ofNullable(resource.getPresource()).orElseGet(() -> createNewResource());
        System.out.println(resource1.getGuid());
    }

    /**
     * map() 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional 中
     */
    @Test
    public void whenMap(){
        Resource resource = new Resource();
        resource.setGuid("xxx");
        String guid = Optional.ofNullable(resource)
                .map(u -> u.getGuid()).orElse("default@gmail.com");
        logger.debug("guid:---------->" + guid);
        Assert.assertEquals(guid, resource.getGuid());
    }

    @Test
    public void whenFilter_theOK(){
        Resource resource = new Resource();
        resource.setGuid("xxx");
        Optional<Resource> result = Optional.ofNullable(resource)
                .filter(u -> u.getPresource() == null);
        Assert.assertTrue(result.isPresent());
        logger.debug("---------->" + (result.get().getPresource() == null));
    }

    private Resource createNewResource() {
        logger.debug("Creating New User");
        Resource resource = new Resource();
        resource.setGuid("11");
        return resource;
    }
}
