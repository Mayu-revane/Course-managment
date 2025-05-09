package com.courseMangemnet.course.mapper;

import com.courseMangemnet.course.dto.CourseDto;
import com.courseMangemnet.course.entity.Course;
import com.courseMangemnet.course.entity.Student;

import java.util.stream.Collectors;

public class CourseMapper {


    public static CourseDto toDTO(Course course) {
      return new CourseDto(
              course.getId(),
              course.getTitle(),
              course.getStudents().stream().map(Student::getName).collect(Collectors.toSet()),
              course.getInstructor() !=null ?course.getInstructor().getName():null


      );
    }

    public static Course toEntity(CourseDto dto) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setTitle(dto.getTitle());
        return course;
    }

}
