package com.ktna.cloud.component;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.ddl.DdlHelper;
import com.baomidou.mybatisplus.extension.ddl.IDdl;
import com.ktna.cloud.dto.NavConfigDTO;
import com.ktna.cloud.service.SysNavCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.noear.snack.ONode;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Init;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.util.ResourceUtil;

import java.io.IOException;
import java.util.List;

/**
 * com.ktna.cloud.component
 *
 * @author kingdragon 王小龙
 * @since 2025/3/3 14:33
 */
@Slf4j
@Component
public class DDLInitAppRunner {

    @Inject
    private List<IDdl> ddlList;
    @Inject
    private SysNavCategoryService sysNavCategoryService;

    @Init(index = -1)
    public void init() throws IOException {
        if (CollectionUtils.isNotEmpty(ddlList)) {
            log.debug("  ...  DDL start create  ...  ");
            ddlList.forEach(ddl -> ddl.runScript(dataSource -> DdlHelper.runScript(ddl.getDdlGenerator(),
                    dataSource, ddl.getSqlFiles(), true)));
            log.debug("  ...  DDL end create  ...  ");
        }
        long count = sysNavCategoryService.count();
        if (count == 0) {
            String config = ResourceUtil.findResourceAsString("classpath:config.json");

            NavConfigDTO configDTO = ONode.deserialize(config, NavConfigDTO.class);
            sysNavCategoryService.updateConfig(configDTO);
        }

    }

}
