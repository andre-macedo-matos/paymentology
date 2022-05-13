package org.paymentology.helpers;

import org.paymentology.interfaces.ReconcileStrategy;
import org.paymentology.models.Transaction;

public class ReconcileWithUnmatchedId implements ReconcileStrategy {

	@Override
	public boolean isUnmatched(Transaction t1, Transaction t2) {
		return t1.getWalletReference().equals(t2.getWalletReference())
				&& t1.getType().equals(t2.getType());
	}

	@Override
	public boolean isPossibleOfReconciliation(Transaction t1, Transaction t2) {
		return !t1.getProfileName().equals(t2.getProfileName())
				||!t1.getDate().equals(t2.getDate())
				||!t1.getAmount().equals(t2.getAmount())
				||!t1.getNarrative().equals(t2.getNarrative())
				||!t1.getDescription().equals(t2.getDescription())
				||!t1.getId().equals(t2.getId());
	}

}
