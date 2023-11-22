package inhatc.spring.shop.controller;

import inhatc.spring.shop.dto.ItemFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @GetMapping("/admin/item/new")
    public String itemForm(Model model, ItemFormDto itemFormDto) {
        model.addAttribute("itemFormDto", itemFormDto);

        return "item/itemForm";
    }
}
