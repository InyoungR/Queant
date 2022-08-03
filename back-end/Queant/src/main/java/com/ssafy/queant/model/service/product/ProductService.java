package com.ssafy.queant.model.service.product;

import com.ssafy.queant.model.dto.product.ProductDetailDto;
import com.ssafy.queant.model.dto.product.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findByBankId(int bankId);

    List<ProductDto> findByNameContaining(String name);

    ProductDetailDto findByProductId(String productId);
}