package com.businesspoint.backend.payouts.mapper;

import com.businesspoint.backend.payouts.dto.PayoutRequest;
import com.businesspoint.backend.payouts.dto.PayoutResponse;
import com.businesspoint.backend.payouts.entity.Payout;
import com.businesspoint.backend.transfers.entity.Transfer;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:35+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class PayoutMapperImpl implements PayoutMapper {

    @Override
    public PayoutResponse toResponse(Payout entity) {
        if ( entity == null ) {
            return null;
        }

        PayoutResponse payoutResponse = new PayoutResponse();

        payoutResponse.setTransferId( entityTransferId( entity ) );
        payoutResponse.setId( entity.getId() );
        payoutResponse.setProvider( entity.getProvider() );
        payoutResponse.setProviderRef( entity.getProviderRef() );
        payoutResponse.setAmount( entity.getAmount() );
        payoutResponse.setCurrency( entity.getCurrency() );
        payoutResponse.setStatus( entity.getStatus() );
        payoutResponse.setFailureReason( entity.getFailureReason() );
        payoutResponse.setProcessedAt( entity.getProcessedAt() );
        payoutResponse.setCreatedAt( entity.getCreatedAt() );

        return payoutResponse;
    }

    @Override
    public Payout toEntity(PayoutRequest request) {
        if ( request == null ) {
            return null;
        }

        Payout.PayoutBuilder payout = Payout.builder();

        payout.provider( request.getProvider() );
        payout.providerRef( request.getProviderRef() );
        payout.amount( request.getAmount() );
        payout.currency( request.getCurrency() );

        return payout.build();
    }

    @Override
    public void updateEntity(Payout payout, PayoutRequest request) {
        if ( request == null ) {
            return;
        }

        payout.setProvider( request.getProvider() );
        payout.setProviderRef( request.getProviderRef() );
        payout.setAmount( request.getAmount() );
        payout.setCurrency( request.getCurrency() );
    }

    private UUID entityTransferId(Payout payout) {
        if ( payout == null ) {
            return null;
        }
        Transfer transfer = payout.getTransfer();
        if ( transfer == null ) {
            return null;
        }
        UUID id = transfer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
