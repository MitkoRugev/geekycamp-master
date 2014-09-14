package geeky.camp.jpa.dao;

import geeky.camp.jpa.entities.Facility;
import geeky.camp.jpa.entities.Student;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

public class FacilityDAO {
	protected EntityManagerFactory emf;

	public FacilityDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public void deleteFacility(Object number) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Facility willBeDeleted = em.find(Facility.class, number);
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

	public void createNewFacility(String name,Integer number) {
		Facility newFacility = new Facility(number,name);
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(newFacility);
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
		System.out.println("Facility created " + newFacility);
	}

	public void updateFacility(Facility updateInfo) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Facility willBeUpdated = em.find(Facility.class,
					updateInfo.getNumber());
			System.out.println("Facility " + willBeUpdated
					+ " will be updated to " + updateInfo);
			updateProps(willBeUpdated, updateInfo);
			tx.commit();
			System.out.println("Facility updated with success");
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
	
	public Facility findByPrimaryKey(Object primaryKey) { 
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em.find(Facility.class, primaryKey);
		}  finally {
			if (em != null)
				em.close();
		}
	}

	private void updateProps(Facility willBeUpdated, Facility updateInfo) {
		willBeUpdated.setName(updateInfo.getName());
		willBeUpdated.setNumber(updateInfo.getNumber());
	}
	
	public boolean facilityContainsStudent(String facilityName,String studentFN){
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em.find(Facility.class,facilityName).hasStudent(em.find(Student.class, studentFN));
		}  finally {
			if (em != null)
				em.close();
		}
	}
	
	public void addStudentToFacility(Student s,String facilityName){
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.find(Facility.class, facilityName).addStudent(s);
		}  finally {
			if (em != null)
				em.close();
		}
	}
}
