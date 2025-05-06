package edu.mohamed.restaurantfinder.config;

import org.hibernate.community.dialect.SQLiteDialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class CustomSQLiteDialect extends SQLiteDialect {
    public CustomSQLiteDialect() {
        super();
        //registerFunction("acos", new StandardSQLFunction("acos"));
       /// registerFunction("cos", new StandardSQLFunction("cos"));
       // registerFunction("sin", new StandardSQLFunction("sin"));
       // registerFunction("radians", new StandardSQLFunction("radians"));
    }
}
