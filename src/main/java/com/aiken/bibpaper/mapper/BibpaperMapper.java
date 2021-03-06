package com.aiken.bibpaper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aiken.bibpaper.domain.Bibpaper;
import com.aiken.bibpaper.domain.sort.BibpaperSort;

@Mapper
public interface BibpaperMapper {
    List<Bibpaper> findAll();

    List<Bibpaper> findOne(Long id);

    void save(Bibpaper bibpaper);

    void update(Bibpaper bibpaper);

    void delete(Long id);

    List<Bibpaper> findBibpaper(@Param("bibpaperSort") BibpaperSort bibpaperSort);

    List<Bibpaper> findRecentViewBibpaper(@Param("sorting") String sorting, @Param("search_key") String search_key,
            @Param("keyword") String keyword);

    List<Bibpaper> findRecentRegisterBibpaper(@Param("sorting") String sorting, @Param("search_key") String search_key,
            @Param("keyword") String keyword);

    List<Bibpaper> findRecentUpdateBibpaper(@Param("sorting") String sorting, @Param("search_key") String search_key,
            @Param("keyword") String keyword);

    List<Bibpaper> findViewLogCount(@Param("sorting") String sorting, @Param("search_key") String search_key,
            @Param("keyword") String keyword);

    void registerAuthor(String author);

    void registerHashtag(String hashTag);

}