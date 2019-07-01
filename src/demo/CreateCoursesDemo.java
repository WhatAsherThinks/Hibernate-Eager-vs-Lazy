package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
    public static void main(String [] args){
        //create session factory
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{

            //start Transaction
            session.beginTransaction();

            //get the instructor from DB
            int theID = 1;
            Instructor tempInstructor = session.get(Instructor.class, theID);


            //create some courses
            Course tempCourse1 = new Course("Air Guitar- The Ultimate Guide");
            Course tempCourse2 = new Course("The Pinball MasterClass");
            Course tempCourse3 = new Course("Clowning 101 - The Animal Ballon");

            //add courses to instructor
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);
            tempInstructor.add(tempCourse3);

            //save the courses
            System.out.println("Saving Courses: " + tempCourse1 + " " + tempCourse2 + " " + tempCourse3);
            session.save(tempCourse1);
            session.save(tempCourse2);
            session.save(tempCourse3);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }finally {
            //close the session and Factory
            session.close();
            factory.close();
        }
    }
}
