package belog.service.impl;

import belog.context.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Beldon
 */
@Service
public class BaseService {
    @Autowired
    protected AppContext appContext;
}
