package com.ktna.cloud.service;

import com.ktna.cloud.domain.SysNavCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ktna.cloud.dto.NavConfigDTO;

/**
* @author kingdragon
* @description 针对表【sys_nav_category】的数据库操作Service
* @createDate 2025-03-04 09:48:20
*/
public interface SysNavCategoryService extends IService<SysNavCategory> {

    /**
     * 获取导航配置
     *
     * @return
     */
    NavConfigDTO getConfig();

    /**
     * 更新导航配置
     *
     * @param config
     * @return
     */
    void updateConfig(NavConfigDTO config);

}
