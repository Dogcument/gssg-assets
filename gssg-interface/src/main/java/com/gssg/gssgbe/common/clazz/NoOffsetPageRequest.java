package com.gssg.gssgbe.common.clazz;

import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@Getter
public class NoOffsetPageRequest {

    private final long current;
    private final int size;
    private final Sort sort;

    private NoOffsetPageRequest(final Long current, final int size, final Sort sort) {
        this.current = Optional.ofNullable(current).orElse(Long.MAX_VALUE);
        this.size = size;
        this.sort = sort;
    }

    public static NoOffsetPageRequest of(final Long current, final int size) {
        return of(current, size, Sort.unsorted());
    }

    public static NoOffsetPageRequest of(final Long current, final int size, final Sort sort) {
        return new NoOffsetPageRequest(current, size, sort);
    }

    public boolean isSorted() {
        return sort.isSorted();
    }
}
