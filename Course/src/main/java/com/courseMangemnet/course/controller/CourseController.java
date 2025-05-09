package com.courseMangemnet.course.controller;

import com.courseMangemnet.course.dto.CourseDto;
import com.courseMangemnet.course.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/id/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping("/add")
    public CourseDto createCourse(@RequestBody CourseDto courseDto) {
        return courseService.createCourse(courseDto);
    }

    @PutMapping("/update/{id}")
    public CourseDto updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        return courseService.updateCourse(id, courseDto);
    }

    @DeleteMapping("/id/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @PostMapping("/{courseId}/instructor/{instructorId}")
    public ResponseEntity<?> addInstructorToCourse(@PathVariable Long courseId , @PathVariable Long instructorId ){

        CourseDto course= courseService.addInstructorToCourse(courseId,instructorId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(course);
    }
}
