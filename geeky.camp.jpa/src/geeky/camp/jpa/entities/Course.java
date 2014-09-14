package geeky.camp.jpa.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@Entity
public class Course implements Serializable {

	@Id
	private Integer number;
	private String name;
	@ManyToMany(fetch = EAGER)
	private Collection<Student> students;
	private static final long serialVersionUID = 2L;

	public Course() {
		super();
	}
	
	public Course(Integer number,String name){
		this.number=number;
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public void addStudent(Student a){
		students.add(a);
	}
    
	public Collection<Student> getStudents(){
		return students;
	}
}
