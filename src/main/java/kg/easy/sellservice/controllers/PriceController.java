package kg.easy.sellservice.controllers;

import kg.easy.sellservice.models.dtos.PriceDto;
import kg.easy.sellservice.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/price")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestHeader String token, @RequestBody PriceDto priceDto){
        return priceService.save(token, priceDto);
    }

}
