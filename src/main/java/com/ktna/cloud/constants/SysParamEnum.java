package com.ktna.cloud.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * com.ktna.cloud.constants
 *
 * @author kingdragon 王小龙
 * @since 2025/3/3 16:36
 */
@Getter
@AllArgsConstructor
public enum SysParamEnum {

    SYS_TITLE("sys_title", "导航标题参数", "运维管理导航（XXX环境）", "导航标题参数，建议修改"),
    SYS_VISIT_COUNT("sys_visit_count", "访问量统计", "0", "当前导航的访问量"),

    ;
    private final String paramCode;
    private final String paramName;
    private final String paramValue;
    private final String paramDescribe;

}
