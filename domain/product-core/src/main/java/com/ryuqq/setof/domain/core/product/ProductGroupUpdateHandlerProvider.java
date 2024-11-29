package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;
import com.ryuqq.setof.support.utils.AbstractProvider;

import java.util.List;

@Component
public class ProductGroupUpdateHandlerProvider extends AbstractProvider<Class<?>, ProductGroupUpdateHandler<?>> {

    public ProductGroupUpdateHandlerProvider(List<ProductGroupUpdateHandler<?>> services) {
        for (ProductGroupUpdateHandler<?> service : services) {
            map.put(service.getType(), service);
        }
    }

}
