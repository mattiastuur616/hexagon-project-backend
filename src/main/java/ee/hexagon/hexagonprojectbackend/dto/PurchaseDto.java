package ee.hexagon.hexagonprojectbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDto {

    private Long purchaseId;
    private Long userId;
    private Long chapter;

    public PurchaseDto(Long purchaseId, Long userId, Long chapter) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.chapter = chapter;
    }
}
