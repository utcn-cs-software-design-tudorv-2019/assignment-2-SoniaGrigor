import model.persistence.entity.Course;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

import java.util.List;

public class Runner {

    @Test
    public void crud() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        create(session);
        read(session);

        update(session);
        read(session);

        delete(session);
        read(session);

        session.close();
    }

    private void delete(Session session) {
        System.out.println("Deleting iia record...");
        Course iai = (Course) session.get(Course.class, "iia");

        session.beginTransaction();
        session.delete(iai);
        session.getTransaction().commit();
    }

    private void update(Session session) {
        System.out.println("Updating iia credit...");
        Course iai = (Course) session.get(Course.class, "iia");
        iai.setName("Introduction to Artificial Intelligence");
        iai.setCredit(5);

        session.beginTransaction();
        session.saveOrUpdate(iai);
        session.getTransaction().commit();
    }

    private void create(Session session) {
        System.out.println("Creating course records...");
        Course iai = new Course();
        iai.setName("Name Test1");
        iai.setCredit(4);

        Course gps = new Course();
        gps.setName("GPS");
        gps.setCredit(5);

        session.beginTransaction();
        session.save(gps);
        session.getTransaction().commit();
    }

    private void read(Session session) {
        Query q = session.createQuery("select _course from Course _course");

        List<Course> courses = q.list();

        System.out.println("Reading course records...");
        System.out.printf("%-30.30s  %-30.30s%n", "Model", "Price");
        for (Course c : courses) {
            System.out.printf(c.toString());
        }
    }
}
