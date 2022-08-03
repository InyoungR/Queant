package com.ssafy.queant.controller;

import com.ssafy.queant.model.dto.product.ProductDetailDto;
import com.ssafy.queant.model.service.product.ProductService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 정보 가져오기 성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 상품 id입니다"),
    })
    @Operation(summary = "상품 세부 정보", description = "상품 세부 정보 제공")
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductInfo(@PathVariable(value = "productId") String productId) throws Exception {
        ProductDetailDto productDetailDto = productService.findByProductId(productId);
        if (productDetailDto == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(productDetailDto, HttpStatus.OK);
    }
}