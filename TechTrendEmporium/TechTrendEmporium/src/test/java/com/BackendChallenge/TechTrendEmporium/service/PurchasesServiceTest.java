package com.BackendChallenge.TechTrendEmporium.service;
import com.BackendChallenge.TechTrendEmporium.entity.Sale;
import com.BackendChallenge.TechTrendEmporium.entity.SaleStatus;
import com.BackendChallenge.TechTrendEmporium.repository.SaleRepository;
import com.BackendChallenge.TechTrendEmporium.service.PurchasesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PurchasesServiceTest {

    @InjectMocks
    private PurchasesService purchasesService;

    @Mock
    private SaleRepository saleRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void getPurchasesByUserIdTest() {
//        when(saleRepository.findByCartUserId(anyLong())).thenReturn(Arrays.asList(new Sale(), new Sale()));
//        assertEquals(2, purchasesService.getPurchasesByUserId(1L).size());
//    }
//
//    @Test
//    public void getAllPurchasesTest() {
//        when(saleRepository.findAll()).thenReturn(Arrays.asList(new Sale(), new Sale()));
//        assertEquals(2, purchasesService.getAllPurchases().size());
//    }

    @Test
    public void updatePurchaseStatusTest_Sent() {
        Sale sale = new Sale();
        when(saleRepository.findById(anyLong())).thenReturn(Optional.of(sale));
        assertTrue(purchasesService.updatePurchaseStatus(1L, "SENT"));
        assertEquals(SaleStatus.SENT, sale.getStatus());
    }

    @Test
    public void updatePurchaseStatusTest_ToSend() {
        Sale sale = new Sale();
        when(saleRepository.findById(anyLong())).thenReturn(Optional.of(sale));
        assertTrue(purchasesService.updatePurchaseStatus(1L, "TO_SEND"));
        assertEquals(SaleStatus.TO_SEND, sale.getStatus());
    }

    @Test
    public void updatePurchaseStatusTest_Closed() {
        Sale sale = new Sale();
        when(saleRepository.findById(anyLong())).thenReturn(Optional.of(sale));
        assertTrue(purchasesService.updatePurchaseStatus(1L, "CLOSED"));
        assertEquals(SaleStatus.CLOSED, sale.getStatus());
    }

    @Test
    public void updatePurchaseStatusTest_InvalidStatus() {
        Sale sale = new Sale();
        when(saleRepository.findById(anyLong())).thenReturn(Optional.of(sale));
        assertFalse(purchasesService.updatePurchaseStatus(1L, "INVALID"));
    }

    @Test
    public void updatePurchaseStatusTest_SaleDoesNotExist() {
        when(saleRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertFalse(purchasesService.updatePurchaseStatus(1L, "SENT"));
    }
}