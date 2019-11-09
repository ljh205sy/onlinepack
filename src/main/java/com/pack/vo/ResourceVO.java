package com.pack.vo;

import com.pack.entity.Resource;
import com.pack.entity.Role;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 16:56
 */
@Data
public class ResourceVO implements Serializable {

    private String guid;
    private String funcname;
    private String funcnameEn;
    private int typeid;
    private String target;
    private String url;
    private String title;
    private String icon;
    private Boolean enable;
    private Integer sort;

    private ResourceVO presource;

}
