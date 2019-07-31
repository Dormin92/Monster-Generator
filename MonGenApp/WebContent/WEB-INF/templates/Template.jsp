<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
.w3-bar,h1,button {font-family: "Montserrat", sans-serif}
.fa-anchor,.fa-coffee {font-size:200px}
</style>
<body>

<!-- Header -->
<header class="w3-container w3-sand w3-center" style="padding:32px 16px">
  <h1 class="w3-margin w3-jumbo">Nightmare</h1>
  <p class="w3-xlarge">A Monster Generator</p>
</header>

<!-- First Grid -->
<div class="w3-row-padding w3-padding-64 w3-container">
  <div class="w3-content">
    <div class="w3-center">
      <h5>One of the following monster descriptions is computer generated. Which one do you think it is?</h5>
      <div class="w3-left-align" style="padding:16px">
	      <h4 class="w3-padding-32">1. ${requestScope["Description1"]} </h4>
	      <h4 class="w3-padding-32">2. ${requestScope["Description2"]} </h4>
      </div>
    </div>
    <div class="w3-center">
	    <form action="${pageContext.request.contextPath}/GenServlet" method="post">
	        <button type="submit" name="button" value="0" class="w3-button w3-black w3-padding-large w3-large w3-margin-top">I think the first is fake!</button>
	        <button type="submit" name="button" value="1" class="w3-button w3-black w3-padding-large w3-large w3-margin-top">I think the second is fake!</button>
        </form>
    </div>
  </div>
</div>

</body>
</html>