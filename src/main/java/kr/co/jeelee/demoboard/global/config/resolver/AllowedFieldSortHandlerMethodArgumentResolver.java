package kr.co.jeelee.demoboard.global.config.resolver;

import kr.co.jeelee.demoboard.global.annotation.AllowedSortFields;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AllowedFieldSortHandlerMethodArgumentResolver extends SortHandlerMethodArgumentResolver {

    @Override
    @NonNull
    public Sort resolveArgument(
            @NonNull MethodParameter parameter,
            @Nullable ModelAndViewContainer mavContainer,
            @NonNull NativeWebRequest webRequest,
            @Nullable WebDataBinderFactory binderFactory
    ) {
        Sort sort = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        Set<String> allowedFields = Optional.ofNullable(parameter.getMethod())
                        .map(Method::getAnnotations)
                                .map(annotations -> Arrays.stream(annotations)
                                        .filter(annotation -> annotation instanceof AllowedSortFields)
                                        .flatMap(annotation -> Stream.of(((AllowedSortFields) annotation).value()))
                                        .collect(Collectors.toSet()))
                                        .orElseGet(HashSet::new);

        return Sort.by(sort.get()
                .filter(order -> allowedFields
                        .contains(order.getProperty()))
                .collect(Collectors.toList()));
    }
}
