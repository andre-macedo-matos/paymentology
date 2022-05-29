package org.paymentology.models;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

public class SubtractionsOfLists {
	
	private List<?> l1MinusL2;
	private List<?> l2MinusL1;
	
	public SubtractionsOfLists(List<?> l1, List<?> l2) {
		this.l1MinusL2 = subtract(l1, l2);
		this.l2MinusL1 = subtract(l2, l1);
	}
	
	public List<?> getL1MinusL2() {
		return l1MinusL2;
	}
	public void setL1MinusL2(List<?> l1MinusL2) {
		this.l1MinusL2 = l1MinusL2;
	}
	public List<?> getL2MinusL1() {
		return l2MinusL1;
	}
	public void setL2MinusL1(List<?> l2MinusL1) {
		this.l2MinusL1 = l2MinusL1;
	}

	@SuppressWarnings("unchecked")
	private List<?> subtract(List<?> l1, List<?> l2) {
		return (List<?>) CollectionUtils.subtract(l1, l2).stream().collect(Collectors.toList());
	}

}
