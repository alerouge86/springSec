package com.alerouge.kyivent.web.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "error/page404";
	        } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "error/page500";
	        }
	    }
	    return "error/pageErrorGeneric";
	}
	
    @Override
    public String getErrorPath() {
        return "/error";
    }
    
}
