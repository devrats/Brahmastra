package com.example.brahmastra.repository;

import com.example.brahmastra.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

    Project findProjectById(int id);
    List<Project> findAllByProjectType(String s);
}
