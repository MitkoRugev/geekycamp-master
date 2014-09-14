package geeky.camp.jpa;

import geeky.camp.jpa.dao.CourseDAO;
import geeky.camp.jpa.dao.FacilityDAO;
import geeky.camp.jpa.dao.StudentDAO;
import geeky.camp.jpa.entities.Course;
import geeky.camp.jpa.entities.Student;

import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppLauncher {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("geeky.camp");
		StudentDAO studentDAO = new StudentDAO(emf);
		CourseDAO courseDAO = new CourseDAO(emf);
		FacilityDAO facilityDAO = new FacilityDAO(emf);
		studentDAO.createNewStudent("1234", "mincho", "mincho", new Date(), 17);

		
	}
}
