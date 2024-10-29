package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductCondition;
import com.ryuqq.setof.core.ProductStatus;
import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.category.Category;
import com.ryuqq.setof.domain.core.color.Color;
import com.ryuqq.setof.domain.core.product.query.Price;

import java.util.List;

public class ProductGroup {
    private long productGroupId;
    private long sellerId;
    private Color color;
    private List<Category> categories;
    private Brand brand;
    private String productGroupName;
    private String styleCode;
    private ProductCondition productCondition;
    private ManagementType managementType;
    private OptionType optionType;
    private Price price;
    private boolean soldOutYn;
    private boolean displayYn;
    private ProductStatus productStatus;
}
