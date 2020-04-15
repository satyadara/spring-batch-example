package com.satyadara.springbatch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelItemDTO {
    private String id;
    private String quantity;
    private String name;
    private String description;
}
