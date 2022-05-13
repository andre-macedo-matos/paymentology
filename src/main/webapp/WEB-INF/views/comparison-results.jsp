<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Comparison Results</title>
</head>
<body>
	<div style="display: inline-block;" align = "left">
		<h2>File 1</h2>
		<p>Total Records: ${report.totalRecords1}</p>
		<p>Matching Records: ${report.matchingRecords1}</p>
		<p>Unmatched Records: ${report.totalUnmatched1}</p>
	</div>
	
	<div style="display: inline-block;" align = "right">
		<h2>File 2</h2>
		<p >Total Records: ${report.totalRecords2}</p>
		<p>Matching Records: ${report.matchingRecords2}</p>
		<p>Unmatched Records: ${report.totalUnmatched2}</p>
	</div>

	<div>
		<form:form method="post" action="${spring:mvcUrl('RC#unmatchedReport').build()}">

			<div>
				<input type="submit" value="Unmatched Report">
			</div>
		</form:form>
	</div>

</body>
</html>