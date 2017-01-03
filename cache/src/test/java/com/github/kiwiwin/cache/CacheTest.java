package com.github.kiwiwin.cache;

import com.github.kiwiwin.cache.core.Product;
import com.github.kiwiwin.cache.mybatis.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(DatabaseRunner.class)
public class CacheTest {
    @Inject
    ProductMapper mapper;

    @Test
    public void should_get() {
        final Product productToCreate = new Product("good product");
        mapper.create(productToCreate);

        final Product product = mapper.find(productToCreate.getId());

        assertThat(product.getDescription(), is("good product"));
    }
}
