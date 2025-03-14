package com.ktna.cloud.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * com.ktna.cloud.dto
 *
 * @author kingdragon 王小龙
 * @since 2025/3/5 14:53
 */
@Data
public class NavConfigCategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;

    private Collection<NavConfigItemDTO> data = Collections.emptyList();

}
