package com.aiken.bibpaper.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.List;

public class Bibpaper {
    private Long id;

    @NotNull(message = "カテゴリを選択してください")
    @Min(value = 1, message = "1以上の整数を入力してください")
    @Max(value = 10000, message = "10000以下の整数を入力してください")
    private Long category;

    @NotBlank(message = "タイトルを入力してください")
    @Size(max = 600, message = "タイトルは600文字を超えないでください")
    private String title;

    @NotNull(message = "著者名を入力してください")
    @Size(max = 200, message = "著者名は200文字を超えないでください")
    private String authors;

    @Size(max = 900, message = "アブストラクトは900文字を超えないでください")
    private String abst;

    @NotBlank(message = "概要を入力してください")
    @Size(max = 600, message = "概要は600文字を超えないでください")
    private String descript;

    @NotNull(message = "ハッシュタグを入力してください")
    @Size(max = 200, message = "ハッシュタグは200文字を超えないでください")
    private String hash_tag;

    @NotBlank(message = "pdfへのリンクを入力してください")
    @Size(max = 300, message = "ハイパーリンクは300文字を超えないでください")
    private String hyperlink;

    @Size(max = 2000, message = "bibtexは2000文字を超えないでください")
    private String bibtex_source;

    @Size(max = 1000, message = "bibitemは1000文字を超えないでください")
    private String bibitem_source;

    @Size(max = 600, message = "備考は600文字を超えないでください")
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

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getAbst() {
        return abst;
    }

    public void setAbst(String abst) {
        this.abst = abst;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(String hash_tag) {
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