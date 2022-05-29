package org.paymentology.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.paymentology.helpers.ReconcileByIds;
import org.paymentology.helpers.ReconcileWithUnmatchedId;
import org.springframework.mock.web.MockMultipartFile;

class ReportTest {

	@Test
	public void shouldReturnExceptionWhenInputsIsNull() {
		assertThrows(NullPointerException.class, () -> {
			new Report(null, new ReconcileByIds());
		});
	}

	@Test
	public void shouldReturnExceptionWhenFileIsEmpty() throws FileNotFoundException, IOException {
		File file = new File("./src/test/resources/empty.csv");
		MockMultipartFile mockMultipartFile = new MockMultipartFile("empty.csv", new FileInputStream(file));

		Inputs inputs = new Inputs();
		inputs.setFile2(mockMultipartFile);
		inputs.setFile2(mockMultipartFile);

		assertThrows(NullPointerException.class, () -> {
			new Report(inputs, new ReconcileByIds());
		});
	}

	@Test
	public void shouldReturnExceptionWhenFileHasOneLessHeader() throws FileNotFoundException, IOException {
		File file = new File("./src/test/resources/one-less-header.csv");
		MockMultipartFile mockMultipartFile = new MockMultipartFile("one-less-header.csv", new FileInputStream(file));

		Inputs inputs = new Inputs();
		inputs.setFile2(mockMultipartFile);
		inputs.setFile2(mockMultipartFile);

		assertThrows(NullPointerException.class, () -> {
			new Report(inputs, new ReconcileByIds());
		});
	}

	@Test
	public void shouldSubtractTransactionsAndReturnOneTransactiotn() {
		Transaction t = new Transaction();
		t.setProfileName("t");

		Transaction t1 = new Transaction();
		t1.setProfileName("t1");

		List<Transaction> transactions1 = new ArrayList<Transaction>();
		List<Transaction> transactions2 = new ArrayList<Transaction>();

		transactions1.add(t);
		transactions1.add(t1);

		transactions2.add(t);

		List<Transaction> actual = (List<Transaction>) new SubtractionsOfLists(transactions1, transactions2)
				.getL1MinusL2();

		List<Transaction> expected = new ArrayList<Transaction>();
		expected.add(t1);

		assertEquals(expected.size(), actual.size());
		assertTrue(actual.equals(expected));
	}

	@Test
	public void givenTwoEquivalentTransactionsByUnmatchedIDShouldReconcileByDifferentsNames() {
		Transaction t1 = new Transaction();
		t1.setWalletReference("");
		t1.setType("");
		t1.setProfileName("t1");

		Transaction t2 = new Transaction();
		t2.setWalletReference("");
		t2.setType("");
		t2.setProfileName("t2");

		List<Transaction> transactions1 = new ArrayList<Transaction>();
		List<Transaction> transactions2 = new ArrayList<Transaction>();

		transactions1.add(t1);
		transactions2.add(t2);

		SubtractionsOfLists subtractions = new SubtractionsOfLists(transactions1, transactions2);

		List<Transaction> actual = (List<Transaction>) new ReconcileRecords(
				(List<Transaction>) subtractions.getL1MinusL2(), (List<Transaction>) subtractions.getL2MinusL1(),
				new ReconcileWithUnmatchedId()).getL1WithL2();

		List<Transaction> expected = new ArrayList<Transaction>();
		expected.add(t1);

		assertEquals(expected.size(), actual.size());
		assertTrue(actual.equals(expected));
	}

	@Test
	public void givenTwoEquivalentTransactionsByIDShouldReconcileByDifferentsNames() {
		Transaction t1 = new Transaction();
		t1.setWalletReference("");
		t1.setId("");
		t1.setType("");
		t1.setProfileName("t1");

		Transaction t2 = new Transaction();
		t2.setWalletReference("");
		t2.setId("");
		t2.setType("");
		t2.setProfileName("t2");

		List<Transaction> transactions1 = new ArrayList<Transaction>();
		List<Transaction> transactions2 = new ArrayList<Transaction>();

		transactions1.add(t1);
		transactions2.add(t2);

		SubtractionsOfLists subtractions = new SubtractionsOfLists(transactions1, transactions2);

		List<Transaction> actual = (List<Transaction>) new ReconcileRecords(
				(List<Transaction>) subtractions.getL1MinusL2(), (List<Transaction>) subtractions.getL2MinusL1(),
				new ReconcileWithUnmatchedId()).getL1WithL2();

		List<Transaction> expected = new ArrayList<Transaction>();
		expected.add(t1);

		assertEquals(expected.size(), actual.size());
		assertTrue(actual.equals(expected));
	}

}
