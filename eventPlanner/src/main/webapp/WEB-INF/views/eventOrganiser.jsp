<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${eventOrganiser.organiserName}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- style sheet -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/eventPlannerStyle.css"/> ">
<!-- javascripts -->
<script src="<c:url value="/resources/js/action.js"/>"></script>
</head>
<body>
	<div class="container">
		<div class="eventOrganiser">
			<div class='page-header'>
				<h1>
					${eventOrganiser.organiserName}					
				</h1>
			</div>

			<div>
				<c:choose>
					<c:when test="${not empty eventsSpecificToOrganiser}">
						<ul class="list-unstyled">
							<c:forEach items="${eventsSpecificToOrganiser}" var="event">
								<li><a href="<c:url value='/organiser${eventOrganiser.eventOrganiserId}/plan/event${event.eventId}' />">
										${event.eventName} </a></li>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
					There are no Events
				</c:otherwise>
				</c:choose>
			</div>

		</div>
	</div>
</body>
</html>
