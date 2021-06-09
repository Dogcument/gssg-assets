package com.gssg.gssgbe.common.util;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class QuerydslUtil {

  public static <T> Slice<T> createSlice(List<T> content, Pageable pageable) {
    boolean hasNext = false;

    if (content.size() > pageable.getPageSize()) {
      content.remove(content.size() - 1);
      hasNext = true;
    }
    return new SliceImpl<>(content, pageable, hasNext);
  }
}
