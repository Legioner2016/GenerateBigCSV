package com.bigcsv.demo.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.bigcsv.demo.service.MyService;

@RestController
public class Controller {
	
	@Autowired
	private MyService service;
	
	@GetMapping("/stream")
	public ResponseEntity<StreamingResponseBody> streamAll(
			@RequestParam(name = "ownerId", required = true) Integer ownerId,
				final HttpServletResponse response) {
        response.setContentType("application/csv");
        response.setHeader(
                "Content-Disposition",
                "attachment;filename=sample.csv");

        return new ResponseEntity<StreamingResponseBody>(outputStream -> service.streamAll(outputStream, ownerId), HttpStatus.OK);
	}

}
