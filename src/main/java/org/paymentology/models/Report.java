package org.paymentology.models;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.paymentology.helpers.ReconcileWithUnmatchedId;
import org.paymentology.interfaces.ReconcileStrategy;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBeanBuilder;

public class Report {

	private Map<String, FileValues> filesValues = new HashMap<String, FileValues>();

	public Report(Inputs inputs) {
		List<Transaction> fileTransactions1 = getFileTransactions(inputs.getFile1());
		List<Transaction> fileTransactions2 = getFileTransactions(inputs.getFile2());
		
		List<Transaction> outerTransaction1 = subtract(fileTransactions1, fileTransactions2);
		List<Transaction> outerTransaction2 = subtract(fileTransactions2, fileTransactions1);
		
		List<Transaction> unmatchedRecords1 = reconcileRecords(outerTransaction1, outerTransaction2);
		List<Transaction> unmatchedRecords2 = reconcileRecords(outerTransaction2, outerTransaction1);
		
		FileValues fileValues1 = new FileValues(inputs.getFile1().getOriginalFilename(), 
												fileTransactions1.size(), 
												fileTransactions1.size() - outerTransaction1.size(), 
												unmatchedRecords1.size(), 
												outerTransaction1,
												unmatchedRecords1);
		
		FileValues fileValues2 = new FileValues(inputs.getFile2().getOriginalFilename(), 
												fileTransactions2.size(), 
												fileTransactions2.size() - outerTransaction2.size(), 
												unmatchedRecords2.size(), 
												outerTransaction2,
												unmatchedRecords2);
		
		this.filesValues.put(fileValues1.getName(), fileValues1);
		this.filesValues.put(fileValues2.getName(), fileValues2);
	}

	public Map<String, FileValues> getFilesValues() {
		return filesValues;
	}

	public void setFilesValues(Map<String, FileValues> filesValues) {
		this.filesValues = filesValues;
	}
	
	private List<Transaction> getFileTransactions(MultipartFile file) {
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

	@SuppressWarnings("unchecked")
	private List<Transaction> subtract(List<Transaction> file1, List<Transaction> file2) {
		return (List<Transaction>) CollectionUtils.subtract(file1, file2)
												  .stream()
												  .collect(Collectors.toList());
	}

	private List<Transaction> reconcileRecords(List<Transaction> diff1, List<Transaction> diff2) {
		ReconcileStrategy reconcileStrategy = new ReconcileWithUnmatchedId();

		return diff1.stream()
				    .flatMap(t1 -> {
				    	return diff2.stream()
				    				.filter(t2 -> reconcileStrategy.isPossibleOfReconciliation(t1, t2))
				    				.filter(t2 -> reconcileStrategy.isUnmatched(t1, t2));
				    })
				    .collect(Collectors.toList());
	}
	
}