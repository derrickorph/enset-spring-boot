package dev.derrick.enset_spring_boot.dtos;

import dev.derrick.enset_spring_boot.entities.PaymentStatus;
import dev.derrick.enset_spring_boot.entities.PaymentType;
import dev.derrick.enset_spring_boot.entities.Student;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;


}
