package com.ssafy.queant.controller;

import com.ssafy.queant.model.dto.product.*;
import com.ssafy.queant.model.entity.product.Product;
import com.ssafy.queant.model.service.product.ProductService;
import com.ssafy.queant.model.service.product.ReportProductService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ReportProductService reportProductService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 정보 가져오기 성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 상품 id입니다"),
    })
    @Operation(summary = "상품 세부 정보", description = "상품 세부 정보 제공")
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductInfo(@PathVariable(value = "productId") int productId) throws Exception {
        ProductDetailDto productDetailDto = productService.findByProductId(productId);
        if (productDetailDto == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(productDetailDto, HttpStatus.OK);
    }

//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Enabled False 상품들 조회에 성공했습니다."),
//    })
//    @Operation(summary = "Enabled= false인 상품 조회", description = "상품 데이터 관리용, 상품 세부 정보 제공")
//    @GetMapping("/admin")
//    public ResponseEntity<?> getIsEnabledFalseProduct() {
//        List<ProductDto> productDtoList = productService.findByIsEnabledFalse();
//        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
//    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "제보 목록들 조회에 성공했습니다."),
    })
    @Operation(summary = "제보 목록", description = "제보 데이터 관리, admin이 제보받은 목록들 보고 데이터 추가용")
    @GetMapping("/report")
    public ResponseEntity<?> getReportProduct() {
        List<ReportProductDto> reportProductDtos = reportProductService.findAll();
        return new ResponseEntity<>(reportProductDtos, HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "제보 등록에 성공했습니다."),
            @ApiResponse(code = 404, message = "유저 정보가 옳지 않습니다.(유저가 존재하지 않습니다.)"),
    })
    @Operation(summary = "제보 등록", description = "")
    @PostMapping("/report")
    public ResponseEntity<?> registReportProduct(@RequestBody ReportProductDto reportProductDto) {
        try {
            reportProductService.registReport(reportProductDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "제보의 상세 정보 조회에 성공했습니다."),
            @ApiResponse(code = 404, message = "조회하려는 제보가 없습니다.(제보 id 오류)"),
    })
    @Operation(summary = "제보 내용 상세정보", description = "조회 번호에 해당하는 제보 정보를 가져옴")
    @GetMapping("/report/{reportId}")
    public ResponseEntity<?> getReportProductDetail(@PathVariable(value = "reportId") int reportId) {
        try {
            ReportProductDto reportProductDto = reportProductService.findByReportId(reportId);
            return new ResponseEntity<>(reportProductDto,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @ApiResponses({
//            @ApiResponse(code = 200, message = "제보의 상품화가 성공했습니다."),
//            @ApiResponse(code = 404, message = "등록하려는 제보가 없습니다.(제보 id 오류)"),
//    })
//    @Operation(summary = "제보받은 상품 등록", description = "admin이 제보받은 상품 등록 처리")
//    @PostMapping("/report/{reportId}")
//    public ResponseEntity<?> updateReportProduct(@PathVariable(value = "reportId") int reportId, @RequestBody ProductDetailDto productDetail) {
//        try {
//            productService.updateReportToProduct(reportId, productDetail);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "제보의 상품화가 성공했습니다."),
            @ApiResponse(code = 404, message = "등록하려는 제보가 없습니다.(제보 id 오류)"),
    })
    @Operation(summary = "제보받은 상품 등록", description = "admin이 제보받은 상품 등록 처리")
    @PostMapping("/report/regist")
    public ResponseEntity<?> test(@RequestBody UpdateDetailDto updateDetailDto) {
        try {
            productService.updateReportToProduct(updateDetailDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "제보받은 상품을 삭제했습니다."),
            @ApiResponse(code = 404, message = "삭제하려는 제보가 없습니다.(제보 id 오류)"),
    })
    @Operation(summary = "제보받은 상품 삭제", description = "admin이 제보받은 상품 삭제 처리")
    @PutMapping("/report/delete")
    public ResponseEntity<?> deleteReportProduct(@RequestBody int reportId) {
        try {
            String tmp = productService.reportStatusToUpdated(reportId);
            log.info("result is : "+tmp);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "사용자별 제보 목록들 조회에 성공했습니다."),
    })
    @Operation(summary = "사용자별 제보 목록", description = "자신이 작성한 제보 리스트를 받아옴")
    @GetMapping("/report/user")
    public ResponseEntity<?> getReportProductById(@RequestBody UUID uuid) {
        List<ReportProductDto> reportProductDtos = reportProductService.findById(uuid);
        return new ResponseEntity<>(reportProductDtos, HttpStatus.OK);
    }

}
