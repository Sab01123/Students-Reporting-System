package com.student.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.student.model.Student;

@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, String> {

}
