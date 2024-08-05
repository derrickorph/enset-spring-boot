package dev.derrick.enset_spring_boot.services;

import dev.derrick.enset_spring_boot.entities.Payment;
import dev.derrick.enset_spring_boot.entities.PaymentStatus;
import dev.derrick.enset_spring_boot.entities.PaymentType;
import dev.derrick.enset_spring_boot.entities.Student;
import dev.derrick.enset_spring_boot.repository.PaymentRepository;
import dev.derrick.enset_spring_boot.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service @Transactional
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public PaymentService(StudentRepository studentRepository, PaymentRepository paymentRepository){
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }
    public Payment savePayment (MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"),"enset-data","payments");

        if(!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"enset-data","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Student student =  studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder().date(date).amount(amount).type(type).student(student).file(filePath.toUri().toString()).status(PaymentStatus.CREATED).build();
        return  paymentRepository.save(payment);
    }
    public Payment updatePaymentStatus (PaymentStatus status, Long id) {
        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(status);
        return  paymentRepository.save(payment);
    }
    public byte[] getPaymentFile(Long paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }
}
