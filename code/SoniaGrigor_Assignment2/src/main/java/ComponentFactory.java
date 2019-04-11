
import model.persistence.my_utility.HibernateUtil;

public class ComponentFactory {

    private static HibernateUtil instance;


    public static synchronized HibernateUtil getInstance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new HibernateUtil(componentsForTests);
        }
        return instance;
    }


}
