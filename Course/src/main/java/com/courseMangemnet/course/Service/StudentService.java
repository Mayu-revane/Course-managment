package com.courseMangemnet.course.Service;

import com.courseMangemnet.course.dto.StudentCourseInstructorDTO;
import com.courseMangemnet.course.dto.StudentDto;
import com.courseMangemnet.course.entity.Course;
import com.courseMangemnet.course.entity.Student;
import com.courseMangemnet.course.exception.ResourceNotFoundException;
import com.courseMangemnet.course.mapper.StudentDtoMapper;
import com.courseMangemnet.course.repository.CourseRepository;
import com.courseMangemnet.course.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {


    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;



    public List<StudentDto> getAllStudents(){
        return studentRepository.findAll().stream()
                .map(StudentDtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(Long id){
  /*      Optional<Student> student=studentRepository.findById(id);
        return student.map(StudentDtoMapper::toDTO).orElse(null);*/

        Student student=studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student not found"));
                 return StudentDtoMapper.toDTO(student);



    }

    public StudentDto createStudent(StudentDto studentDto){
       /* Student student=new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        studentRepository.save(student);
        return StudentDtoMapper.toDTO(student);*/

        Student student = new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());

        List<Course> courses = new ArrayList<>();
        if (studentDto.getEnrolledCourseTitles() != null) {
            for (String title : studentDto.getEnrolledCourseTitles()) {
                Course course = courseRepository.findByTitle(title)
                        .orElseThrow(() -> new ResourceNotFoundException("course not found"+ title));
                courses.add(course);
            }
        }

        student.setEnrolledCourses(courses);

        student = studentRepository.save(student);
        return StudentDtoMapper.toDTO(student);
    }

    public StudentDto updateStudent(Long id,StudentDto studentDto){
        Optional<Student> existingStudent=studentRepository.findById(id);
        if(existingStudent.isPresent()){
            Student student=existingStudent.get();
            student.setName(studentDto.getName());
            student.setEmail(studentDto.getEmail());
            student=studentRepository.save(student);
            return StudentDtoMapper.toDTO(student);
        }else {
            return null;
        }
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    @Transactional
    public Student addCourseToStudent(Long studentId, Long courseId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (studentOptional.isPresent() && courseOptional.isPresent()) {
            Student student = studentOptional.get();
            Course course = courseOptional.get();

            List<Course> enrolledCourses = student.getEnrolledCourses();
            if (enrolledCourses == null) {
                enrolledCourses = new ArrayList<>();
                student.setEnrolledCourses(enrolledCourses);
            }
            enrolledCourses.add(course);

          return studentRepository.save(student);
        } else {
            throw new IllegalArgumentException("Student or course not found");
        }
    }

    public List<StudentDto> getStudentByCourseTitle(String courseTitle){

        List<Student> students=studentRepository.findStudentByCourseTitle(courseTitle);

        return students.stream()
                .map(StudentDtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<StudentCourseInstructorDTO>getStudentAndInstructorByCourseTitle(String courseTitle){

        return studentRepository.findStudentsAndInstructorByCourseTitle(courseTitle);

    }

    public List<StudentDto> getDistinctStudentsWithCourses(){
        List<Student> students=studentRepository.getDistinctStudentWithCourses();
        return students.stream()
                .map(StudentDtoMapper::toDTO)
                .collect(Collectors.toList());
    }


}
