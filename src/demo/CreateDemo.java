package demo;

import entity.Instructor;
import entity.InstructorDetail;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args){

        //create session Factory
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{

            //create the objects

                /* Instructor tempInstructor = new Instructor("Asher", "DeMadet","demadet@coder.com");

                 InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.coder.com/youtube","coding Java");
                 */

            Instructor tempInstructor = new Instructor("Patricia", "Madison","Madison@coder.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com/PMadison","Guitar");

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
            //close the session factory
            factory.close();
        }

    }
}
