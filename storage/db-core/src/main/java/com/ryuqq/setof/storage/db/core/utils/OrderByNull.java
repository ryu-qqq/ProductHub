package com.ryuqq.setof.storage.db.core.utils;

import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;

import static com.querydsl.core.types.OrderSpecifier.NullHandling.Default;

public class OrderByNull extends OrderSpecifier {

    public static final OrderByNull DEFAULT = new OrderByNull();

    protected OrderByNull() {
        super(Order.ASC, NullExpression.DEFAULT, Default);
    }
}
