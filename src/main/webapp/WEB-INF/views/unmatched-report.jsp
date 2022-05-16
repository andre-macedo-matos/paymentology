<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="customTags" tagdir="/WEB-INF/tags"%>

<customTags:pageTemplate bodyClass="body" title="Unmatched Report">
	<jsp:body>
		<h2 class="display-3">Unmatched Report</h2>
			<div class="row">
				<c:forEach items="${report.filesValues}" var="entry">
					<div style="font-size: 9px" class="col-md-6">
							<table class="table table-light table-striped table-hover table-sm caption-top table-responsive">
								<caption>${entry.key}</caption>
								<thead>
								<tr>
									<th>TransactionDate</th>
									<th>TransactionAmount</th>
									<th>TransactionNarrative</th>
									<th>Description</th>
									<th>TransactionID</th>
									<th>Type</th>
									<th>WalletReference</th>
								</tr>
								</thead>
								<tbody class="table-group-divider">
									<c:forEach items="${entry.value.unmatchedRecords}" var="transaction">
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
				</c:forEach>
			</div>
	
	</jsp:body>
</customTags:pageTemplate>