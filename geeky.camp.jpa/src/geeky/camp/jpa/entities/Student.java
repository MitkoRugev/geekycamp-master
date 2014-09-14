package geeky.camp.jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.FetchType.EAGER;

/**
 * Entity implementation class for Entity: Student
 *
 */
@Entity
public class Student implements Serializable {
	   
	@Id
	private String facultyNumber;
	private String firstName;
	private String lastName;
	private Integer credits;
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	//small hint ;)
	@ManyToMany(fetch = EAGER,mappedBy="students")
	private Collection<Course> courses;
	@ManyToOne
	@JoinColumn(name="Facility", referencedColumnName="Number")
	private Facility facility;
	private static final long serialVersionUID = 1L;

	public Student() {
		super();
	}   
	
	public Student(String facultyNumber, String firstName, String lastName,
			Integer credits, Date birthDate) {
		this.facultyNumber = facultyNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.credits = credits;
		this.birthDate = birthDate;
	}


	public String getFacultyNumber() {
		return this.facultyNumber;
	}

	public void setFacultyNumber(String facultyNumber) {
		this.facultyNumber = facultyNumber;
	}   
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   
	public Integer getCredits() {
		return this.credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	
	@Override
	public String toString() {
		return "Student [facultyNumber=" + facultyNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", credits=" + credits
				+ ", birthDate=" + birthDate + "]";
	}
	
	public void addCourse(Course a){
		courses.add(a);
	}
	
	public void removeCourse(Course a){
		courses.remove(a);
	}
	
	public void setFacility(Facility a){
		facility=a;
	}
	
	public Facility getFacility(){
		return facility;
	}
}
