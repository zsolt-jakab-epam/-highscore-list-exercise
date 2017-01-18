<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>
	<table>
		<c:forEach items="${coloredLabels}" var="line">
			<tr>
				<td><c:out value="${line.color}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>

</html>
