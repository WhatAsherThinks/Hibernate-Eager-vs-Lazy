package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
    public static void main(String[] args){
        //create session factory
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

            //get the instructor from DB
            int theID = 1;
            Instructor tempInstructor = session.get(Instructor.class,theID);

            System.out.println("Asher Printing: Instructor: " + tempInstructor);

            //get course for the instructor
            //if its EAGER loading then you dont need the getter method,
            // LAZY fetch needs getter because its only loaded on demand. it will perform a hibernate Query (HQL)
            System.out.println("Asher Printing: Courses: " + tempInstructor.getCourses());


            //commit transaction
            session.getTransaction().commit();

            //close the session
            session.close();

            System.out.println("Asher Printing: The session is now closed!\n");

            //option 1: call the getter method while session is open.
            // This means that the information is already stored in memory while the session was open ,
            // so its not going back to the database to retrieve anything.


            //get courses for the instructor
            System.out.println("Asher Printing: Courses: " + tempInstructor.getCourses());


            System.out.println("Asher Printing : Done!");

        }finally {

            //clean up code: close session and factory
            session.close();
            factory.close();
        }

    }
}
