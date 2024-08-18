package com.luvs.advancecruddemo.dao;

import com.luvs.advancecruddemo.entity.Instructor;
import com.luvs.advancecruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImple implements AppDAO{

    // define field for entityManager
    private EntityManager entityManager;
    // Inject entity Manager using constructor injection
    @Autowired
    public AppDAOImple(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
    // Add Transactional as we are persisting/saving in the database
    entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        // this will also retrieve the instructor details object
        // Because of default behaviour of @OneToOne fetch type is eager...
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the Instructor
        Instructor tempInstructor=entityManager.find(Instructor.class,theId);

        //delete the Instructor
        // This will also delete the instructor details object because of CascadeType.ALL
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // retrieve instructor detail
        InstructorDetail tempInstructorDetail=entityManager.find(InstructorDetail.class,theId);
        // remove the associated object reference
        // break the bi-directional link
        System.out.println("Some Data:"+tempInstructorDetail);
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }
}
