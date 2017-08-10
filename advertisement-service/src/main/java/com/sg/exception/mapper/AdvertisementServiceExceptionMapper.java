package com.sg.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.sg.exception.AdvertisementServiceException;
import com.sg.model.ErrorResponse;

@Provider
public class AdvertisementServiceExceptionMapper implements ExceptionMapper<AdvertisementServiceException>{
	private final static Logger logger = Logger.getLogger(AdvertisementServiceExceptionMapper.class);
	
	@Override
	public Response toResponse(AdvertisementServiceException exception) {
		logger.error("Error occured:"+ exception.getErrorCode() +":"+exception.getErrorMessage());
		ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(exception.getErrorCode());
        errorResponse.setErrorMessage(exception.getErrorMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
	}

}
