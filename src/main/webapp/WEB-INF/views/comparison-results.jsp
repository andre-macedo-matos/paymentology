<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 class="display-3">Comparison Results</h2>
<div class="container">
	<div class="row">
		<c:forEach items="${report.filesValues}" var="entry">
			<div class="col">
				<div class="card">
					<h5 class="card-header">${entry.key}</h5>
					<div class="card-body">
						<dl class="row">
							<dt class="col-sm-6">Total Records:</dt>
							<dd class="col-sm-6">${entry.value.totalRecords}</dd>
							<dt class="col-sm-6">Matching Records:</dt>
							<dd class="col-sm-6">${entry.value.totalMatches}</dd>
							<dt class="col-sm-6">Unmatched Records:</dt>
							<dd class="col-sm-6">${entry.value.totalUnmatches}</dd>
						</dl>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<div>
	<form:form method="post"
		action="${spring:mvcUrl('RC#unmatchedReport').build()}">

		<div class="input-group input-group mb-3">
			<input class="btn btn-primary" type="submit" value="Unmatched Report">
		</div>
	</form:form>
</div>