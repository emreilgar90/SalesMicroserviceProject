package com.emre.controller;

import com.emre.dto.request.BaseRequestDto;
import com.emre.repository.entity.Sale;
import com.emre.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.emre.contants.RestApis.*;



@RestController
@RequestMapping(SALES)
@RequiredArgsConstructor /**construcktor injection*/
public class SaleController {
    private final SaleService saleService;



    @PostMapping(GETALL)
    public ResponseEntity<List<Sale>> getAll(@RequestBody @Valid BaseRequestDto dto){
        return ResponseEntity.ok(saleService.findAll());

    }


}
