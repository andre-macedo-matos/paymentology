package org.paymentology.models;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.paymentology.abstractions.Transaction;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBeanBuilder;

public class Report {

	private Map<String, FileValues> filesValues = new HashMap<String, FileValues>();
	
	public Report() {}

	@SuppressWarnings("unchecked")
	public Report(Inputs inputs) {		
		List<Transaction> fileTransactions1 = getFileTransactions(inputs.getFile1());
		List<Transaction> fileTransactions2 = getFileTransactions(inputs.getFile2());
		
		SubtractionsOfLists outerTransactions = new SubtractionsOfLists(fileTransactions1, fileTransactions2);
		
		ReconcileRecords reconcileRecords = new ReconcileRecords((List<Transaction>) outerTransactions.getL1MinusL2(), 
																 (List<Transaction>) outerTransactions.getL2MinusL1());
		
		FileValues fileValues1 = new FileValues(inputs.getFile1().getOriginalFilename(), 
												fileTransactions1.size(), 
												fileTransactions1.size() - outerTransactions.getL1MinusL2().size(), 
												reconcileRecords.getL1WithL2().size(), 
												(List<Transaction>) outerTransactions.getL1MinusL2(),
												reconcileRecords.getL1WithL2());
		
		FileValues fileValues2 = new FileValues(inputs.getFile2().getOriginalFilename(), 
												fileTransactions2.size(), 
												fileTransactions2.size() - outerTransactions.getL2MinusL1().size(), 
												reconcileRecords.getL2WithL1().size(), 
												(List<Transaction>) outerTransactions.getL2MinusL1(),
												reconcileRecords.getL2WithL1());
		
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
					.withType(TransactionMarkoff.class)
					.build()
					.parse();
			
			return transactions;
		} catch (IOException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
		return null;
	}
	
}