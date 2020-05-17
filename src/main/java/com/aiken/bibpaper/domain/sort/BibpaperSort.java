package com.aiken.bibpaper.domain.sort;

import com.aiken.bibpaper.domain.Bibpaper;

public class BibpaperSort extends Bibpaper {

    private String key;

    private String sorting;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

}