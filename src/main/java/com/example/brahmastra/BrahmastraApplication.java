package com.example.brahmastra;

import com.example.brahmastra.entity.Project;
import com.example.brahmastra.repository.ProjectRepository;
import org.hibernate.criterion.ProjectionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BrahmastraApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrahmastraApplication.class, args);
    }

}
