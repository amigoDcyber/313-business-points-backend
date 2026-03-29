package com.businesspoint.backend.payments.mapper;

import com.businesspoint.backend.payments.dto.PaymentRequest;
import com.businesspoint.backend.payments.dto.PaymentResponse;
import com.businesspoint.backend.payments.entity.Payment;
import com.businesspoint.backend.transfers.entity.Transfer;
import com.businesspoint.backend.users.entity.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:35+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentResponse toResponse(Payment entity) {
        if ( entity == null ) {
            return null;
        }

        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setTransferId( entityTransferId( entity ) );
        paymentResponse.setUserId( entityUserId( entity ) );
        paymentResponse.setId( entity.getId() );
        paymentResponse.setPaymentMethod( entity.getPaymentMethod() );
        paymentResponse.setProvider( entity.getProvider() );
        paymentResponse.setProviderRef( entity.getProviderRef() );
        paymentResponse.setAmount( entity.getAmount() );
        paymentResponse.setCurrency( entity.getCurrency() );
        paymentResponse.setStatus( entity.getStatus() );
        paymentResponse.setCapturedAt( entity.getCapturedAt() );
        paymentResponse.setCreatedAt( entity.getCreatedAt() );

        return paymentResponse;
    }

    @Override
    public Payment toEntity(PaymentRequest request) {
        if ( request == null ) {
            return null;
        }

        Payment.PaymentBuilder payment = Payment.builder();

        payment.paymentMethod( request.getPaymentMethod() );
        payment.provider( request.getProvider() );
        payment.providerRef( request.getProviderRef() );
        payment.amount( request.getAmount() );
        payment.currency( request.getCurrency() );

        return payment.build();
    }

    @Override
    public void updateEntity(Payment payment, PaymentRequest request) {
        if ( request == null ) {
            return;
        }

        payment.setPaymentMethod( request.getPaymentMethod() );
        payment.setProvider( request.getProvider() );
        payment.setProviderRef( request.getProviderRef() );
        payment.setAmount( request.getAmount() );
        payment.setCurrency( request.getCurrency() );
    }

    private UUID entityTransferId(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        Transfer transfer = payment.getTransfer();
        if ( transfer == null ) {
            return null;
        }
        UUID id = transfer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityUserId(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        User user = payment.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
