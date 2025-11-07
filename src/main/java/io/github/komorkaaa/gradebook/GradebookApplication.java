package io.github.komorkaaa.gradebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GradebookApplication {

  public static void main(String[] args) {
    SpringApplication.run(GradebookApplication.class, args);
  }

}
