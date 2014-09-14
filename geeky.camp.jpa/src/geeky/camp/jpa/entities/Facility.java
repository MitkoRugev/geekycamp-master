package geeky.camp.jpa.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import org.eclipse.persistence.annotations.JoinFetch;
import static org.eclipse.persistence.annotations.JoinFetchType.INNER;
import static javax.persistence.FetchType.EAGER;


@Entity
public class Facility implements Serializable {
	
	@Id
	private Integer number;
	private String name;
	@OneToMany(mappedBy = "facility", fetch = EAGER)
	private Collection<Student> students;
	private static final long serialVersionUID = 3L;

	public Facility() {
		super();
	}
	
	public Facility(Integer number,String name){
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
	
	public boolean hasStudent(Student a){
		return students.contains(a);
	}
	
	public void addStudent(Student a){
		students.add(a);
	}
    
	public Collection<Student> getStudents(){
		return students;
	}
}
