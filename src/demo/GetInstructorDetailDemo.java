package demo;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {
    public static void main(String[] args){

        //create session Factory
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{

            //start a transaction
            session.beginTransaction();

            //get the instructorDetail object
            int theid = 2;
            InstructorDetail tempInstructorDetail =  session.get(InstructorDetail.class, theid);

            //print the instructorDetail
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            //print the associated instructor
            System.out.println("Associated Instructor: " +tempInstructorDetail.getInstructor());

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            //handles connection leak issue
            session.close();

            //close the session factory
            factory.close();
        }

    }
}
