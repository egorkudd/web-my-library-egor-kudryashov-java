package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.ShopService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shopController")
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/cost")
    public void getBookvoedCost(
            @RequestParam("bookvoed_id") int bookvoedId,
            @RequestParam("labirint_id") int labirintId,
            @RequestParam("chitai_gorod_id") String chitaiGorodId
    ) {
        System.out.println(shopService.getBookvoedCost(bookvoedId));
        System.out.println(shopService.getLabirintCost(labirintId));
        System.out.println(shopService.getChitaiGorodCost(chitaiGorodId));
    }
} // TODO : add foreign keys
