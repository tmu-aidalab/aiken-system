package com.aiken.bibpaper.domain.sort;

public interface BibpaperSorter {
    public static String convertSortMethod(String sorting_param) {
        String sorting;
        if (sorting_param.equals("desc")) {
            sorting = "DESC";
        } else {
            sorting = "ASC";
        }
        return sorting;
    }
}