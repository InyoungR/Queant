package com.ssafy.queant.model.dto.product;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductInfoDto {
    private ProductDto product;
    private List<OptionsDto> options;
    private List<ConditionsDto> conditions;
    private List<JoinwayDto> joinway;
}
