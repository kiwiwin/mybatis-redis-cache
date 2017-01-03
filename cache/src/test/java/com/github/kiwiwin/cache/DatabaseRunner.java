package com.github.kiwiwin.cache;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.rules.TestRule;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRunner extends InjectorBasedRunner {
    @Inject
    private SqlSessionFactory sessionFactory;

    public DatabaseRunner(final Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected List<TestRule> getTestRules(Object target) {
        List<TestRule> rules = new ArrayList<>();
        rules.add(cleanData);
        rules.addAll(super.getTestRules(target));
        return rules;
    }

    private final TestRule cleanData = (base, description) -> new Statement() {
        @Override
        public void evaluate() throws Throwable {
            try {
                cleanData();
                base.evaluate();
            } finally {
            }
        }
    };

    private void cleanData() throws SQLException {
        SqlSession sqlSession = sessionFactory.openSession();
        Connection connection = sqlSession.getConnection();
        java.sql.Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM PRODUCTS");

        connection.commit();
    }
}
