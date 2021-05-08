package com.example.shareIngJdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xie
 * @since 2018-09-01
 */
public class tOrder extends Model<tOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id",type = IdType.AUTO)
    private Long orderId;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value="status")
    private String status;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

    @Override
    public String toString() {
        return "tOrder{" +
        "orderId=" + orderId +
        ", userId=" + userId +
        ", status=" + status +
        "}";
    }
}
