package com.courseMangemnet.course.Service;


import com.courseMangemnet.course.dto.InstructorDto;
import com.courseMangemnet.course.entity.Instructor;
import com.courseMangemnet.course.mapper.InstructorMapper;
import com.courseMangemnet.course.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<InstructorDto> getAllInstructors() {
        return instructorRepository.findAll().stream()
                .map(InstructorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public InstructorDto getInstructorById(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        return instructor.map(InstructorMapper::toDTO).orElse(null);
    }

    public InstructorDto createInstructor(InstructorDto instructorDto) {
        Instructor instructor = InstructorMapper.toEntity(instructorDto);
        instructor = instructorRepository.save(instructor);
        return InstructorMapper.toDTO(instructor);
    }

    public InstructorDto updateInstructor(Long id, InstructorDto instructorDto) {
        Optional<Instructor> existingInstructor = instructorRepository.findById(id);
        if (existingInstructor.isPresent()) {
            Instructor instructor = InstructorMapper.toEntity(instructorDto);
            instructor.setId(id);
            instructor = instructorRepository.save(instructor);
            return InstructorMapper.toDTO(instructor);
        } else {
            return null;
        }
    }

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }



}
