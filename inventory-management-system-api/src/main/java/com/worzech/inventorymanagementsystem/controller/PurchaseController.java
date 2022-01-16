package com.worzech.inventorymanagementsystem.controller;

import com.worzech.inventorymanagementsystem.model.purchase.PurchaseDto;
import com.worzech.inventorymanagementsystem.service.purchase.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "purchase", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PurchaseDto> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PurchaseDto getOrderById(@PathVariable Long id) {
        return purchaseService.getPurchaseById(id);
    }

    @PostMapping(path = "newPurchase", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PurchaseDto createNewPurchase(@RequestBody PurchaseDto purchaseDto) {
        return purchaseService.createNewPurchase(purchaseDto);
    }

    @PutMapping(path = "/editPurchase/{id}", consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PurchaseDto editDemand(@PathVariable Long id, @RequestBody PurchaseDto purchaseDto) {
        return purchaseService.updatePurchase(id, purchaseDto);
    }

    @DeleteMapping("/delete/{orderId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long purchaseId) {
        purchaseService.deletePurchaseById(purchaseId);
    }

}
