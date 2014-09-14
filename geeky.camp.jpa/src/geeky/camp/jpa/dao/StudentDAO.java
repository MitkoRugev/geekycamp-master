package geeky.camp.jpa.dao;

import geeky.camp.jpa.entities.Course;
import geeky.camp.jpa.entities.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.RollbackException;

public class StudentDAO {
	protected EntityManagerFactory emf;

	public StudentDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public void deleteStudent(Object primaryKey) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Student willBeDeleted = em.find(Student.class, primaryKey);
			em.remove(willBeDeleted);
			tx.commit();
			System.out.println("Entry : " + willBeDeleted + " removed from db");
		} catch (RollbackException e) {
			System.out.println("Couldn't commit transaction, db will be reverted");
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			if (em != null)
				em.close();
		}
	}

	public void createNewStudent(String fn, String firstName, String lastName,
			Date birthDate, Integer credits) {
		Student newStudent = new Student(fn, firstName, lastName, credits,
				birthDate);
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(newStudent);
			tx.commit();
		} catch (RollbackException e) {
			System.out.println("Couldn't commit transaction, db will be reverted");
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			if (em != null)
				em.close();
		}
		System.out.println("Student created " + newStudent);
	}

	public void updateStudent(Student updateInfo) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Student willBeUpdated = em.find(Student.class,
					updateInfo.getFacultyNumber());
			System.out.println("Student " + willBeUpdated
					+ " will be updated to " + updateInfo);
			updateProps(willBeUpdated, updateInfo);
			tx.commit();
			System.out.println("Student updated with success");
		} catch (RollbackException e) {
			System.out
					.println("Couldn't commit transaction, db will be reverted");
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			if (em != null)
				em.close();
		}
	}
	
	public Collection<Student> findAllStudentsWithCreditsMoreThan(
			Integer credits) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em
					.createQuery(
							"SELECT s FROM Student s WHERE s.credits > :credits",
							Student.class).setParameter("credits", credits)
					.getResultList();
		} finally {
			if (em != null)
				em.close();
		}
	}
	
	public Student findByPrimaryKey(Object primaryKey) { 
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em.find(Student.class, primaryKey);
		}  finally {
			if (em != null)
				em.close();
		}
	}

	public Collection<Student> findStudentsOfAGivenCourse(String courseName) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em.createQuery("SELECT c.students FROM Course c WHERE c.name = :courseName",Student.class).setParameter("courseName", courseName).getResultList();
		} finally {
			if (em != null)
				em.close();
		}
	}
	
	public Collection<Student> findStudentsOfAGivenFacility(String facilityName) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em.createQuery("SELECT f.students FROM Facility f WHERE f.name = :facilityName",Student.class).setParameter("name", facilityName).getResultList();
		} finally {
			if (em != null)
				em.close();
		}
	}
	
	public Collection<Student> findStudentsOfAGivenFacilityAndCourse(String facilityName,String courseName) {
		Collection<Student> a=findStudentsOfAGivenFacility(facilityName);
		Collection<Student> b=findStudentsOfAGivenCourse(courseName);
		Collection<Student> toReturn=new ArrayList<Student>();
		for(Student s:a){
			if(b.contains(s)) toReturn.add(s);
		}
		return toReturn;
	}

	private void updateProps(Student willBeUpdated, Student updateInfo) {
		willBeUpdated.setBirthDate(updateInfo.getBirthDate());
		willBeUpdated.setCredits(updateInfo.getCredits());
		willBeUpdated.setFirstName(updateInfo.getFirstName());
		willBeUpdated.setLastName(updateInfo.getLastName());
	}
	
	public void addCourseToStudent(Student b,Course a){
		b.addCourse(a);
	}

}
