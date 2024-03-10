package ee.hexagon.hexagonprojectbackend.controller;

import ee.hexagon.hexagonprojectbackend.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@RestController
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("/purchase")
    public String addPurchase(@RequestParam String email, @RequestParam Long chapter) {
        return purchaseService.addPurchase(email, chapter);
    }

    @GetMapping("/isPurchased")
    public boolean isPurchased(@RequestParam String email, @RequestParam Long chapter) {
        return purchaseService.isPurchased(email, chapter);
    }
}
