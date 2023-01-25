package ru.ildar.sockshopapp.service;

import org.springframework.stereotype.Service;
import ru.ildar.sockshopapp.model.Color;
import ru.ildar.sockshopapp.model.Size;
import ru.ildar.sockshopapp.model.Sock;
import ru.ildar.sockshopapp.model.SockProduct;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class StoreService {
    private final Map<Sock, Integer> socks = new LinkedHashMap<>();

    public void income(SockProduct sockProduct) {
        if (isNotValid(sockProduct)) {
            throw new IllegalArgumentException();
        }
        Sock sock = sockProduct.getSock();
        if (socks.containsKey(sockProduct.getSock())) {
            socks.replace(sockProduct.getSock(), socks.get(sock) + sockProduct.getQuantity());
        } else {
            socks.put(sockProduct.getSock(), sockProduct.getQuantity());
        }
    }

    public void release(SockProduct sockProduct) {
        Sock sock = sockProduct.getSock();
        int difference = socks.get(sock) - sockProduct.getQuantity();
        if (!socks.containsKey(sock) || isNotValid(sockProduct)) {
            throw new IllegalArgumentException();
        }
        if (difference < 0) {
            throw new RuntimeException("Количество списываемого товара не может превышать количество товара на складе");
        }
        socks.replace(sock, difference);
    }

    public int getCount(String color, int size, int cottonMin, int cottonMax) {
        Color c = Color.parse(color);
        Size s = Size.parse(size);
        if (Objects.isNull(c) || Objects.isNull(s) || cottonMin >= cottonMax || cottonMin < 0 || cottonMax > 100) {
            throw new RuntimeException("Некорректные параметры");
        }
        for (Map.Entry<Sock, Integer> entry : socks.entrySet()) {
            Sock sock = entry.getKey();
            if (sock.getColor() == c
                    && sock.getSize() == s
                    && sock.getCottonPart() >= cottonMin
                    && sock.getCottonPart() <= cottonMax) {
                return entry.getValue();
            }
        }
        return 0;
    }

    private boolean isNotValid(SockProduct sockProduct) {
        Sock sock = sockProduct.getSock();
        return sock.getCottonPart() < 0 || sock.getCottonPart() > 100 || sockProduct.getQuantity() <= 0;
    }
}
