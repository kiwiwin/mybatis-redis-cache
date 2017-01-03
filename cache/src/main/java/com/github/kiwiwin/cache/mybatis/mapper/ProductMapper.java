package com.github.kiwiwin.cache.mybatis.mapper;

import com.github.kiwiwin.cache.domain.Product;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {

    void create(@Param("product") Product product);

    Product find(@Param("id") long id);
}