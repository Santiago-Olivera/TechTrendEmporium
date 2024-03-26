package com.BackendChallenge.TechTrendEmporium.service;

import com.BackendChallenge.TechTrendEmporium.entity.Sale;
import com.BackendChallenge.TechTrendEmporium.repository.SaleRepository;
import com.BackendChallenge.TechTrendEmporium.service.Response.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.oauth2.login.OAuth2LoginSecurityMarker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchasesService {

    @Autowired
    private SaleRepository saleRepository;

    public List<PurchaseResponse> getPurchasesByUserId(Long userId) {
        List<Sale> sales = saleRepository.findByCartUserId(userId);
        return sales.stream().map(this::convertToPurchaseResponse).collect(Collectors.toList());
    }

    public List<PurchaseResponse> getAllPurchases() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream().map(this::convertToPurchaseResponse).collect(Collectors.toList());
    }

    public Boolean updatePurchaseStatus(Long saleId, String status) {
        Sale sale = saleRepository.findById(saleId).orElse(null);
        if (sale != null) {
            sale.setStatus(status);
            saleRepository.save(sale);
            return true;
        }
        return false;
    }

    private PurchaseResponse convertToPurchaseResponse(Sale sale) {
        PurchaseResponse response = new PurchaseResponse();
        response.setSaleId(sale.getId());
        response.setUserId(sale.getCart().getUser().getId());
        response.setTotal(sale.getTotal());
        response.setDate(sale.getDate());
        response.setStatus(sale.getStatus());
        return response;
    }
}
