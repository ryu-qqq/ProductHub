package com.ryuqq.setof.producthub.data;

import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;
import com.ryuqq.setof.domain.core.category.CategoryRecord;
import com.ryuqq.setof.producthub.core.api.controller.v1.category.response.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryModuleHelper {

    public static List<CategoryRecord> toCategory(){

        List<CategoryRecord> categories = new ArrayList<>();

        categories.add(new CategoryRecord( 1L, "women",1, 0L, true,  TargetGroup.FEMALE, CategoryType.NONE,"1"));
        categories.add(new CategoryRecord( 2L, "의류", 2,1, true,  TargetGroup.FEMALE,CategoryType.CLOTHING,"1,2"));
        categories.add(new CategoryRecord( 3L, "탑", 3,2, true,   TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,3"));
        categories.add(new CategoryRecord( 4L, "스웨트셔츠", 4,3, true,  TargetGroup.FEMALE,CategoryType.CLOTHING,"1,2,3,4"));

        return categories;
    }

    public static List<CategoryResponse> toCategoryResponses(){

        List<CategoryResponse> categories = new ArrayList<>();

        categories.add(new CategoryResponse( 1L, "women",1, 0L, true,  TargetGroup.FEMALE, CategoryType.NONE,"1"));
        categories.add(new CategoryResponse( 2L, "의류", 2,1, true,  TargetGroup.FEMALE,CategoryType.CLOTHING,"1,2"));
        categories.add(new CategoryResponse( 3L, "탑", 3,2, true,   TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,3"));
        categories.add(new CategoryResponse( 4L, "스웨트셔츠", 4,3, true,  TargetGroup.FEMALE,CategoryType.CLOTHING,"1,2,3,4"));

        return categories;
    }

}
