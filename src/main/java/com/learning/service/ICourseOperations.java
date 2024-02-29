package com.learning.service;

import java.util.List;

import com.learning.response.Courses;

import reactor.core.publisher.Mono;

public interface ICourseOperations
{
	public Mono<String> enrollCourse(Courses course);
	public Courses getCourceInfoById(Integer id);
	public List<Courses> getAllCourceInfo();
	public Mono<String> updateCourse(Integer id,Courses course);
	public Mono<String> deleteCourse(Integer id);
}
