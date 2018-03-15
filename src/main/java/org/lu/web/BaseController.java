package org.lu.web;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {

	private static Logger LOG = Logger.getLogger(BaseController.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleInternalError(Exception ex) {
		LOG.error("Error occurred", ex);
		return ex.getMessage();
	}
}
