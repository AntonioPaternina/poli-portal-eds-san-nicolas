package co.com.estacionsannicolas.service;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected Mapper mapper;
}
