<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Compare Transaction</title>
</head>
<body>
	<h2>Specify files to Compare</h2>
	<form:form method="post"
	 	action="${spring:mvcUrl('RC#transactionCompare').build()}"
	 	commandName="inputs"
		enctype="multipart/form-data">

		<div>
			<label for="file1">File 1</label> 
			<input type="file" name="file1" />
		</div>
		
		<div>
			<label for="file2">File 2</label> 
			<input type="file" name="file2" />
		</div>

		<div>
			<input type="submit" value="Compare">
		</div>
	</form:form>
</body>
</html>