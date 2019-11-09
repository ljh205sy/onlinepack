package com.dudu;

import com.pack.entity.Department;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class OnlinepackApplicationTests {

    /**
     * department的值为空，取else的部分
     */
    @Test
    public void testDepartmentNull() {
        Department department = null;

        Department other = new Department();
        other.setName("other");
        other.setAliasname("别名");

        // 如果department为空，去other的值
        // 如果不为空取department的值
        Department department2 = Optional.ofNullable(department).orElse(other);

        System.out.println(department2);
    }

    /**
     * department的值不为空，直接去现有的值
     */
    @Test
    public void testDepartmentNoTnull() {
        Department department = new Department();
        department.setName("1111");
        department.setAliasname("别名");

        Department other = new Department();
        other.setName("other");
        // 如果department为空，去other的值
        // 如果不为空取department的值
        Department department2 = Optional.ofNullable(department).orElse(other);
        System.out.println(department2);
    }

}
