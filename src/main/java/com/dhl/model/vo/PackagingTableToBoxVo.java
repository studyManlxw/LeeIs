package com.dhl.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackagingTableToBoxVo {
    private String text;
    private List<PackagingTableToBoxVo> children;
}
