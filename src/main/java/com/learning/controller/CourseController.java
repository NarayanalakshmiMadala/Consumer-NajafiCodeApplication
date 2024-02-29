package com.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learning.response.Courses;
import com.learning.service.ICourseOperations;

@Controller
public class CourseController 
{
	@Autowired
	ICourseOperations courseService;
	
	@GetMapping("/courses/all")
    public String getAllCourses(Model model) {
        List<Courses> courseList=courseService.getAllCourceInfo();
        model.addAttribute("courses", courseList);
        return "courses";
    }

    @GetMapping("/courses/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Courses());
        return "addCourse";
    }
    
    @GetMapping("/courses/fetch")
    public String showFetchCourseForm(Model model) {
    	model.addAttribute("course",new Courses());
    	return "viewCourseForm";
    }

    @PostMapping("/courses/added")
    public String addCourse(@ModelAttribute Courses course) {
        courseService.enrollCourse(course).block();
        return "redirect:/courses/all";
    }

    @GetMapping("/courses/update/{id}")
    public String showEditCourseForm(@PathVariable Integer id, Model model) {
        Courses coursedetails=courseService.getCourceInfoById(id);
        model.addAttribute("update_course", coursedetails);
        return "updateForm";
    }

    @PostMapping("/courses/updateCourse/{id}")
    public String updateCourse(@PathVariable Integer id, 
    		@ModelAttribute("course") Courses course) 
    {
        courseService.updateCourse(id, course).block();
        return "redirect:/courses/all";
    }

    @GetMapping("/courses/fetchCourse")
    public String getCourseById(@RequestParam("id")Integer id, @ModelAttribute("course")Courses course, Model model) {
       Courses courseDetails=courseService.getCourceInfoById(id);
        model.addAttribute("course", courseDetails);
        return "viewCourseDetails";
    }
    
    @GetMapping("/courses/delete/{id}")
    public String deleteCourseById(@PathVariable Integer id) 
    {
    	courseService.deleteCourse(id).block();
        return "redirect:/courses/all";
    }
}
