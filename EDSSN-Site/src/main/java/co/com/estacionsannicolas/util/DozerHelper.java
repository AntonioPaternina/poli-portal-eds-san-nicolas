package co.com.estacionsannicolas.util;

import co.com.estacionsannicolas.beans.PageBean;
import org.dozer.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class DozerHelper {

    public static <T, U> ArrayList<U> map(final Mapper mapper, final List<T> source, final Class<U> destType) {

        final ArrayList<U> dest = new ArrayList<>();

        for (T element : source) {
            if (element == null) {
                continue;
            }
            dest.add(mapper.map(element, destType));
        }

        List<U> s1 = new ArrayList<>();
        s1.add(null);
        dest.removeAll(s1);

        return dest;
    }

    public static <T, U> PageBean<U> map(final Mapper mapper, final Page<T> source, final Class<U> destType) {
        List<U> mappedList = map(mapper, source.getContent(), destType);

        PageBean<U> page = new PageBean<>();
        page.setTotalItems(source.getTotalElements());
        page.setContent(mappedList);

        return page;
    }


}
