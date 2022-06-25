package pl.edu.wszib.order.infrastructure.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.edu.wszib.order.api.PageApi;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PageApiUtils {

    public static PageApi fromPageable(final Pageable pageable) {
        final List<PageApi.SortingOrder> sortingOrders = pageable.getSort().get()
                .map(sortingOrder -> new PageApi.SortingOrder(PageApi.Direction.valueOf(sortingOrder.getDirection().name()), sortingOrder.getProperty()))
                .collect(Collectors.toList());
        return new PageApi(pageable.getPageNumber(), pageable.getPageSize(), new PageApi.Sort(sortingOrders));
    }

    public static Pageable fromPageApi(final PageApi pageApi) {
        final Sort sort = Sort.by(pageApi.getSort().getSortingOrders().stream()
                .map(sortingOrder -> new Sort.Order(Sort.Direction.fromString(sortingOrder.getDirection().name()), sortingOrder.getProperty()))
                .collect(Collectors.toList()));
        return PageRequest.of(pageApi.getPage(), pageApi.getSize(), sort);
    }
}
