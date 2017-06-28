package com.redhat.systems.neptune.util;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {
	
//	@SuppressWarnings("cdi-ambiguous-dependency")
//	@Inject  
//	private transient Logger logger;
	
	private static final Logger logger = Logger.getLogger(ObjectMapperResolver.class);
	
	private final ObjectMapper mapper;

    public ObjectMapperResolver() 
    {
    	logger.debug("Loading Object Mapper....");
        mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate4Module());
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) 
    {
        return mapper;
    }
}
