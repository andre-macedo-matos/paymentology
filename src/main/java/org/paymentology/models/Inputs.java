package org.paymentology.models;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBeanBuilder;

public class Inputs {

	private List<Transaction> file1;
	private List<Transaction> file2;
	
	public List<Transaction> getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = getFileTransactions(file1);
	}
	public List<Transaction> getFile2() {
		return file2;
	}
	public void setFile2(MultipartFile file2) {
		this.file2 = getFileTransactions(file2);
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

}
