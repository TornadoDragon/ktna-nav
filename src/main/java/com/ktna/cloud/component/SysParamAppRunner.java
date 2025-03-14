package com.ktna.cloud.component;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ktna.cloud.constants.SysParamEnum;
import com.ktna.cloud.domain.SysParameter;
import com.ktna.cloud.mapper.SysParameterMapper;
import com.ktna.cloud.service.SysNavCategoryService;
import com.ktna.cloud.service.SysParameterService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Init;
import org.noear.solon.annotation.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * com.ktna.cloud.component
 *
 * @author kingdragon 王小龙
 * @since 2025/3/3 16:39
 */
@Component
public class SysParamAppRunner {

    @Inject
    private SysParameterMapper sysParameterMapper;

    @Inject
    private SysNavCategoryService categoryService;

    @Init
    public void init() {
        SysParamEnum[] paramEnums = SysParamEnum.values();

        List<String> paramCodeList = Arrays.stream(paramEnums).map(SysParamEnum::getParamCode)
                .collect(Collectors.toList());

        Set<String> savedParamCode = sysParameterMapper.selectList(Wrappers.<SysParameter>lambdaQuery()
                        .in(SysParameter::getParamCode, paramCodeList)
                ).stream()
                .map(SysParameter::getParamCode)
                .collect(Collectors.toSet());

        paramCodeList = paramCodeList.stream()
                .filter(item -> !savedParamCode.contains(item))
                .collect(Collectors.toList());

        if (CollUtil.isEmpty(paramCodeList)) {
            return;
        }
        Map<String, SysParamEnum> sysParamEnumMap = Arrays.stream(paramEnums)
                .collect(Collectors.toMap(SysParamEnum::getParamCode, Function.identity()));

        sysParameterMapper.insert(paramCodeList.stream().map(item -> {
            SysParamEnum sysParamEnum = sysParamEnumMap.get(item);
            SysParameter sysParameter = new SysParameter();
            sysParameter.setParamCode(sysParamEnum.getParamCode());
            sysParameter.setParamName(sysParamEnum.getParamName());
            sysParameter.setParamValue(sysParamEnum.getParamValue());
            sysParameter.setParamDescribe(sysParamEnum.getParamDescribe());
            return sysParameter;
        }).collect(Collectors.toList()));

    }

}
