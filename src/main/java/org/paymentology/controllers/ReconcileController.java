package org.paymentology.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReconcileController {
	
	@RequestMapping(value = "transactionCompare", method = RequestMethod.POST)
	public ModelAndView transactionCompare(@RequestParam("file1") MultipartFile file1,
								   		   @RequestParam("file2") MultipartFile file2) throws IOException {
		
		String content1 = getFileContent(file1);
		String content2 = getFileContent(file2);
		
		System.out.println(content1);
		System.out.println(content2);
		
		return form();
	}

	private String getFileContent(MultipartFile file1) throws IOException {
		return IOUtils.toString(new ByteArrayInputStream(file1.getBytes()));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {
		return new ModelAndView("form");
	}

}
