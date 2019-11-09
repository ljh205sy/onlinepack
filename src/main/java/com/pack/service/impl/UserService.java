package com.pack.service.impl;

        import com.pack.entity.Department;
        import com.pack.entity.User;
        import com.pack.repository.BaseRepository;
        import com.pack.repository.DepartmentRepository;
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
public class UserService extends BaseServiceImpl<User, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseRepository<User, String> getRepository() {
        return userRepository;
    }
}
