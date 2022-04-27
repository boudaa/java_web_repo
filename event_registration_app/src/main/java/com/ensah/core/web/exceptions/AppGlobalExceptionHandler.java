package com.ensah.core.web.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AppGlobalExceptionHandler {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler
	public ModelAndView handleException(Exception exc, HttpServletRequest req) {

		LOGGER.error("Exception : " , exc);

		ModelAndView modelAndView = new ModelAndView();

		String errorMsg = "The application is configured to work in DEV mode."
				+ " An Error has occurred due to following " + " exception. For more details, please see the log file: "
				+ exc;

		modelAndView.addObject("message", errorMsg);

		modelAndView.addObject("url", req.getRequestURL());
		modelAndView.setViewName(DEFAULT_ERROR_VIEW);

		return modelAndView;

	}

}