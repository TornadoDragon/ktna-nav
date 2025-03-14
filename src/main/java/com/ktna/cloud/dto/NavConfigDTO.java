package com.ktna.cloud.dto;

import com.ktna.cloud.domain.SysNavItem;
import lombok.Data;
import org.noear.solon.validation.annotation.NotBlank;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * com.ktna.cloud.vo
 *
 * @author kingdragon 王小龙
 * @since 2025/3/4 10:35
 */
@Data
public class NavConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String title;

    /**
     * 访问计数
     */
    private Integer count;

    private Collection<NavConfigCategoryDTO> navData = Collections.emptyList();

}
