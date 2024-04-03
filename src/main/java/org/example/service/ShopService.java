package org.example.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ShopService {
    public String getBookvoedCost(int id) {
        String urlStr = String.format("https://www.bookvoed.ru/book?id=%d", id);
        try {
            Document document = Jsoup.connect(urlStr).get();
            return document
                    .getElementsByClass("PG").get(0)
                    .text().split(" ")[0];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLabirintCost(int id) {
        String urlStr = String.format("https://www.labirint.ru/books/%d/", id);
        try {
            Document document = Jsoup.connect(urlStr).get();
            return document
                    .getElementsByClass("buying-price-val-number").get(0)
                    .text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getChitaiGorodCost(String id) {
        String urlStr = String.format("https://www.chitai-gorod.ru/product/%s?productShelf=&shelfIndex=0&productIndex=4", id);
        try {
            Document document = Jsoup.connect(urlStr).get();
            return document
                    .getElementsByClass("product-detail-offer-header__price-currency").get(0)
                    .text().split(" ")[0];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
