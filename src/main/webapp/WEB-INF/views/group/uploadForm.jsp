<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<h1>파일 업로드</h1>

	<form action="uploadFormAction" method="post" enctype="multipart/form-data">
	
		<!-- multiple : 여러개 선택( Controller에서 받을 때 List나 배열로 받는다. -->
		
		첨부파일 <input type="file" name="uploadFile" multiple><br>
		
		<button>전송</button>
	
	</form>

</body>

</html>