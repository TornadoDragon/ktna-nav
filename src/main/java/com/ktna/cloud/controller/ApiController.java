package com.ktna.cloud.controller;

import cn.hutool.json.JSONUtil;
import com.ktna.cloud.dto.NavConfigDTO;
import com.ktna.cloud.service.SysNavCategoryService;
import lombok.RequiredArgsConstructor;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.MethodType;

/**
 * com.ktna.cloud.controller
 *
 * @author kingdragon 王小龙
 * @since 2025/3/4 11:28
 */
@Controller
@Mapping("/api")
@RequiredArgsConstructor
public class ApiController {

    @Inject
    private SysNavCategoryService sysNavCategoryService;

    @Mapping(value = "/config", method = MethodType.GET)
    public NavConfigDTO getConfig() {
        return sysNavCategoryService.getConfig();
    }

    @Mapping(value = "/config", method = MethodType.POST)
    public void updateConfig(NavConfigDTO config) {
        sysNavCategoryService.updateConfig(config);
    }

}
