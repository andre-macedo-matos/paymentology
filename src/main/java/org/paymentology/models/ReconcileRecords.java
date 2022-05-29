package org.paymentology.models;

import java.util.List;
import java.util.stream.Collectors;

import org.paymentology.interfaces.ReconcileStrategy;

public class ReconcileRecords {
	
	private List<Transaction> l1WithL2;
	private List<Transaction> l2WithL1;

	public ReconcileRecords(List<Transaction> l1, List<Transaction> l2, ReconcileStrategy strategy) {
		this.l1WithL2 = reconcile(l1, l2, strategy);
		this.l2WithL1 = reconcile(l2, l1, strategy);
	}

	public List<Transaction> getL1WithL2() {
		return l1WithL2;
	}

	public void setL1WithL2(List<Transaction> l1WithL2) {
		this.l1WithL2 = l1WithL2;
	}

	public List<Transaction> getL2WithL1() {
		return l2WithL1;
	}

	public void setL2WithL1(List<Transaction> l2WithL1) {
		this.l2WithL1 = l2WithL1;
	}

	private List<Transaction> reconcile(List<Transaction> diff1, List<Transaction> diff2, 
			ReconcileStrategy reconcileStrategy) {

		return diff2.stream()
				    .flatMap(t2 -> {
				    	return diff1.stream()
				    				.filter(t1 -> reconcileStrategy.isPossibleOfReconciliation(t1, t2))
				    				.filter(t1 -> reconcileStrategy.isUnmatched(t1, t2));
				    })
				    .collect(Collectors.toList());
	}

}
