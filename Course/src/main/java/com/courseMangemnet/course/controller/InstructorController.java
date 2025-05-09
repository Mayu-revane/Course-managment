package com.courseMangemnet.course.controller;


import com.courseMangemnet.course.dto.InstructorDto;
import com.courseMangemnet.course.Service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/all")
    public List<InstructorDto>getAllInstructor(){
        return instructorService.getAllInstructors();
    }

    @GetMapping("id/{id}")
    public InstructorDto InstructorFindById(@PathVariable Long  id){
        return instructorService.getInstructorById(id);
    }


    @PostMapping("/add")
    public InstructorDto createInstructor(@RequestBody InstructorDto instructorDto){

        return instructorService.createInstructor(instructorDto);

    }

    @PutMapping("/update/{id}")
    public InstructorDto  updateInstructor ( @PathVariable Long id,@RequestBody InstructorDto instructorDto){
        return instructorService.updateInstructor(id,instructorDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteInstructor(@PathVariable Long id){
         instructorService.deleteInstructor(id);
    }



}
