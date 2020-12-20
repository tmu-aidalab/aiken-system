package com.aiken.bibpaper.controller;

import com.aiken.bibpaper.domain.Bibpaper;
import com.aiken.bibpaper.domain.sort.BibpaperSorter;
import com.aiken.bibpaper.service.BibpaperService;

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
import org.springframework.web.bind.annotation.RequestParam;

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
        boolean flag = false;
        model.addAttribute("duplicateTitle", flag);
        return "new";
    }

    @GetMapping("{id}")
    public String findOne(@PathVariable Long id, Model model) {
        model.addAttribute("bibpapers", bibpaperService.findOne(id));
        model.addAttribute("bibpaperCategory", bibpaperService.findCategory(bibpaperService.findOne(id).getId()));
        return "show";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute("bibpaper") Bibpaper bibpaper, Model model) {
        model.addAttribute("bibpaper", bibpaperService.findOne(id));
        return "edit";
    }

    @GetMapping("/search/view")
    public String searchRecentView(Model model,
            @RequestParam(name = "search_key", defaultValue = "title") String search_key,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "sorting", defaultValue = "DESC") String sorting) {
        sorting = BibpaperSorter.convertSortMethod(sorting);
        model.addAttribute("bibpapers", bibpaperService.findRecentViewBibpaper(sorting, search_key, keyword));
        return "index";
    }

    @GetMapping("search/regist")
    public String searchRecentRegister(Model model,
            @RequestParam(name = "search_key", defaultValue = "title") String search_key,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "sorting", defaultValue = "DESC") String sorting) {
        sorting = BibpaperSorter.convertSortMethod(sorting);
        model.addAttribute("bibpapers", bibpaperService.findRecentRegisterBibpaper(sorting, search_key, keyword));
        return "index";
    }

    @GetMapping("search/update")
    public String searchRecentUpdate(Model model,
            @RequestParam(name = "search_key", defaultValue = "title") String search_key,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "sorting", defaultValue = "DESC") String sorting) {
        sorting = BibpaperSorter.convertSortMethod(sorting);
        model.addAttribute("bibpapers", bibpaperService.findRecentUpdateBibpaper(sorting, search_key, keyword));
        return "index";
    }

    @GetMapping("search/freq")
    public String searchFrequentView(Model model,
            @RequestParam(name = "search_key", defaultValue = "title") String search_key,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "sorting", defaultValue = "DESC") String sorting) {
        sorting = BibpaperSorter.convertSortMethod(sorting);
        model.addAttribute("bibpapers", bibpaperService.findViewLogCount(sorting, search_key, keyword));
        return "index";
    }

    @PostMapping
    public String registerBibpaper(@ModelAttribute("bibpaper") @Validated Bibpaper bibpaper, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "new";
        } else if (bibpaperService.checkTitleDuplication(bibpaper.getTitle()) >= 1) {
            boolean flag = true;
            model.addAttribute("duplicateTitle", flag);
            return "new";
        } else {
            String[] authorsList = bibpaper.getAuthors().split(",", 0);
            for (String author : authorsList) {
                bibpaperService.registerAuthor(author);
            }

            String[] hashTagList = bibpaper.getHash_tag().split(",", 0);
            for (String hashTag : hashTagList) {
                bibpaperService.registerHashtag(hashTag);
            }

            bibpaperService.save(bibpaper);
            return "redirect:/";
        }
    }

    @PutMapping("{id}")
    public String update(@PathVariable Long id, @ModelAttribute("bibpaper") @Validated Bibpaper bibpaper,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("bibpaper", bibpaper);
            return "edit";
        } else {
            bibpaper.setId(id);
            bibpaperService.update(bibpaper);
            return "redirect:/{id}";
        }
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        bibpaperService.delete(id);
        return "redirect:/";
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