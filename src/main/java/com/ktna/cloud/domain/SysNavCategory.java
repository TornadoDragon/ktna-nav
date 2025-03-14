package com.ktna.cloud.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_nav_category
 */
@Data
@TableName(value ="sys_nav_category")
public class SysNavCategory implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 分类标题
     */
    private String title;

    /**
     * 排序码
     */
    private Integer sort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}