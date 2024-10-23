package kr.muve.common.service.my;

import java.util.List;

public interface FindMyOrders {

    List<MyOrderRes> findMyAllOrders(Long id, Integer page, Integer size);
}
