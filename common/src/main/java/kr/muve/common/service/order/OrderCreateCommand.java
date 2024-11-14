package kr.muve.common.service.order;

public record OrderCreateCommand(
        Long productId,
        Integer count,
        String ordererName,
        String ordererPhoneNumber,
        String ordererEmail,
        String receiverName,
        String receiverPhoneNumber,
        String city,
        String street,
        String zipcode,
        String paymentMethod
) {}
