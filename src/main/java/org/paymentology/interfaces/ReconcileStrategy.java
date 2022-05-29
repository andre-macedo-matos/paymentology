package org.paymentology.interfaces;

import org.paymentology.abstractions.Transaction;

public interface ReconcileStrategy {
	
	boolean isPossibleOfReconciliation(Transaction other);
	
	boolean isUnmatched(Transaction other);


}
