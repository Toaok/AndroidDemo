package com.toaok.study.module.map.marker.convert;

import java.util.List;

public interface MarkerConvert<F, T, E > {
    List<T> converts(List<F> values, E config);

    T convert(F values, E config);
}