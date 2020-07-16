package com.phantom5702.sms;

import com.phantom5702.sms.dao.PaymentMapper;
import com.phantom5702.sms.entity.Payment;
import com.phantom5702.sms.service.PaymentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExample {

    @Mock
    private PaymentMapper paymentMapper;

    @Autowired
    private PaymentService paymentService;


    private Payment payment;

    @Before
    public void setUp() throws Exception {
        //用于初始化@Mock注解修饰的组件
        MockitoAnnotations.initMocks(this);
        payment = new Payment();
        payment.setId(1);
        payment.setSerial("a book");

        when(paymentMapper.selectById(1)).thenReturn(payment);
        try {

            Field declaredField = PaymentService.class.getDeclaredField("paymentMapper");

            declaredField.setAccessible(true);

            ReflectionUtils.setField(declaredField, paymentService, paymentMapper);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }



    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        Payment byId = paymentService.getById(1);
        System.out.println(byId);
        Payment payment = paymentMapper.selectById(6);
        System.out.println(payment);
    }

}
