package com.example.shareIngJdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class tOrderItem extends Model<tOrderItem> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_item_id",type = IdType.AUTO)
    private Long orderItemId;

    private Long orderId;

    private Integer userId;

    private String status;


    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

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
        return this.orderItemId;
    }

    @Override
    public String toString() {
        return "tOrderItem{" +
        "orderItemId=" + orderItemId +
        ", orderId=" + orderId +
        ", userId=" + userId +
        ", status=" + status +
        "}";
    }
}
