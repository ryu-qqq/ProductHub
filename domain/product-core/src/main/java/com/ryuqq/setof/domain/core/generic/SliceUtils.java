package com.ryuqq.setof.domain.core.generic;

import com.ryuqq.setof.enums.core.Sort;

import java.util.Collections;
import java.util.List;

public class SliceUtils {

    public static <T> Slice<T> toSlice(List<T> contents, int pageSize) {

        boolean hasNext = isContentSizeGreaterThanPageSize(contents, pageSize);
        return new Slice.Builder<T>()
                .content(hasNext ? subListLastContent(contents, pageSize) : contents)
                .size(pageSize)
                .last(!hasNext)
                .first(contents.isEmpty())
                .numberOfElements(contents.size())
                .empty(contents.isEmpty())
                .sort(Sort.DESC)
                .build();
    }

    public static <T> Slice<T> toSlice(List<T> contents, int pageSize, long totalElements) {

        boolean hasNext = isContentSizeGreaterThanPageSize(contents, pageSize);
        return new Slice.Builder<T>()
                .content(hasNext ? subListLastContent(contents, pageSize) : contents)
                .size(pageSize)
                .last(!hasNext)
                .first(contents.isEmpty())
                .numberOfElements(contents.size())
                .empty(contents.isEmpty())
                .totalElements(totalElements)
                .sort(Sort.DESC)
                .build();
    }

    public static <T> Slice<T> emptySlice(int pageSize) {
        return new Slice.Builder<T>()
                .content(Collections.emptyList())
                .size(pageSize)
                .last(true)
                .first(true)
                .numberOfElements(0)
                .empty(true)
                .build();
    }

    private static <T> boolean isContentSizeGreaterThanPageSize(List<T> content, int pageSize) {
        return content.size() == pageSize + 1;
    }

    private static <T> List<T> subListLastContent(List<T> content, int pageSize) {
        return content.subList(0, pageSize);
    }

}
