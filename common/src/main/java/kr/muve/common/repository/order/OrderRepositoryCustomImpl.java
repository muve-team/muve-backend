package kr.muve.common.repository.order;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static kr.muve.common.domain.order.QOrderJpaEntity.orderJpaEntity;
import static kr.muve.common.domain.user.QUserJpaEntity.userJpaEntity;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<OrderJpaEntity> findDynamicOrders(OrderSearch orderSearch) {
        return query.select(orderJpaEntity)
                .from(orderJpaEntity)
                .join(orderJpaEntity.userJpaEntity, userJpaEntity)
                .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getUserName()))
                .fetch();
    }

    private BooleanExpression statusEq(OrderStatus status) {
        if (status == null) {
            return null;
        }
        return orderJpaEntity.status.eq(status);
    }

    private BooleanExpression nameLike(String userName) {
        if (!StringUtils.hasText(userName)) {
            return null;
        }
        return userJpaEntity.name.like(userName);
    }
}
