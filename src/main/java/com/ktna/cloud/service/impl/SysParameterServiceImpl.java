package com.ktna.cloud.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktna.cloud.constants.SysParamEnum;
import com.ktna.cloud.domain.SysParameter;
import com.ktna.cloud.mapper.SysParameterMapper;
import com.ktna.cloud.service.SysParameterService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.Optional;

/**
 * @author kingdragon
 * @description 针对表【sys_parameter(系统参数表)】的数据库操作Service实现
 * @createDate 2025-03-03 16:44:56
 */
@Component
public class SysParameterServiceImpl extends ServiceImpl<SysParameterMapper, SysParameter> implements SysParameterService {

    @Override
    public String getParamValue(SysParamEnum paramEnum) {
        return Optional.ofNullable(this.getById(paramEnum.getParamCode()))
                .map(SysParameter::getParamValue)
                .orElse(null);
    }

    @Override
    public void updateParamValue(SysParamEnum paramEnum, String paramValue) {
        this.update(Wrappers.<SysParameter>lambdaUpdate()
                .eq(SysParameter::getParamCode, paramEnum.getParamCode())
                .set(SysParameter::getParamValue, paramValue)
        );
    }

    @Override
    public Integer incrVisitCount() {
        getBaseMapper().incrVisitCount();
        String paramValue = this.getParamValue(SysParamEnum.SYS_VISIT_COUNT);
        return Integer.parseInt(paramValue);
    }
}
