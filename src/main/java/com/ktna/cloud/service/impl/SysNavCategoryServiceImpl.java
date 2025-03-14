package com.ktna.cloud.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktna.cloud.constants.SysParamEnum;
import com.ktna.cloud.domain.SysNavCategory;
import com.ktna.cloud.domain.SysNavItem;
import com.ktna.cloud.dto.NavConfigCategoryDTO;
import com.ktna.cloud.dto.NavConfigDTO;
import com.ktna.cloud.dto.NavConfigItemDTO;
import com.ktna.cloud.mapper.SysNavCategoryMapper;
import com.ktna.cloud.mapper.SysParameterMapper;
import com.ktna.cloud.service.SysNavCategoryService;
import com.ktna.cloud.service.SysNavItemService;
import com.ktna.cloud.service.SysParameterService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.data.annotation.Tran;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kingdragon
 * @description 针对表【sys_nav_category】的数据库操作Service实现
 * @createDate 2025-03-04 09:48:20
 */
@Component
public class SysNavCategoryServiceImpl extends ServiceImpl<SysNavCategoryMapper, SysNavCategory> implements SysNavCategoryService {

    @Inject
    private static SysParameterService sysParameterService;

    @Inject
    private static SysNavItemService sysNavItemService;

    @Override
    public NavConfigDTO getConfig() {
        NavConfigDTO res = new NavConfigDTO();
        String title = sysParameterService.getParamValue(SysParamEnum.SYS_TITLE);

        List<SysNavCategory> categoryList = this.list();

        Map<Long, List<SysNavItem>> natItemMap = sysNavItemService.list().stream()
                .collect(Collectors.groupingBy(SysNavItem::getCategoryId));

        List<NavConfigCategoryDTO> navConfigCategories = categoryList.stream()
                .sorted(Comparator.comparing(SysNavCategory::getSort))
                .map(item -> {
                    List<SysNavItem> navItemList = natItemMap.getOrDefault(item.getId(), Collections.emptyList());

                    NavConfigCategoryDTO category = new NavConfigCategoryDTO();
                    category.setTitle(item.getTitle());
                    category.setData(NavConfigItemDTO.convert(navItemList));
                    return category;
                }).collect(Collectors.toList());

        res.setTitle(title);
        res.setNavData(navConfigCategories);
        res.setCount(sysParameterService.incrVisitCount());

        return res;
    }

    @Tran
    @Override
    public void updateConfig(NavConfigDTO config) {
        String title = config.getTitle();

        Collection<NavConfigCategoryDTO> configNavData = config.getNavData();

        // 更新标题参数
        sysParameterService.updateParamValue(SysParamEnum.SYS_TITLE, title);

        // 移除全部
        this.remove(Wrappers.emptyWrapper());
        // 移除全部项目
        sysNavItemService.remove(Wrappers.lambdaQuery());

        // 批量保存
        int sort = 10;
        for (NavConfigCategoryDTO configNavDatum : configNavData) {
            SysNavCategory navCategory = new SysNavCategory();
            navCategory.setTitle(configNavDatum.getTitle());
            navCategory.setSort(sort++);
            // 保存分类
            this.save(navCategory);

            Collection<NavConfigItemDTO> navItemList = configNavDatum.getData();
            for (NavConfigItemDTO navConfigItem : navItemList) {
                SysNavItem navItem = new SysNavItem();
                navItem.setCategoryId(navCategory.getId());
                navItem.setName(navConfigItem.getName());
                navItem.setHref(navConfigItem.getHref());
                navItem.setDescription(navConfigItem.getDesc());
                navItem.setLogo(navConfigItem.getLogo());
                sysNavItemService.save(navItem);
            }
        }
    }
}




