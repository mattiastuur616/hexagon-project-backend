package ee.hexagon.hexagonprojectbackend.mapper;

import ee.hexagon.hexagonprojectbackend.dto.PurchaseDto;
import ee.hexagon.hexagonprojectbackend.model.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    @Mapping(target = "purchaseId", source = "purchase.purchaseId")
    @Mapping(target = "userId", source = "purchase.clientUser.userId")
    @Mapping(target = "chapter", source = "purchase.chapter")
    PurchaseDto purchaseToPurchaseDto(Purchase purchase);
}
