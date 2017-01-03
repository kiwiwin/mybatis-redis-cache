package com.github.kiwiwin.cache;

import com.github.kiwiwin.db.mybatis.Models;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.inject.Injections;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import static com.google.inject.Guice.createInjector;
import static org.jvnet.hk2.guice.bridge.api.GuiceBridge.getGuiceBridge;

public class InjectorBasedRunner extends BlockJUnit4ClassRunner {
    protected ServiceLocator locator = Injections.createLocator();

    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @param klass
     * @throws InitializationError if the test class is malformed.
     */
    public InjectorBasedRunner(Class<?> klass) throws InitializationError {
        super(klass);

        try {
            Injector injector = createInjector(
                    new Models("development"),
                    new AbstractModule() {
                        @Override
                        protected void configure() {
                            bind(ServiceLocator.class).toInstance(locator);
                        }
                    }
            );
            bridge(locator, injector);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        locator.inject(this);
    }

    private static void bridge(ServiceLocator serviceLocator, Injector injector) {
        getGuiceBridge().initializeGuiceBridge(serviceLocator);
        serviceLocator.getService(GuiceIntoHK2Bridge.class).bridgeGuiceInjector(injector);
    }

    @Override
    protected Object createTest() throws Exception {
        Object testClass = super.createTest();
        locator.inject(testClass);
        return testClass;
    }
}
