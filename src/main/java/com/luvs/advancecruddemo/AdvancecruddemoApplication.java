package com.luvs.advancecruddemo;

import com.luvs.advancecruddemo.dao.AppDAO;
import com.luvs.advancecruddemo.entity.Instructor;
import com.luvs.advancecruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvancecruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancecruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
//			createinstructor(appDAO);
//			findInstructor(appDAO);
//			deleteinstructorById(appDAO);
//			System.out.println("It is working !");
//			findInstructorDetail(appDAO);
			deleteInstructorDetail(appDAO);
			
			
		};

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId=2;
		System.out.println("DEleting Instructor of Id:"+theId);

		appDAO.deleteInstructorDetailById(1);


	}

	private void findInstructorDetail(AppDAO appDAO) {
		// get the instructor detail object
		int theId=2;
		InstructorDetail tempInstructorDetail=appDAO.findInstructorDetailById(theId);
		// print the instructor detail
		System.out.println(tempInstructorDetail);
		// print the associated instructor
		System.out.println(tempInstructorDetail);

	}

	private void deleteinstructorById(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleteing the Id "+theId);
		appDAO.deleteInstructorById(theId);

	}

	private void findInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor Id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor:"+tempInstructor);
		System.out.println("the associated instructorDetail only: "+tempInstructor.getInstructorDetail());
	}

	private void createinstructor(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Auro Saswat","Raj","auro.raj@gmail.com");

		// create the instructor detail
		InstructorDetail tempinstructorDetail = new InstructorDetail("www.google.com/auro","Loves to sleep");

		// associate the objects
		tempInstructor.setInstructorDetail(tempinstructorDetail);

		// save the instructor
		// NOTE: this will also save the details object
		// because of CascadeType.ALL
		System.out.println("Saving Instructor "+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!");

	}

}
