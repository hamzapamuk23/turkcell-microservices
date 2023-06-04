package com.kodlamaio.rentalservice.api.clients;

import com.kodlamaio.commonpackage.utils.dto.responses.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.requests.CreateRentalPaymentRequest;
import com.kodlamaio.commonpackage.utils.dto.responses.GetPaymentCardHolderResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "payment-service", fallback = PaymentClientFallback.class)
public interface PaymentClient {
    @Retry(name = "payment-retry")
    @PostMapping(value = "/api/payments/check")
    ClientResponse processPayment(@RequestBody CreateRentalPaymentRequest request);

    @GetMapping("/api/payments/card-holder/{id}")
    @Retry(name = "notAvailableService")
    public GetPaymentCardHolderResponse getCardHolder(@PathVariable UUID id);
}
