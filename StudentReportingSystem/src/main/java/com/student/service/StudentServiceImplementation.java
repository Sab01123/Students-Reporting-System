package com.student.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student.model.CollegeSemester;
import com.student.model.Student;
import java.util.Base64;
import java.util.UUID;
import com.student.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	private final String indexName = "student-index";

	@Override
	public String addStudent(String name) {

		String Id = UUIDs.randomBase64UUID();

		CollegeSemester first = new CollegeSemester(1);
		CollegeSemester second = new CollegeSemester(2);
		List<CollegeSemester> semList = new ArrayList<>();
		semList.add(first);
		semList.add(second);

		Student student = new Student(Id, name, semList);

		System.out.println("Student Count : " + Id);
		System.out.println("Student Collegesemesters : " + student.getCollegeSemesters());

		try {
			studentRepository.save(student);
		} catch (Exception e) {
			return e.toString();
		}

		return "Successfully added student";

	}

	// Marks
	@Override
	public String addMarks(String studentId, int semId, String subject, int marks) {

		try {

			Optional<Student> students = studentRepository.findById(studentId);

			if (students.isEmpty()) {
				return "Marks add unsuccessfull, no student with StudentId : " + semId;
			}

			Student student = students.get();
			List<CollegeSemester> semestersList = student.getCollegeSemesters();

			for (CollegeSemester sem : semestersList) {

				System.out.println(sem.getSemId());
				System.out.println(semId);
				System.out.println(sem.getSemId() == semId);
				System.out.println((subject.equals("Maths")));

				if (sem.getSemId() == semId) {
					if (subject.equals("English")) {
						sem.setEnglish(marks);
					} else if (subject.equals("Maths")) {
						sem.setMaths(marks);
					} else if (subject.equals("Science")) {
						sem.setScience(marks);
					} else {
						return "error in subject name";
					}

					break;
				}
			}

			student.setCollegeSemesters(semestersList);
			studentRepository.save(student);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return "marks add success!";

	}

	@Override
	public Student getStudent(String studentId) throws Exception{
	
			Optional<Student> students = studentRepository.findById(studentId);
			if (students.isEmpty()) {
				throw new Exception("getStudent() failed!");
			}
			return students.get();
	}

	@Override
	public List<Student> getAllStudents() {

		try {

			Iterable<Student> students = studentRepository.findAll();
			List<Student> studentList = new ArrayList<>();
			for (Student s : students) {
				studentList.add(s);
			}

			return studentList;

		} catch (Exception e) {

			System.out.println("erro : " + e.toString());
		}

		return new ArrayList<Student>();

	}

	@Override
	public String deleteStudent(String id) {

		try {
			studentRepository.deleteById(id);
		} catch (Exception e) {
			return (e.toString());
		}
		return "Successfully deleted student with Id : " + id;
	}

	@Override
	public String averagePercentage(int sem) {

		Double average = 0.0;

		try {

			List<Double> percentageList = new ArrayList<>();
			Iterable<Student> studentList = studentRepository.findAll();
			for (Student s : studentList) {
				CollegeSemester semester = s.getCollegeSemesters().get(sem - 1);
				Double sum = Double.valueOf((semester.getEnglish() + semester.getMaths() + semester.getScience()));
				Double percentage = sum / 3;
				percentageList.add(percentage);
			}

			for (Double d : percentageList) {
				average += d;
			}

			average = average / percentageList.size();
			System.out.println("average : " + average);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		DecimalFormat df = new DecimalFormat("####0.00");

		return df.format(average);

	}

	@Override
	public HashMap<String, Double> top2() {

		HashMap<String, Double> map = new HashMap<String, Double>();

		try {
			Iterable<Student> studentList = studentRepository.findAll();
			for (Student s : studentList) {
				CollegeSemester semester1 = s.getCollegeSemesters().get(0);
				CollegeSemester semester2 = s.getCollegeSemesters().get(1);
				Double sum = Double.valueOf((semester1.getEnglish() + semester1.getMaths() + semester1.getScience()
						+ semester2.getEnglish() + semester2.getMaths() + semester2.getScience()));
				Double max = sum / 2;
				map.put(s.getId(), max);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return map;

	}
}
