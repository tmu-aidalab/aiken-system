package com.aiken.bibpaper.mapper;

import java.util.List;

import com.aiken.bibpaper.domain.Bibpaper;
import com.aiken.bibpaper.domain.sort.BibpaperSort;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BibpaperMapper {
    List<Bibpaper> findAll();

    Bibpaper findOne(Long id);

    void save(Bibpaper bibpaper);

    void update(Bibpaper bibpaper);

    void delete(Long id);

    List<Bibpaper> findBibpaper(@Param("bibpaperSort") BibpaperSort bibpaperSort);

    List<Bibpaper> findRecentViewBibpaper(String sorting);

    List<Bibpaper> findRecentRegisterBibpaper(String sorting);

    List<Bibpaper> findRecentUpdateBibpaper(String sorting);

    List<Bibpaper> findViewLogCount(String sorting);

    void registerAuthor(String author);

    void registerHashtag(String hashTag);

}