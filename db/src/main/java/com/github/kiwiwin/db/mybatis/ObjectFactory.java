package com.github.kiwiwin.db.mybatis;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.glassfish.hk2.api.ServiceLocator;

import javax.inject.Inject;
import java.util.List;

public class ObjectFactory extends DefaultObjectFactory {
    @Inject
    ServiceLocator locator;

    @Override
    @SuppressWarnings(value = "unchecked")
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        T object = super.create(type, constructorArgTypes, constructorArgs);
        locator.inject(object);
        return object;
    }
}
