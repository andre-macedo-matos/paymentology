package org.paymentology.models;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

public class Report {

	private List<Transaction> outerUnmatched1;
	private List<Transaction> outerUnmatched2;
	
	private List<Transaction> unmatched1;
	private List<Transaction> unmatched2;
	
	public Report(Inputs inputs) {
		setOuterUnmatched1(inputs);
		setOuterUnmatched2(inputs);
		
		setUnmatched1();
		setUnmatched2();
	}

	public List<Transaction> getOuterUnmatched1() {
		return outerUnmatched1;
	}

	public void setOuterUnmatched1(Inputs inputs) {
		this.outerUnmatched1 = outerJoin(inputs.getFile1(), inputs.getFile2());
	}

	public List<Transaction> getOuterUnmatched2() {
		return outerUnmatched2;
	}

	public void setOuterUnmatched2(Inputs inputs) {
		this.outerUnmatched2 = outerJoin(inputs.getFile2(), inputs.getFile1());
	}

	public List<Transaction> getUnmatched1() {
		return unmatched1;
	}

	public void setUnmatched1() {
		this.unmatched1 = reconcileRecords(this.outerUnmatched1, this.outerUnmatched2);
	}

	public List<Transaction> getUnmatched2() {
		return unmatched2;
	}

	public void setUnmatched2() {
		this.unmatched2 = reconcileRecords(this.outerUnmatched2, this.outerUnmatched1);
	}

	@SuppressWarnings("unchecked")
	private List<Transaction> outerJoin(List<Transaction> file1, List<Transaction> file2) {
		return (List<Transaction>) CollectionUtils.subtract(file1, file2).stream().collect(Collectors.toList());
	}
	
	private List<Transaction> reconcileRecords(List<Transaction> diff1, List<Transaction> diff2) {
		return diff1.stream()
				.flatMap(t1 -> {
					return diff2.stream()
						.filter(t2 -> t1.getId().equals(t2.getId())
										&& t1.getWalletReference().equals(t2.getWalletReference())
										&& t1.getType().equals(t2.getType()))
						.filter(t2 -> t1.getProfileName().equals(t2.getProfileName())
										||!t1.getDate().equals(t2.getDate())
										||!t1.getAmount().equals(t2.getAmount())
										||!t1.getNarrative().equals(t2.getNarrative())
										||!t1.getDescription().equals(t2.getDescription()));
				})
				.collect(Collectors.toList());
	}
}