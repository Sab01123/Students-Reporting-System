package com.student.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.student.model.CollegeSemester;
import com.student.model.Student;
import com.student.repository.StudentRepository;
import com.student.service.StudentService;

@Controller
public class StudentUIController {
	
	@Autowired
    private StudentService service;

    @Autowired
    private StudentRepository repository;
    
    
    @RequestMapping("/")
    public ModelAndView Index(){
        ModelAndView model = new ModelAndView("index.jsp");
        List<Student> studentList = service.getAllStudents();
        model.addObject("students", studentList);

        String averageClass1 = service.averagePercentage(1);
        String averageClass2 = service.averagePercentage(2);

        HashMap<String, Double> map2 = service.top2();

        
        String s1="";
        String s2="";
        Double x1 = 0.0;
         Double x2 = 0.0;
        for(Map.Entry<String , Double> entry : map2.entrySet()){
            if(entry.getValue() > x1)
            {
            	x2 = x1.doubleValue();
                x1 = entry.getValue().doubleValue();
                s2 = s1.toString();
                s1 = entry.getKey().toString();
            }
            else if(entry.getValue() > x2)
            {
            	x2 = entry.getValue().doubleValue();
                s2 = entry.getKey().toString();
            }
        }

         Optional<Student> student1 = repository.findById(s1);
         String str1 = student1.get().getName();
         Optional<Student> student2 = repository.findById(s2);
         String str2 = student2.get().getName();


        model.addObject("averageClass1", averageClass1);
        model.addObject("averageClass2", averageClass2);

        model.addObject("top1", s1);
        model.addObject("top2", s2);
        model.addObject("top1_score", x1);
        model.addObject("top2_score", x2);
        model.addObject("top1_name", str1);
        model.addObject("top2_name", str2);

        return model;
    }


    @RequestMapping(value = "/addSemMarks", method = RequestMethod.POST)
    public String addSemesterMarks(@RequestParam("semId") int semId, @RequestParam("Id") String Id,  @RequestParam("English") String English,
                                   @RequestParam("Maths") String Maths, @RequestParam("Science") String Science)
    {

        int sem = semId;


        try {
            if(English != "" && !English.isEmpty())
            {
                service.addMarks(Id, sem, "English", Integer.parseInt(English));
            }
            if(Science != "" && !Science.isEmpty())
            {
                service.addMarks(Id, sem, "Science", Integer.parseInt(Science));
            }
            if(Maths != "" && !Maths.isEmpty())
            {
                service.addMarks(Id, sem, "Maths", Integer.parseInt(Maths));
            }

        }catch (Exception e)
        {
            System.out.println(e.toString());
            return e.toString();
        }

        return "redirect:/";
    }


    @RequestMapping(value = "/addStudentMarks", method = RequestMethod.POST)
    public String addMarks(@RequestParam("Id") String Id, @RequestParam("sem") int sem, @RequestParam("subject") String subject, @RequestParam("marks") int marks)
    {
        try {
            String response = service.addMarks(Id, sem, subject, marks);
            return "redirect:/";
        }catch (Exception e)
        {
            System.out.println(e.toString());
            return e.toString();
        }

    }

    @RequestMapping(value = "/deleteStudent", method = RequestMethod.POST)
    public String deleteStudentByID(@RequestParam("SId") String SId)
    {
        try {
            String response = service.deleteStudent(SId);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return (e.toString());
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudentByName(@RequestParam("SName") String SName)
    {
        try {
            String response = service.addStudent(SName);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return e.toString();
        }

        return "redirect:/";

    }




    @RequestMapping("/home")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("home.jsp");
        model.addObject("message", "this.message");
        System.out.println("this is home");
        return model;
    }


    @RequestMapping("/save")
    public String saveStudent() throws IOException {
        Student s = new Student("101","Varsha",new ArrayList<CollegeSemester>());
        System.out.println(s.toString());
        return "save.jsp";
    }

    @RequestMapping("/find")
    public String find(Model model){

        return "find.jsp";
    }


}
