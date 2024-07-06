package pl.mikus.learning.jobapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableTransactionManagement
@EnableAspectJAutoProxy
public class JobAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobAppApplication.class, args);
    }

}
