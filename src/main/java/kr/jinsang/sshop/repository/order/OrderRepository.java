package kr.jinsang.sshop.repository.order;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.jinsang.sshop.domain.order.Order;
import kr.jinsang.sshop.domain.order.OrderStatus;
import kr.jinsang.sshop.domain.order.QOrder;
import kr.jinsang.sshop.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static kr.jinsang.sshop.domain.order.QOrder.order;
import static kr.jinsang.sshop.domain.user.QUser.user;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    private final JPAQueryFactory query;

    public void save(Order order) {
        em.persist(order);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
       return query.select(order)
               .from(order)
               .join(order.user, user)
               .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getUserName()))
               .fetch();
    }

    private BooleanExpression statusEq(OrderStatus status) {
        if (status == null) {
            return null;
        }
        return order.status.eq(status);
    }

    private BooleanExpression nameLike(String userName) {
        if (!StringUtils.hasText(userName)) {
            return null;
        }
        return user.name.like(userName);
    }

}
