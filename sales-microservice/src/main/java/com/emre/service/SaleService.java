package com.emre.service;

import com.emre.controller.SaleController;
import com.emre.repository.entity.Sale;
import com.emre.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleService extends ServiceManager<Sale,Long> {
    private final SaleController saleController;

    public SaleService(JpaRepository<Sale, Long> repository, SaleController saleController) {
        super(repository);
        this.saleController = saleController;
    }
}
