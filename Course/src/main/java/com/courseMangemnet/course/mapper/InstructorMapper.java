package com.courseMangemnet.course.mapper;

import com.courseMangemnet.course.dto.InstructorDto;
import com.courseMangemnet.course.entity.Course;
import com.courseMangemnet.course.entity.Instructor;

import java.util.stream.Collectors;

public class InstructorMapper {
    public static InstructorDto toDTO(Instructor instructor) {
        return new InstructorDto(
                instructor.getId(),
                instructor.getName(),
                instructor.getEmail(),
                instructor.getCourses().stream().map(Course::getId).collect(Collectors.toList())
        );
    }

    public static Instructor toEntity(InstructorDto dto) {
        Instructor instructor = new Instructor();
        instructor.setId(dto.getId());
        instructor.setName(dto.getName());
        instructor.setEmail(dto.getEmail());
        return instructor;
    }


}
