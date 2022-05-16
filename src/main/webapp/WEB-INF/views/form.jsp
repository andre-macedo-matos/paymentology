<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="customTags" tagdir="/WEB-INF/tags"%>

<customTags:pageTemplate bodyClass="body"
	title="Specify files to Compare">
	<jsp:body>
		<h2 class="display-3">Specify files to Compare</h2>
		
		<form:form method="post"
			action="${spring:mvcUrl('RC#transactionCompare').build()}"
			commandName="inputs" enctype="multipart/form-data">
	
			<div class="container">
				<div class="input-group input-group mb-3 col">
					<span class="input-group-text" id="inputGroup-sizing-sm" for="file1">File 1</span> 
					<input class="form-control" id="formFile" type="file" name="file1" />
				</div>
				<div class="row">
					<form:errors class="alert alert-danger" role="alert" path="file1" />
				</div>
			
				<div class="input-group input-group mb-3 col">
					<span class="input-group-text" id="inputGroup-sizing-sm" for="file2">File 2</span> 
					<input class="form-control" id="formFile" type="file" name="file2" />
				</div>
				<div class="row">
					<form:errors class="alert alert-danger" role="alert" path="file2" />
				</div>
			</div>
	
			<div class="input-group input-group-sm mb-3">
				<input class="btn btn-primary" type="submit" value="Compare">
			</div>
		</form:form>
	</jsp:body>
</customTags:pageTemplate>
