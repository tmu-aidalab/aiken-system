package com.aiken.bibpaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

import com.aiken.bibpaper.service.BibpaperService;
import com.aiken.bibpaper.domain.Bibpaper;
import com.aiken.bibpaper.domain.sort.BibpaperSort;
import com.aiken.bibpaper.domain.sort.BibpaperSorter;

@Controller
@RequestMapping("/")
public class BibpaperController {

    @Autowired
    private BibpaperService bibpaperService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("bibpapers", bibpaperService.findAll());
        return "index";
    }

    @GetMapping("new")
    public String newBibpaper(@ModelAttribute("bibpaper") Bibpaper bibpaper, Model model) {
        return "new";
    }

    @GetMapping("{id}")
    public String findOne(@PathVariable Long id, Model model) {
        model.addAttribute("bibpapers", bibpaperService.findOne(id));
        return "index";
    }

    @GetMapping("search/bib/{title}/{sorting}")
    public String searchBibpaper(@PathVariable("title") String title, @PathVariable("sorting") String sorting,
            Model model) {
        String key = "title";
        BibpaperSort params = new BibpaperSort();
        sorting = BibpaperSorter.convertSortMethod(sorting);
        params.setTitle(title);
        params.setKey(key);
        params.setSorting(sorting);
        model.addAttribute("bibpapers", bibpaperService.findBibpaper(params));
        params = null;
        return "index";
    }

    @GetMapping("search/view/{sorting}")
    public String searchRecentView(@PathVariable String sorting, Model model) {
        sorting = BibpaperSorter.convertSortMethod(sorting);
        model.addAttribute("bibpapers", bibpaperService.findRecentViewBibpaper(sorting));
        return "index";
    }

    @GetMapping("search/regist/{sorting}")
    public String searchRecentRegister(@PathVariable String sorting, Model model) {
        sorting = BibpaperSorter.convertSortMethod(sorting);
        model.addAttribute("bibpapers", bibpaperService.findRecentRegisterBibpaper(sorting));
        return "index";
    }

    @GetMapping("search/update/{sorting}")
    public String searchRecentUpdate(@PathVariable String sorting, Model model) {
        sorting = BibpaperSorter.convertSortMethod(sorting);
        model.addAttribute("bibpapers", bibpaperService.findRecentUpdateBibpaper(sorting));
        return "index";
    }

    @GetMapping("search/freq/{sorting}")
    public String searchFrequentView(@PathVariable String sorting, Model model) {
        sorting = BibpaperSorter.convertSortMethod(sorting);
        model.addAttribute("bibpapers", bibpaperService.findViewLogCount(sorting));
        return "index";
    }

    @PostMapping
    public String create(@ModelAttribute("bibpaper") @Validated Bibpaper bibpaper, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new";
        } else {
            // Bibpaper x = new Bibpaper();
            String[] authorsList = bibpaper.getAuthors().split(",", 0);
            for (String author : authorsList) {
                bibpaperService.authorRegist(author);
            }

            String[] hashTagList = bibpaper.getHash_tag().split(",", 0);
            for (String hashTag : hashTagList) {
                bibpaperService.hashTagRegist(hashTag);
            }

            bibpaperService.save(bibpaper);
            // x = null;
            return "redirect:/";
        }
    }

    /*
     * PostMapping PutMapping DeleteMapping の参考となるように残しておく
     * 
     * @PostMapping public String create(@ModelAttribute("item") @Validated Item
     * item, BindingResult result, Model model) { if (result.hasErrors()) { return
     * "new"; } else { itemService.save(item); return "redirect:/items"; } }
     * 
     * @PutMapping("{id}") public String update(@PathVariable Long
     * id, @ModelAttribute("item") @Validated Item item, BindingResult result, Model
     * model) { if (result.hasErrors()) { model.addAttribute("item", item); return
     * "edit"; } else { item.setId(id); itemService.update(item); return
     * "redirect:/items"; } }
     * 
     * @DeleteMapping("{id}") public String delete(@PathVariable Long id) {
     * itemService.delete(id); return "redirect:/items"; }
     */
}