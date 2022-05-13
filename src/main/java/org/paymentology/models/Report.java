package org.paymentology.models;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.paymentology.helpers.ReconcileWithUnmatchedId;
import org.paymentology.interfaces.ReconcileStrategy;

public class Report {

	private List<Transaction> outerUnmatched1;
	private List<Transaction> outerUnmatched2;
	
	private List<Transaction> unmatched1;
	private List<Transaction> unmatched2;
	
	private int totalRecords1;
	private int totalRecords2;
	
	private int matchingRecords1;
	private int matchingRecords2;
	
	private int totalUnmatched1;
	private int totalUnmatched2;
	
	public Report() {}

	public Report(Inputs inputs) {
		setOuterUnmatched1(inputs);
		setOuterUnmatched2(inputs);
		
		setUnmatched1();
		setUnmatched2();
		
		setTotalRecords1(inputs);
		setTotalRecords2(inputs);
		
		setMatchingRecords1();
		setMatchingRecords2();

		setTotalUnmatched1();
		setTotalUnmatched2();
	}

	public List<Transaction> getOuterUnmatched1() {
		return outerUnmatched1;
	}

	public void setOuterUnmatched1(Inputs inputs) {
		this.outerUnmatched1 = subtract(inputs.getFile1(), inputs.getFile2());
	}

	public List<Transaction> getOuterUnmatched2() {
		return outerUnmatched2;
	}

	public void setOuterUnmatched2(Inputs inputs) {
		this.outerUnmatched2 = subtract(inputs.getFile2(), inputs.getFile1());
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
	
	public int getTotalRecords1() {
		return totalRecords1;
	}

	public void setTotalRecords1(Inputs inputs) {
		this.totalRecords1 = inputs.getFile1().size();
	}

	public int getTotalRecords2() {
		return totalRecords2;
	}

	public void setTotalRecords2(Inputs inputs) {
		this.totalRecords2 = inputs.getFile2().size();
	}

	public int getMatchingRecords1() {
		return matchingRecords1;
	}

	public void setMatchingRecords1() {
		this.matchingRecords1 = this.totalRecords1 - this.outerUnmatched1.size();
	}

	public int getMatchingRecords2() {
		return matchingRecords2;
	}

	public void setMatchingRecords2() {
		this.matchingRecords2 = this.totalRecords2 - this.outerUnmatched2.size();
	}
	
	public int getTotalUnmatched1() {
		return totalUnmatched1;
	}

	public void setTotalUnmatched1() {
		this.totalUnmatched1 = this.unmatched1.size();
	}

	public int getTotalUnmatched2() {
		return totalUnmatched2;
	}

	public void setTotalUnmatched2() {
		this.totalUnmatched2 = this.unmatched2.size();
	}

	@SuppressWarnings("unchecked")
	private List<Transaction> subtract(List<Transaction> file1, List<Transaction> file2) {
		return (List<Transaction>) CollectionUtils.subtract(file1, file2).stream().collect(Collectors.toList());
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