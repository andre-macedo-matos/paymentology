package org.paymentology.models;

import java.util.Objects;

import org.paymentology.abstractions.Transaction;

import com.opencsv.bean.CsvBindByPosition;

public class TransactionMarkoff extends Transaction {

	@CsvBindByPosition(position = 0)
	private String profileName;

	@CsvBindByPosition(position = 1)
	private String date;

	@CsvBindByPosition(position = 2)
	private String amount;

	@CsvBindByPosition(position = 3)
	private String narrative;

	@CsvBindByPosition(position = 4)
	private String description;

	@CsvBindByPosition(position = 5)
	private String id;

	@CsvBindByPosition(position = 6)
	private String type;

	@CsvBindByPosition(position = 7)
	private String walletReference;

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWalletReference() {
		return walletReference;
	}

	public void setWalletReference(String walletReference) {
		this.walletReference = walletReference;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, date, description, id, narrative, profileName, type, walletReference);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionMarkoff other = (TransactionMarkoff) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(date, other.date)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(narrative, other.narrative) && Objects.equals(profileName, other.profileName)
				&& Objects.equals(type, other.type) && Objects.equals(walletReference, other.walletReference);
	}

	@Override
	public String toString() {
		return "Transaction [profileName=" + profileName + ", date=" + date + ", amount=" + amount + ", narrative="
				+ narrative + ", description=" + description + ", id=" + id + ", type=" + type + ", walletReference="
				+ walletReference + "]";
	}

	@Override
	public boolean isPossibleOfReconciliation(Transaction other) {
		TransactionMarkoff m2 = (TransactionMarkoff) other;
		
		return this.getWalletReference().equals(m2.getWalletReference())
				&& this.getType().equals(m2.getType());
	}

	@Override
	public boolean isUnmatched(Transaction other) {
		TransactionMarkoff m2 = (TransactionMarkoff) other;
		
		return !this.getProfileName().equals(m2.getProfileName())
				||!this.getDate().equals(m2.getDate())
				||!this.getAmount().equals(m2.getAmount())
				||!this.getNarrative().equals(m2.getNarrative())
				||!this.getDescription().equals(m2.getDescription())
				||!this.getId().equals(m2.getId());
	}

}
