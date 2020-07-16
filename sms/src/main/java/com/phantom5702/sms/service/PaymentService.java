package com.phantom5702.sms.service;

import com.phantom5702.sms.dao.PaymentMapper;
import com.phantom5702.sms.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentMapper paymentMapper;


    public Payment getById(Integer id) {

        Payment payment = paymentMapper.selectById(id);
        if (payment.getSerial().contains("a")) return payment;
        else return new Payment();

    }

    public int insert(Payment payment){
        return paymentMapper.insert(payment);
    }





}
