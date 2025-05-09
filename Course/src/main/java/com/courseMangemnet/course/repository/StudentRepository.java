package com.courseMangemnet.course.repository;

import com.courseMangemnet.course.dto.StudentCourseInstructorDTO;
import com.courseMangemnet.course.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {


    @Query("SELECT s FROM Student s  JOIN s.enrolledCourses c WHERE c.title= :courseTitle")
    List<Student> findStudentByCourseTitle(@Param("courseTitle") String courseTitle);



   @Query("SELECT new com.courseMangemnet.course.dto.StudentCourseInstructorDTO(s.name, c.title, i.name) " +
           "FROM Student s JOIN s.enrolledCourses c JOIN c.instructor i " +
            "WHERE c.title = :courseTitle")
   List<StudentCourseInstructorDTO> findStudentsAndInstructorByCourseTitle(@Param("courseTitle") String courseTitle);


    @Query("SELECT DISTINCT s FROM Student s JOIN FETCH s.enrolledCourses")
    List<Student> getDistinctStudentWithCourses();






}
