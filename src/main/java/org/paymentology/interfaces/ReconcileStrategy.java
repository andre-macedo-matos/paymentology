package org.paymentology.interfaces;

import org.paymentology.models.Transaction;

public interface ReconcileStrategy {
	
	boolean isPossibleOfReconciliation(Transaction t1, Transaction t2);
	
	boolean isUnmatched(Transaction t1, Transaction t2);


}
