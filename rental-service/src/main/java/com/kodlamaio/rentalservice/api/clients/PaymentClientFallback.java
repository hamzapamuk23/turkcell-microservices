package com.kodlamaio.rentalservice.api.clients;

import com.kodlamaio.commonpackage.utils.dto.responses.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.requests.CreateRentalPaymentRequest;
import com.kodlamaio.commonpackage.utils.dto.responses.GetPaymentCardHolderResponse;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentClientFallback implements PaymentClient {
    @Override
    public ClientResponse processPayment(CreateRentalPaymentRequest request) {
        return null;
    }

    @Override
    public GetPaymentCardHolderResponse getCardHolder(UUID id) {
        return null;
    }
}
