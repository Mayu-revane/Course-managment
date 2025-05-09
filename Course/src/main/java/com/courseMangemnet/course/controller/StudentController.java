package com.courseMangemnet.course.controller;


import com.courseMangemnet.course.dto.StudentCourseInstructorDTO;
import com.courseMangemnet.course.dto.StudentDto;
import com.courseMangemnet.course.entity.Student;
import com.courseMangemnet.course.Service.CourseService;
import com.courseMangemnet.course.Service.StudentService;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public List<StudentDto>getAllStudent(){
        return studentService.getAllStudents();
    }

    @PostMapping("/add")
    public StudentDto createStudent(@RequestBody StudentDto studentDto){
        return studentService.createStudent(studentDto);
    }
    @GetMapping("/id/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/update/{id}")
    public StudentDto updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        return studentService.updateStudent(id, studentDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }


   // New endpoint to assign a course to a student
    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Student> addCourseToStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        Student student = studentService.addCourseToStudent(studentId, courseId);


        return ResponseEntity.ok(student);
    }


    @GetMapping("/by-course")
    public ResponseEntity<List<StudentDto>>getStudentByCourseName(@RequestParam String courseTitle){


        List<StudentDto> studentDto=studentService.getStudentByCourseTitle(courseTitle);
        return ResponseEntity.ok(studentDto);
    }


    @GetMapping("/course-instructor")
    public ResponseEntity<List<StudentCourseInstructorDTO>>getInstructorByCourse(@RequestParam String courseTitle){
       List<StudentCourseInstructorDTO> result=studentService.getStudentAndInstructorByCourseTitle(courseTitle);
       return ResponseEntity.ok(result);
    }

    @GetMapping("/distinct")
    public List<StudentDto> getDistinctStudentsWithCourses() {
        return studentService.getDistinctStudentsWithCourses();
    }

    @GetMapping("/getKey")
    public String getSecretKey(){

        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println("Key : "+base64Key);

        return base64Key;
    }
}
