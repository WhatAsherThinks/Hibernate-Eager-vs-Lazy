package demo;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args){

        //create session Factory
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{

            //start a transaction
            session.beginTransaction();

            //get instructor by primary key/id
            int tempID = 1;
            Instructor tempInstructor = session.get(Instructor.class, tempID);

            System.out.println("Found Instructor: " + tempInstructor);

            //delete the instructors
            if(tempInstructor != null){
                System.out.println("deleting: " +tempInstructor);

                //Note: This will also delete the associated details (instructorDetails object) because of CascadeType=All
                session.delete(tempInstructor);
            }

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }finally{
            //close the session factory
            factory.close();
        }

    }
}
