package kr.muve.common.service.search;

import java.util.List;

public interface ProductsSearch {

    List<SearchProducts> getSearchProducts(String keyword, Integer page, Integer size);
}
