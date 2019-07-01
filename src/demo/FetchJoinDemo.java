package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
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

            //Option 2: Hibernate Query
            //join fetch will get the instructor and courses
            Query<Instructor> query = session.createQuery(" select i from Instructor i "
                    + "JOIN FETCH i.courses "
                    + "where  i.id =:theInstructorId", Instructor.class);

            //set parameter on query
            query.setParameter("theInstructorId",theID);

            //execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();

            System.out.println("Asher Printing: Instructor: " + tempInstructor);

            //commit transaction
            session.getTransaction().commit();

            //close the session
            session.close();

            System.out.println("Asher Printing: The session is now closed!\n");

            //get courses for the instructor
            //Courses were already loaded in memory via the HQL query writtne abnove
            System.out.println("Asher Printing: Courses: " + tempInstructor.getCourses());

            System.out.println("Asher Printing : Done!");

        }finally {

            //clean up code: close session and factory
            session.close();
            factory.close();
        }

    }
}
