package com.student.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Document(indexName = "CollegeSemester-index" )
@Component
public class CollegeSemester {
	
	private int semId;
    private int English = 0;
    private int Maths = 0;
    private int Science = 0;
    
    

    public CollegeSemester() {
    	
    }

    public CollegeSemester(int semId){
        this.semId = semId;
    }
    
    public CollegeSemester(int semId, int english, int maths, int science) {
        this.semId = semId;
        this.English = english;
        this.Maths = maths;
        this.Science = science;
    }

}
