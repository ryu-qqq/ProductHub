package data;

import com.ryuqq.setof.core.*;
import com.ryuqq.setof.domain.core.product.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductModuleHelper {

    public static ProductGroupCommandContext toProductGroupCommandContext() {
        ProductGroupCommand productGroup = createProductGroupCommand();
        ProductNoticeCommand notice = createProductNoticeCommand();
        ProductDeliveryCommand delivery = createProductDeliveryCommand();
        List<ProductGroupImageCommand> images = createProductGroupImageCommands();
        ProductDetailDescriptionCommand detailDescription = createProductDetailDescriptionCommand();
        List<ProductCommand> productCommands = createProductCommands();

        return new ProductGroupCommandContext(productGroup, notice, delivery, images, detailDescription, productCommands);
    }

    public static ProductGroupCommand createProductGroupCommand() {
        return new ProductGroupCommand(
                1L, 1L, 1L, "Test Product Group", "STYLE123", ProductCondition.NEW, ManagementType.MENUAL,
                OptionType.OPTION_TWO, BigDecimal.valueOf(100000), BigDecimal.valueOf(90000), false, true, ProductStatus.WAITING
        );
    }

    public static ProductNoticeCommand createProductNoticeCommand() {
        return new ProductNoticeCommand(
                "Cotton", "Blue", "M", "BrandX", Origin.KR, "Dry Clean", "2023-08", "Standard", "010-1234-5678"
        );
    }

    public static ProductDeliveryCommand createProductDeliveryCommand() {
        return new ProductDeliveryCommand(
                "Seoul", BigDecimal.valueOf(3000), 3, ReturnMethod.RETURN_CONSUMER, ShipmentCompanyCode.SHIP01, BigDecimal.valueOf(5000), "Seoul"
        );
    }

    public static List<ProductGroupImageCommand> createProductGroupImageCommands() {
        return List.of(
                new ProductGroupImageCommand(ProductImageType.MAIN, "https://example.com/main.jpg"),
                new ProductGroupImageCommand(ProductImageType.DETAIL, "https://example.com/detail1.jpg"),
                new ProductGroupImageCommand(ProductImageType.DETAIL, "https://example.com/detail2.jpg")
        );
    }

    public static ProductDetailDescriptionCommand createProductDetailDescriptionCommand() {
        return new ProductDetailDescriptionCommand("This is a test product detail description.");
    }

    public static List<ProductCommand> createProductCommands() {
        return List.of(
                new ProductCommand(false, true, 10, BigDecimal.valueOf(5000), createDoubleOptionCommands("Blue", " M")),
                new ProductCommand(false, true, 20, BigDecimal.valueOf(7000), createDoubleOptionCommands("Blue", "L"))
        );
    }

    public static List<OptionCommand> createDoubleOptionCommands(String color, String size) {
        return List.of(
                new OptionCommand(OptionName.COLOR, color),
                new OptionCommand(OptionName.SIZE, size)
        );
    }

}
