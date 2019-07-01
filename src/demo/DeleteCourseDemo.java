package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {
    public static void main(String [] args){
        //create session Factory
        SessionFactory factory = new Configuration().configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            //start a transaction
            session.beginTransaction();

            //get the course
            int theID = 1;

            Course tempCourse = session.get(Course.class, theID);

            //delete the course
            System.out.println("Deleting course: " + tempCourse);
            session.delete(tempCourse);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
            //add clean up code: close session and factory
            session.close();
            factory.close();
        }
    }
}
