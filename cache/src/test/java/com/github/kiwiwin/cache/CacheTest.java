package com.github.kiwiwin.cache;

import com.github.kiwiwin.cache.domain.Product;
import com.github.kiwiwin.cache.mybatis.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.stream.IntStream;

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

    @Test
    public void should_use_cache() {
        final Product productToCreate = new Product("good product");
        mapper.create(productToCreate);

        IntStream.range(0, 20).forEach($ -> {
            final Product product = mapper.find(productToCreate.getId());

            assertThat(product.getDescription(), is("good product"));
        });
    }

    @Test
    public void should_not_user_cache() {
        final Product productToCreate = new Product("good product");
        mapper.create(productToCreate);

        IntStream.range(0, 20).forEach($ -> {
            final Product product = mapper.findWithoutCache(productToCreate.getId());

            assertThat(product.getDescription(), is("good product"));
        });
    }
}
