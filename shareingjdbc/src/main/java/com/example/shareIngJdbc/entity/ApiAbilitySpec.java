package com.example.shareIngJdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 接口规格配置表
 * </p>
 *
 * @author xie
 * @since 2019-03-21
 */
@TableName("API_ABILITY_SPEC")
public class ApiAbilitySpec extends Model<ApiAbilitySpec> {

    private static final long serialVersionUID = 1L;

    /**
     * 接口编码
     */
    @TableId("ABILITY_CODE")
    private String abilityCode;

    /**
     * 接口处理类，配置bean
     */
    @TableField("JAVA_CLASS")
    private String javaClass;

    /**
     * 接口名
     */
    @TableField("ABILITY_NAME")
    private String abilityName;

    /**
     * 接口描述
     */
    @TableField("ABILITY_DESC")
    private String abilityDesc;

    /**
     * 状态 00A：有效 00X：失效
     */
    @TableField("STATE")
    private String state;

    /**
     * 创建时间
     */
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    /**
     * 状态时间
     */
    @TableField("STATE_DATE")
    private LocalDateTime stateDate;

    /**
     * 超时控制开关 T：开 F：关，一般查询类的才打开
     */
    @TableField("TIMEOUT_FLAG")
    private String timeoutFlag;

    /**
     * 超时时间（秒）
     */
    @TableField("TIMEOUT")
    private Integer timeout;

    @TableField("CURRENT_LIMITING")
    private Integer currentLimiting;

    public String getAbilityCode() {
        return abilityCode;
    }

    public void setAbilityCode(String abilityCode) {
        this.abilityCode = abilityCode;
    }
    public String getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass;
    }
    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
    public String getAbilityDesc() {
        return abilityDesc;
    }

    public void setAbilityDesc(String abilityDesc) {
        this.abilityDesc = abilityDesc;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public LocalDateTime getStateDate() {
        return stateDate;
    }

    public void setStateDate(LocalDateTime stateDate) {
        this.stateDate = stateDate;
    }
    public String getTimeoutFlag() {
        return timeoutFlag;
    }

    public void setTimeoutFlag(String timeoutFlag) {
        this.timeoutFlag = timeoutFlag;
    }
    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
    public Integer getCurrentLimiting() {
        return currentLimiting;
    }

    public void setCurrentLimiting(Integer currentLimiting) {
        this.currentLimiting = currentLimiting;
    }

    @Override
    protected Serializable pkVal() {
        return this.abilityCode;
    }

    @Override
    public String toString() {
        return "ApiAbilitySpec{" +
        "abilityCode=" + abilityCode +
        ", javaClass=" + javaClass +
        ", abilityName=" + abilityName +
        ", abilityDesc=" + abilityDesc +
        ", state=" + state +
        ", createDate=" + createDate +
        ", stateDate=" + stateDate +
        ", timeoutFlag=" + timeoutFlag +
        ", timeout=" + timeout +
        ", currentLimiting=" + currentLimiting +
        "}";
    }
}
