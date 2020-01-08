package com.dudu;

import com.pack.PackApplication;
import com.pack.entity.User;
import com.pack.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PackApplication.class)
@Transactional
@Rollback(false)
public class UserControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @Transactional
    @Rollback(false)
    public void whenCreateUser() {
        User user = new User();
        user.setAccount("account1");
        user.setName("liujinhui");
        user.setPassword("123456");
        userRepository.save(user);
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"aliasname\":\"123\",\"name\":\"那么部门1\",\"note\":\"note111\"}";
        String reuslt = mockMvc.perform(post("/v1/depetment").contentType("application/json;charset=UTF-8")
                .content(content))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(reuslt);
    }
}
