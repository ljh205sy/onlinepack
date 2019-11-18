package com.pack.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author: liujinhui
 * @Date: 2019/11/15 20:36
 */
// 一个需求存在多个模块的依赖
@Entity
@Table(name = "t_demand_module")
public class DemandModule {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "guid")
    private String guid;
    @Column(name = "relative_module", length = 255)
    private String relativeModule;
    @Column(name = "relative_modle_version", length = 255)
    private String relativeModuleVersion;
    @ManyToOne
    @JoinColumn(name="demand_id")
    private Demand demand;

    public DemandModule(){

    }
    public DemandModule(Demand demand){
        this.demand = demand;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getRelativeModule() {
        return relativeModule;
    }

    public void setRelativeModule(String relativeModule) {
        this.relativeModule = relativeModule;
    }

    public String getRelativeModuleVersion() {
        return relativeModuleVersion;
    }

    public void setRelativeModuleVersion(String relativeModuleVersion) {
        this.relativeModuleVersion = relativeModuleVersion;
    }
}
