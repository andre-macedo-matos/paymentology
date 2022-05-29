package org.paymentology.controllers;

import javax.validation.Valid;

import org.paymentology.helpers.ReconcileWithUnmatchedId;
import org.paymentology.models.Inputs;
import org.paymentology.models.Report;
import org.paymentology.validators.InputsValidator;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class ReconcileController {

	@InitBinder("inputs")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new InputsValidator());
	}

	@RequestMapping(value = "transactionCompare", method = RequestMethod.POST)
	public ModelAndView transactionCompare(@Valid Inputs inputs, BindingResult bindingResult, RedirectAttributes attributes) {
		if (bindingResult.hasErrors())
			return form(inputs);
		
		attributes.addFlashAttribute("report", new Report(inputs, new ReconcileWithUnmatchedId()));
		attributes.addFlashAttribute("showCompare", "display:visible;");

		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView form(Inputs inputs) {
		ModelAndView modelAndView = new ModelAndView("form");
		return modelAndView;
	}
	
	@RequestMapping(value = "/json/report", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView jsonReport(@Valid Inputs inputs) {
		ModelAndView modelAndView = new ModelAndView("report");
		Report report = new Report(inputs, new ReconcileWithUnmatchedId());
		
		String reportAsJson;
		try {
			reportAsJson = new ObjectMapper().writeValueAsString(report);
		} catch (JsonProcessingException e) {
			reportAsJson = "Error to convert to json: " + e.getMessage();
			e.printStackTrace();
		}
		modelAndView.addObject("report", reportAsJson);
		
		return modelAndView;
	}

}
