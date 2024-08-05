package dev.derrick.enset_spring_boot.repository;

import dev.derrick.enset_spring_boot.entities.Payment;
import dev.derrick.enset_spring_boot.entities.PaymentStatus;
import dev.derrick.enset_spring_boot.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
