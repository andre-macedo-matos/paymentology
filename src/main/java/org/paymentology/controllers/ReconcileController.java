package org.paymentology.controllers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.paymentology.models.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.opencsv.bean.CsvToBeanBuilder;

@Controller
public class ReconcileController {
	
	@RequestMapping(value = "transactionCompare", method = RequestMethod.POST)
	public ModelAndView transactionCompare(@RequestParam("file1") MultipartFile file1,
								   		   @RequestParam("file2") MultipartFile file2) {
		
		List<Transaction> transactions1 = getFileTransactions(file1);
		List<Transaction> transactions2 = getFileTransactions(file2);
		
		List<Transaction> diff1 = (List<Transaction>) CollectionUtils.subtract(transactions1, transactions2).stream().collect(Collectors.toList());
		List<Transaction> diff2 = (List<Transaction>) CollectionUtils.subtract(transactions2, transactions1).stream().collect(Collectors.toList());
		
		ModelAndView modelAndView = new ModelAndView("unmatched-report");
		
		modelAndView.addObject("diff1", diff1);
		modelAndView.addObject("diff2", diff2);
		
		diff1.forEach(System.out::println);
		System.out.println();
		diff2.forEach(System.out::println);
		
		return modelAndView;
	}

	private List<Transaction> getFileTransactions(MultipartFile file)  {
		Reader inputStreamReader;
		
		try {
			inputStreamReader = new InputStreamReader(file.getInputStream());
			List<Transaction> transactions = new CsvToBeanBuilder<Transaction>(inputStreamReader)
					.withSkipLines(1)
					.withType(Transaction.class)
					.build()
					.parse();
			
			return transactions;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("form");
		return modelAndView;
	}

}
