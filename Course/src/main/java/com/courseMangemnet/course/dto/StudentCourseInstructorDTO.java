package com.courseMangemnet.course.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentCourseInstructorDTO {

    private String studentName;
    private String courseTitle;
    private String instructorName;


    public StudentCourseInstructorDTO(String studentName, String courseTitle, String instructorName) {
        this.studentName = studentName;
        this.courseTitle = courseTitle;
        this.instructorName = instructorName;
    }
}
