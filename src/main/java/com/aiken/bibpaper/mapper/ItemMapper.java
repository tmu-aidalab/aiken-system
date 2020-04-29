package com.aiken.bibpaper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aiken.bibpaper.domain.Item;

@Mapper
public interface ItemMapper {
    List<Item> findAll();

    Item findOne(Long id);

    void save(Item item);

    void update(Item item);

    void delete(Long id);
}