package com.konoplastiy.orm.utils;

import com.github.javafaker.Faker;
import com.konoplastiy.orm.entities.*;
import com.konoplastiy.orm.repositories.StudentIdCardRepository;
import com.konoplastiy.orm.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;
import java.util.List;

public class InitializationData implements CommandLineRunner {

    private final StudentRepository studentRepository;

    private final StudentIdCardRepository studentIdCardRepository;

    @Autowired
    public InitializationData(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository) {
        this.studentRepository = studentRepository;
        this.studentIdCardRepository = studentIdCardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
        Student student = new Student(
                firstName,
                lastName,
                email,
                faker.number().numberBetween(17, 55));

        student.addBook(
                new Book("Clean Code", LocalDateTime.now().minusDays(4)));

        student.addBook(
                new Book("Think and Grow Rich", LocalDateTime.now()));

        student.addBook(
                new Book("Spring Data JPA", LocalDateTime.now().minusYears(1)));

        StudentIdCard studentIdCard = new StudentIdCard(
                "123456789",
                student);

        student.setStudentIdCard(studentIdCard);

        student.addEnrolment(new Enrolment(
                new EnrolmentId(1L, 1L),
                student,
                new Course("Computer Science", "IT"),
                LocalDateTime.now()
        ));

        student.addEnrolment(new Enrolment(
                new EnrolmentId(1L, 2L),
                student,
                new Course("Spring Data JPA", "IT"),
                LocalDateTime.now().minusDays(18)
        ));

        student.addEnrolment(new Enrolment(
                new EnrolmentId(1L, 2L),
                student,
                new Course("Spring Data JPA", "IT"),
                LocalDateTime.now().minusDays(18)
        ));


        studentRepository.save(student);

        studentRepository.findById(1L)
                .ifPresent(s -> {
                    System.out.println("fetch book lazy...");
                    List<Book> books = student.getBooks();
                    books.forEach(book -> {
                        System.out.println(
                                s.getFirstName() + " borrowed " + book.getBookName());
                    });
                });
    }
}
