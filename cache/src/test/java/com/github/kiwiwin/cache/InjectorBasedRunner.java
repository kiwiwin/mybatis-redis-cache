package com.github.kiwiwin.cache;

import com.github.kiwiwin.db.mybatis.Models;
import com.google.inject.Injector;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import static com.google.inject.Guice.createInjector;

public class InjectorBasedRunner extends BlockJUnit4ClassRunner {
    protected Injector injector;

    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @param klass
     * @throws InitializationError if the test class is malformed.
     */
    public InjectorBasedRunner(Class<?> klass) throws InitializationError {
        super(klass);

        this.injector = createInjector(new Models("development"));
        this.injector.injectMembers(this);
    }
}
