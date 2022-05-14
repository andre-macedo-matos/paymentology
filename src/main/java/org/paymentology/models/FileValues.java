package org.paymentology.models;

import java.util.List;

public class FileValues {

	private String name;
	private int totalRecords;
	private int totalMatches;
	private int totalUnmatches;
	
	private List<Transaction> outerUnmatchedRecords;
	private List<Transaction> unmatchedRecords;
	
	public FileValues(String name, int totalRecords, int totalMatches, int totalUnmatches,
			List<Transaction> outerUnmatchedRecords, List<Transaction> unmatchedRecords) {
		this.name = name;
		this.totalRecords = totalRecords;
		this.totalMatches = totalMatches;
		this.totalUnmatches = totalUnmatches;
		this.outerUnmatchedRecords = outerUnmatchedRecords;
		this.unmatchedRecords = unmatchedRecords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getTotalMatches() {
		return totalMatches;
	}

	public void setTotalMatches(int totalMatches) {
		this.totalMatches = totalMatches;
	}

	public int getTotalUnmatches() {
		return totalUnmatches;
	}

	public void setTotalUnmatches(int totalUnmatches) {
		this.totalUnmatches = totalUnmatches;
	}

	public List<Transaction> getOuterUnmatchedRecords() {
		return outerUnmatchedRecords;
	}

	public void setOuterUnmatchedRecords(List<Transaction> outerUnmatchedRecords) {
		this.outerUnmatchedRecords = outerUnmatchedRecords;
	}

	public List<Transaction> getUnmatchedRecords() {
		return unmatchedRecords;
	}

	public void setUnmatchedRecords(List<Transaction> unmatchedRecords) {
		this.unmatchedRecords = unmatchedRecords;
	}
	
}
