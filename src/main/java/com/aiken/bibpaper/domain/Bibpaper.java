package com.aiken.bibpaper.domain;

import javax.validation.constraints.NotBlank;

import java.util.List;

public class Bibpaper {
    private Long id;

    @NotBlank(message = "カテゴリを選択してください")
    private Long category;

    @NotBlank(message = "タイトルを入力してください")
    private String title;

    @NotBlank(message = "著者名を入力してください")
    private List<String> authors;

    private String abst;

    @NotBlank(message = "概要を入力してください")
    private String descript;

    @NotBlank(message = "ハッシュタグを入力してください")
    private List<String> hash_tag;

    @NotBlank(message = "pdfへのリンクを入力してください")
    private String hyperlink;

    private String bibtex_source;

    private String bibitem_source;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getAbstract() {
        return abst;
    }

    public void setAbstract(String abst) {
        this.abst = abst;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public List<String> getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(List<String> hash_tag) {
        this.hash_tag = hash_tag;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public String getBibtex_source() {
        return bibtex_source;
    }

    public void setBibtex_source(String bibtex_source) {
        this.bibtex_source = bibtex_source;
    }

    public String getBibitem_source() {
        return bibitem_source;
    }

    public void setBibitem_source(String bibitem_source) {
        this.bibitem_source = bibitem_source;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}