package ru.ildar.sockshopapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SockProduct {
    private Sock sock;
    private int quantity;
}
