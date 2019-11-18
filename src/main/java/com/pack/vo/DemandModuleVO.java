package com.pack.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liujinhui
 * @Date: 2019/11/16 18:59
 */
@Data
public class DemandModuleVO implements Serializable {
    private String guid;
    private String relativeModule;
    private String relativeModuleVersion;

    @Override
    public String toString() {
        return "DemandModuleVO{" +
                "guid='" + guid + '\'' +
                ", relativeModule='" + relativeModule + '\'' +
                ", relativeModleVersion='" + relativeModuleVersion + '\'' +
                '}';
    }
}
