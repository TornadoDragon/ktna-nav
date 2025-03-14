package com.ktna.cloud.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Arrays;
import lombok.Data;

/**
 * 
 * @TableName sys_nav_item
 */
@Data
@TableName(value ="sys_nav_item")
public class SysNavItem implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 
     */
    private Long categoryId;

    /**
     * 导航卡片名
     */
    private String name;

    /**
     * 链接
     */
    private String href;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序码
     */
    private Integer sort;

    /**
     * 图标
     */
    private String logo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}