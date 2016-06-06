package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.util.DozerHelper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected Mapper mapper;

    protected <T, U> ArrayList<U> map(final List<T> source, final Class<U> destType) {
        return DozerHelper.map(mapper, source, destType);
    }
}
