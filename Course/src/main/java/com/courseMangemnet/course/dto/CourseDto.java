package com.courseMangemnet.course.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {

    private Long id;
    private String title;
    private Set<String> studentNames;
    private String instructorName;

}
