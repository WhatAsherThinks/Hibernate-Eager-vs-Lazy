package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args){

        //create session Factory
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{

            //create the objects

            Instructor tempInstructor = new Instructor("Susan", "Tulip","STulip@coder.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com/STulip","Gaming");

            //associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //start a transaction
            session.beginTransaction();

            //save the instructor
            //Note: this will also save the details object becauyse of CascadeType.ALL
            System.out.println("Saving Instructor: " + tempInstructor);
            session.save(tempInstructor);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }finally{
            //close the session and sessionfactory
            session.close();
            factory.close();
        }

    }
}
