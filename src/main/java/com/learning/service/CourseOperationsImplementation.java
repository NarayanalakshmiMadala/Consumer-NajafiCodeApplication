package com.learning.service;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.learning.response.Courses;

import reactor.core.publisher.Mono;

@Service
public class CourseOperationsImplementation implements ICourseOperations
{
	private WebClient webClient;
	private static final String BASE_URL = "http://localhost:8888/Telusko/courses";
	
	public CourseOperationsImplementation(WebClient.Builder webClientBuilder)
	{
		this.webClient=webClientBuilder.baseUrl(BASE_URL).build();
	}
	
	@Override
	public Mono<String> enrollCourse(Courses course) {
		return this.webClient.post()
				.uri("/addCourse")
				.bodyValue(course)
				.retrieve()
				.bodyToMono(String.class);
	}
	@Override
	public Courses getCourceInfoById(Integer id) {
		Courses courseDetails= this.webClient.get()
				.uri("/fetchCourse/{id}",id)
				.retrieve()
				.bodyToMono(Courses.class).block();
		return courseDetails;
	}
	/*@Override
	public Model getAllCourceInfo(Model model) {
		Flux<Courses> coursesFlux=this.webClient.get()
				.uri("/fetchCourses")
				.retrieve()
				.bodyToFlux(Courses.class);
		
		coursesFlux.collectList()
					.subscribe(courses -> 
					{
						model.addAttribute("courses",courses);
						System.out.println("List Of Courses are :"+courses);
					});
		return model;
	}*/
	
	public List<Courses> getAllCourceInfo()
	{
		Courses[] list=this.webClient.get()
						.uri("/fetchCourses")
						.retrieve()
						.bodyToMono(Courses[].class)
						.block();
						  
		return Arrays.stream(list).toList();
				
	}
	@Override
	public Mono<String> updateCourse(Integer id, Courses course) {
		return this.webClient.put()
				.uri("/updateCourse/{id}",id)
				.bodyValue(course)
				.retrieve()
				.bodyToMono(String.class);
				 
		 
	}
	@Override
	public Mono<String> deleteCourse(Integer id) {
		return this.webClient.delete()
				.uri("/deleteCourse/{id}",id)
				.retrieve()
				.bodyToMono(String.class);
	}
}
