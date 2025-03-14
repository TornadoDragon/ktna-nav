package com.ktna.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ktna.cloud.constants.SysParamEnum;
import com.ktna.cloud.domain.SysParameter;

/**
* @author kingdragon
* @description 针对表【sys_parameter(系统参数表)】的数据库操作Service
* @createDate 2025-03-03 16:44:56
*/
public interface SysParameterService extends IService<SysParameter> {

    /**
     * 获取参数值
     *
     * @param paramEnum
     * @return
     */
    String getParamValue(SysParamEnum paramEnum);

    void updateParamValue(SysParamEnum paramEnum, String paramValue);

    /**
     * 访问量自增
     *
     * @return
     */
    Integer incrVisitCount();
}
