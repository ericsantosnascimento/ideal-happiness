package br.com.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@SpringBootApplication
@ContextConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = ASSIGNABLE_TYPE, value = {AcmeApplication.class})})
public class AcmeApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(AcmeApplicationTest.class, args);
    }
}