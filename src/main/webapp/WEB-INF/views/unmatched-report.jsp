<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unmateched Report</title>
</head>
<body>

<h2>Unmateched Report</h2>
<div style="display: inline-block;">
	<table>
	<h2>File 1</h2>
		<tr>
			<th>TransactionDate</th>
			<th>TransactionAmount</th>
			<th>TransactionNarrative</th>
			<th>Description</th>
			<th>TransactionID</th>
			<th>Type</th>
			<th>WalletReference</th>
		</tr>
		<tbody>
			<c:forEach items="${diff1}" var="transaction">
				<tr>
					<td>${transaction.date}</td>
					<td>${transaction.amount}</td>
					<td>${transaction.narrative}</td>
					<td>${transaction.description}</td>
					<td>${transaction.id}</td>
					<td>${transaction.type}</td>
					<td>${transaction.walletReference}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div style="display: inline-block;">
	<table>
	<h2>File 2</h2>
		<tr>
			<th>TransactionDate</th>
			<th>TransactionAmount</th>
			<th>TransactionNarrative</th>
			<th>Description</th>
			<th>TransactionID</th>
			<th>Type</th>
			<th>WalletReference</th>
		</tr>
		<tbody>
			<c:forEach items="${diff2}" var="transaction">
				<tr>
					<td>${transaction.date}</td>
					<td>${transaction.amount}</td>
					<td>${transaction.narrative}</td>
					<td>${transaction.description}</td>
					<td>${transaction.id}</td>
					<td>${transaction.type}</td>
					<td>${transaction.walletReference}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

</body>
</html>