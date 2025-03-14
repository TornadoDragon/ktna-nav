package com.ktna.cloud.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 系统参数表
 * @TableName sys_parameter
 */
@Data
@TableName(value ="sys_parameter")
public class SysParameter implements Serializable {
    /**
     * 参数编码
     */
    @TableId
    private String paramCode;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 参数描述
     */
    private String paramDescribe;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}