package dev.derrick.enset_spring_boot;

import dev.derrick.enset_spring_boot.entities.Payment;
import dev.derrick.enset_spring_boot.entities.PaymentStatus;
import dev.derrick.enset_spring_boot.entities.PaymentType;
import dev.derrick.enset_spring_boot.entities.Student;
import dev.derrick.enset_spring_boot.repository.PaymentRepository;
import dev.derrick.enset_spring_boot.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class EnsetSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnsetSpringBootApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository) {
		return args -> {
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Derrick").code("123456").programId("SDIA").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Imane").code("123457").programId("SDIA").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Cica").code("123458").programId("GLSID").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Falonne").code("123459").programId("BDCC").build());
			PaymentType[] paymentTypes = PaymentType.values();
			Random random = new Random();
			studentRepository.findAll().forEach(student -> {
				int index = random.nextInt(paymentTypes.length);
				for (int i=0; i<10;i++){
					Payment payment = Payment.builder().amount(1000+(int)(Math.random()+20000)).type(paymentTypes[index]).status(PaymentStatus.CREATED).date(LocalDate.now()).student(student).build();
					paymentRepository.save(payment);

				}
			});
		};
	}
}
