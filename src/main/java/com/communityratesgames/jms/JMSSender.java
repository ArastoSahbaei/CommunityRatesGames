package com.communityratesgames.jms;

import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import javax.naming.Context;

@LocalBean
@Stateless
public class JMSSender {

    private final static Logger logger = Logger.getLogger(com.communityratesgames.jms.JMSSender.class);


    @Resource(mappedName = "java:/queue/crg")
    private Queue crgQueue;

    @Inject
    JMSContext context;

    public void registerLog(final String test) {
        logger.info(test);
        context.createProducer().send(crgQueue, test);
    }
}
