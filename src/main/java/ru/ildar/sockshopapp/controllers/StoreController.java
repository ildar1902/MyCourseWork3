package ru.ildar.sockshopapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ildar.sockshopapp.model.SockProduct;
import ru.ildar.sockshopapp.service.StoreService;

@Tag(name = "Операции с товаром", description = "API для работы с товаром")
@RestController
@RequestMapping("/socks")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @Operation(summary = "Регистрация прихода", description = "Регистрирует приход товара на склад")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "удалось добавить приход"),
            @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны")})
    @PostMapping
    public ResponseEntity<?> add(@RequestBody SockProduct sockProduct) {
        storeService.income(sockProduct);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Регистрация отпуска товара со склада", description = "Регистрирует отпуск товара со склада")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "удалось произвести отпуск носков со склада"),
            @ApiResponse(responseCode = "400",
                    description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат"),
            @ApiResponse(responseCode = "500",
                    description = "произошла ошибка, не зависящая от вызывающей стороны")})
    @PutMapping
    public ResponseEntity<?> release(@RequestBody SockProduct sockProduct) {
        storeService.release(sockProduct);
        return ResponseEntity.ok().build();
    }
}