package com.example.demo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * sys_log 操作日志表
 * </p>
 *
 * @author xie
 * @since 2017-09-21
 */
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Integer id;
    /**
     * 用户ID
     */
	private Integer uid;
    /**
     * 日志内容
     */
	private String content;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
