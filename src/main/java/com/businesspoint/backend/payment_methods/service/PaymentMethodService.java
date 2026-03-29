package com.businesspoint.backend.payment_methods.service;

import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.payment_methods.dto.PaymentMethodRequest;
import com.businesspoint.backend.payment_methods.dto.PaymentMethodResponse;
import com.businesspoint.backend.payment_methods.entity.PaymentMethod;
import com.businesspoint.backend.payment_methods.mapper.PaymentMethodMapper;
import com.businesspoint.backend.payment_methods.repository.PaymentMethodRepository;
import com.businesspoint.backend.users.entity.User;
import com.businesspoint.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    @Transactional(readOnly = true)
    public List<PaymentMethodResponse> getUserPaymentMethods(UUID userId) {
        return paymentMethodRepository.findAllByUserId(userId).stream()
                .map(paymentMethodMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public PaymentMethodResponse addPaymentMethod(UUID userId, PaymentMethodRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (Boolean.TRUE.equals(request.getIsDefault())) {
            paymentMethodRepository.findByUserIdAndIsDefaultTrue(userId).ifPresent(existing -> {
                existing.setIsDefault(false);
                paymentMethodRepository.save(existing);
            });
        }

        PaymentMethod method = paymentMethodMapper.toEntity(request);
        method.setUser(user);
        
        return paymentMethodMapper.toResponse(paymentMethodRepository.save(method));
    }
    
    @Transactional
    public void deletePaymentMethod(UUID methodId) {
        paymentMethodRepository.deleteById(methodId);
    }
}
