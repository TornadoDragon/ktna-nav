package com.ktna.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ktna.cloud.domain.SysParameter;
import org.apache.ibatis.annotations.Mapper;

/**
* @author kingdragon
* @description 针对表【sys_parameter(系统参数表)】的数据库操作Mapper
* @createDate 2025-03-03 16:44:56
* @Entity generator.domain.SysParameter
*/
@Mapper
public interface SysParameterMapper extends BaseMapper<SysParameter> {

    /**
     * 访问量自增
     */
    void incrVisitCount();

}
