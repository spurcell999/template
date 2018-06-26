package com.project.template.configs;

import com.project.template.repositories.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HealthCheckConfig extends AbstractHealthIndicator{

    private PaymentRepository paymentRepository;

    @Autowired
    public HealthCheckConfig(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        try {
            String payment = paymentRepository.dbConnectionHealthQuery();
            builder.up();
        } catch (Exception e) {
            log.error("Unable to connect Database. exception {}", e.getStackTrace());
            builder.down();
        }
    }
}
