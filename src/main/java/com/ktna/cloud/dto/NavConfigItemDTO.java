package com.ktna.cloud.dto;

import com.ktna.cloud.domain.SysNavItem;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * com.ktna.cloud.dto
 *
 * @author kingdragon 王小龙
 * @since 2025/3/5 14:53
 */
@Data
public class NavConfigItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String href;

    private String desc;

    private String logo;

    public static NavConfigItemDTO convert(SysNavItem navItem) {
        NavConfigItemDTO item = new NavConfigItemDTO();
        item.setName(navItem.getName());
        item.setHref(navItem.getHref());
        item.setDesc(navItem.getDescription());
        item.setLogo(navItem.getLogo());
        return item;
    }

    public static Collection<NavConfigItemDTO> convert(List<SysNavItem> navItems) {
        return navItems.stream()
                .map(NavConfigItemDTO::convert)
                .collect(Collectors.toList());
    }
}
