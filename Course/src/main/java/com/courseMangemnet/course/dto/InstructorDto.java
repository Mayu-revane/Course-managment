package com.courseMangemnet.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDto {

    private Long id;
    private String name;
    private String email;
    private List<Long> courseIds;

}
