package com.aiken.bibpaper.domain.sort;

public interface BibpaperSorter {
    public static String convertSortMethod(String sorting_param) {
        String sorting;
        if (sorting_param.matches("asc|ASC")) {
            sorting = "ASC";
        } else {
            sorting = "DESC";
        }
        return sorting;
    }
}