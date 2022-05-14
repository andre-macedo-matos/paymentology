<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Comparison Results</title>
</head>
<body>
	<h2>Comparison Results</h2>
	<c:forEach items="${report.filesValues}" var="entry">
		<div style="display: inline-block;" align = "left">
			<p><b>${entry.key}</b></p>
			<p>Total Records: ${entry.value.totalRecords}</p>
			<p>Matching Records: ${entry.value.totalMatches}</p>
			<p>Unmatched Records: ${entry.value.totalUnmatches}</p>
		</div>
	</c:forEach>

	<div>
		<form:form method="post" action="${spring:mvcUrl('RC#unmatchedReport').build()}">

			<div>
				<input type="submit" value="Unmatched Report">
			</div>
		</form:form>
	</div>

</body>
</html>