package com.courseMangemnet.course.mapper;

import com.courseMangemnet.course.dto.StudentDto;
import com.courseMangemnet.course.entity.Course;
import com.courseMangemnet.course.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentDtoMapper {

    public static StudentDto toDTO(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        if (student.getEnrolledCourses() != null) {
            studentDto.setEnrolledCourseTitles(student.getEnrolledCourses().stream()
                    .map(course -> course.getTitle())
                    .collect(Collectors.toSet()));
        }
        return studentDto;
    }

    public static Student toEntity(StudentDto dto , List<Course> courses){
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setEnrolledCourses(courses);
        return student;
    }
}
