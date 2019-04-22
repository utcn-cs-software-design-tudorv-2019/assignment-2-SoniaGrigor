package model.persistence.database;

import static model.persistence.my_utility.DBConstants.Schemas.PRODUCTION;
import static model.persistence.my_utility.DBConstants.Schemas.TEST;

public class DBConnectionFactory {

    public JDBConnectionWrapper getConnectionWrapper(boolean test) {
        if (test) {
            return new JDBConnectionWrapper(TEST);
        } else {
            return new JDBConnectionWrapper(PRODUCTION);
        }
    }
}
