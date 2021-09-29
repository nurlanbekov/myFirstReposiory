package kg.easy.sellservice.controllers;

import kg.easy.sellservice.models.dtos.DiscountDto;
import kg.easy.sellservice.services.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/discount")
public class DiscountController {

    private DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestHeader String token, @RequestBody DiscountDto discountDto){
        return discountService.save(token, discountDto);
    }
}
