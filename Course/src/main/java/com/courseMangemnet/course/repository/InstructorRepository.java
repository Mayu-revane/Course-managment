package com.courseMangemnet.course.repository;

import com.courseMangemnet.course.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {

}
