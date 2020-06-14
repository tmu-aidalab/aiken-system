package com.aiken.bibpaper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiken.bibpaper.domain.Bibpaper;
import com.aiken.bibpaper.mapper.BibpaperMapper;
import com.aiken.bibpaper.domain.sort.BibpaperSort;

@Service
public class BibpaperService {

    @Autowired
    private BibpaperMapper bibpaperMapper;

    @Transactional
    public List<Bibpaper> findAll() {
        return bibpaperMapper.findAll();
    }

    @Transactional
    public List<Bibpaper> findOne(Long id) {
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
    public List<Bibpaper> findRecentViewBibpaper(String sorting) {
        return bibpaperMapper.findRecentViewBibpaper(sorting);
    }

    @Transactional
    public List<Bibpaper> findRecentRegisterBibpaper(String sorting) {
        return bibpaperMapper.findRecentRegisterBibpaper(sorting);
    }

    @Transactional
    public List<Bibpaper> findRecentUpdateBibpaper(String sorting) {
        return bibpaperMapper.findRecentUpdateBibpaper(sorting);
    }

    @Transactional
    public List<Bibpaper> findViewLogCount(String sorting) {
        return bibpaperMapper.findViewLogCount(sorting);
    }

    @Transactional
    public List<Bibpaper> findSearchRecentRegisterBibpaper(BibpaperSort params) {
        return bibpaperMapper.findSearchRecentRegisterBibpaper(params);
    }

}