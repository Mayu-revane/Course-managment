package com.courseMangemnet.course.Service;

import com.courseMangemnet.course.dto.CourseDto;
import com.courseMangemnet.course.entity.Course;
import com.courseMangemnet.course.entity.Instructor;
import com.courseMangemnet.course.mapper.CourseMapper;
import com.courseMangemnet.course.repository.CourseRepository;
import com.courseMangemnet.course.repository.InstructorRepository;
import com.courseMangemnet.course.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDto getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(CourseMapper::toDTO).orElse(null);
    }

    public CourseDto createCourse(CourseDto courseDto) {
        Course course = CourseMapper.toEntity(courseDto);
        course = courseRepository.save(course);
        return CourseMapper.toDTO(course);
    }

    @Transactional(
            isolation = Isolation.DEFAULT,
            propagation = Propagation.REQUIRED,
            readOnly = true
    )
    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if (existingCourse.isPresent()) {
            Course course = CourseMapper.toEntity(courseDto);
            course.setId(id);
            course = courseRepository.save(course);
            return CourseMapper.toDTO(course);
        } else {


            return null;
        }
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public CourseDto addInstructorToCourse(Long courseId, Long instructorId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("course with Id : " + courseId + " not found. "));
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor with Id : " + instructorId + " not found. "));

        course.setInstructor(instructor);

        course = courseRepository.save(course);

        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .instructorName(course.getInstructor().getName())
                .build();
    }


}
