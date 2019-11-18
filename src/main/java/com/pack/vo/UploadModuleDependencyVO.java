package com.pack.vo;

import com.pack.entity.UploadModule;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @Author: liujinhui
 * @Date: 2019/11/16 21:51
 */

@Data
public class UploadModuleDependencyVO implements Serializable {

    private String uuid;
    private String relativeModuleId;
    private String relativeModuleVersion;
    private UploadModuleVO uploadModuleVO;
}
