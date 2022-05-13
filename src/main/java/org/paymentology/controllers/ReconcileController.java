package org.paymentology.controllers;

import org.paymentology.models.Inputs;
import org.paymentology.models.Report;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReconcileController {

	@RequestMapping(value = "transactionCompare", method = RequestMethod.POST)
	public ModelAndView transactionCompare(Inputs inputs) {
		Report report = new Report(inputs);
		
		ModelAndView modelAndView = new ModelAndView("unmatched-report");
		modelAndView.addObject("report", report);

		return modelAndView;
	}


	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("form");
		return modelAndView;
	}

}
