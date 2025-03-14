package com.ktna.cloud.component;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.aot.RuntimeNativeMetadata;
import org.noear.solon.aot.RuntimeNativeRegistrar;
import org.noear.solon.aot.hint.MemberCategory;
import org.noear.solon.core.AppContext;

import java.util.Collection;

/**
 * com.ktna.cloud.component
 *
 * @author kingdragon 王小龙
 * @since 2025/3/3 15:53
 */
@Component
public class RuntimeNativeRegistrarImpl implements RuntimeNativeRegistrar {

    @Override
    public void register(AppContext context, RuntimeNativeMetadata metadata) {

        // 注册 Resource 资源
        metadata.registerResourceInclude("config.json");
        metadata.registerResourceInclude("db/.*");
        metadata.registerResourceInclude("com/ktna/cloud/mapper/.*");

        Collection<Class<?>> clazzList = CollUtil.union(
                ClassUtil.scanPackage("com.ktna.cloud.domain"),
                ClassUtil.scanPackage("com.ktna.cloud.dto")
        );

        // 各实体类注册反射
        for (Class<?> clazz : clazzList) {
            metadata.registerReflection(clazz,
                    MemberCategory.PUBLIC_FIELDS,
                    MemberCategory.DECLARED_FIELDS,
                    MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
                    MemberCategory.INVOKE_PUBLIC_METHODS,
                    MemberCategory.INVOKE_DECLARED_METHODS,
                    MemberCategory.INTROSPECT_DECLARED_METHODS
            );
        }

        // 注册各业务实现类，注册 Lambda
        for (Class<?> clazz : ClassUtil.scanPackage("com.ktna.cloud.service.impl")) {
            metadata.registerLambdaSerialization(clazz);
        }
        metadata.registerLambdaSerialization(SysParamAppRunner.class);

    }

}
