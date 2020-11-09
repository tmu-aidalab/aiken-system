package com.aiken.bibpaper.service;

import java.util.List;

import com.aiken.bibpaper.domain.Bibpaper;
import com.aiken.bibpaper.domain.sort.BibpaperSort;
import com.aiken.bibpaper.mapper.BibpaperMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BibpaperService {

    @Autowired
    private BibpaperMapper bibpaperMapper;

    @Transactional
    public List<Bibpaper> findAll() {
        return bibpaperMapper.findAll();
    }

    @Transactional
    public Bibpaper findOne(Long id) {
        return bibpaperMapper.findOne(id);
    }

    @Transactional
    public void save(Bibpaper bibpaper) {
        bibpaperMapper.save(bibpaper);
    }

    @Transactional
    public void update(Bibpaper bibpaper) {
        bibpaperMapper.update(bibpaper);
    }

    @Transactional
    public void delete(Long id) {
        bibpaperMapper.delete(id);
    }

    @Transactional
    public List<Bibpaper> findBibpaper(BibpaperSort params) {
        return bibpaperMapper.findBibpaper(params);
    }

    @Transactional
    public List<Bibpaper> findRecentViewBibpaper(String sorting, String search_key, String keyword) {
        return bibpaperMapper.findRecentViewBibpaper(sorting, search_key, keyword);
    }

    @Transactional
    public List<Bibpaper> findRecentRegisterBibpaper(String sorting, String search_key, String keyword) {
        return bibpaperMapper.findRecentRegisterBibpaper(sorting, search_key, keyword);
    }

    @Transactional
    public List<Bibpaper> findRecentUpdateBibpaper(String sorting, String search_key, String keyword) {
        return bibpaperMapper.findRecentUpdateBibpaper(sorting, search_key, keyword);
    }

    @Transactional
    public List<Bibpaper> findViewLogCount(String sorting, String search_key, String keyword) {
        return bibpaperMapper.findViewLogCount(sorting, search_key, keyword);
    }

    @Transactional
    public void registerAuthor(String author) {
        bibpaperMapper.registerAuthor(author);
    }

    @Transactional
    public void registerHashtag(String hashTag) {
        bibpaperMapper.registerHashtag(hashTag);
    }

    @Transactional
    public String findCategory(Long id) {
        return bibpaperMapper.findCategory(id);
    }
}