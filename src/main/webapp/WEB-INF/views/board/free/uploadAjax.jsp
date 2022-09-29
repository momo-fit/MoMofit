<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Upload with Ajax</title>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"
		integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"
		integrity="sha512-QDsjSX1mStBIAnNXx31dyvw4wVdHjonOwrkaIhpiIlzqGUCdsI62MwQtHpJF+Npy2SmSlGSROoNWQCOFpqbsOg=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>

<body>

	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>

	<div class="uploadResult">
		<ul>

		</ul>
	</div>

	<button id="uploadBtn">Upload</button>

	<script>
		$(document).ready(function () {

			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 5242880; // 5MB

			function checkExtension(fileName, fileSize){
				if(fileSize >= maxSize){
					alert("파일 사이즈 초과");
					return false;
				} // if

				if(regex.test(fileName)){
					alert("해당 종류의 파일은 업로드 할 수 없습니다.");
					return false;
				} // if

				return true;
			}

			// input 태그 초기화
			var cloneObj = $(".uploadDiv").clone();

			// 업로드 된 파일이름 출력
			var uploadResult = $(".uploadResult ul");		
				function showUploadedFile(uploadResultArr){
					var str = "";
					$(uploadResultArr).each(function(i, obj){
						str += "<li>" + obj.fileName + "</li>";
					});					
					uploadResult.append(str);
				}

			$("#uploadBtn").on("click", function (e) {

				var formData = new FormData();
				var inputFile = $("input[name='uploadFile']");
				var files = inputFile[0].files;

				console.log(files);

				// add File Data to formData
				for(var i =0; i<files.length; i++){
					if(!checkExtension(files[i].name, files[i].size)){
						return false;
					}

					formData.append("uploadFile", files[i]);
				} // for

				// Ajax를 이용해 formData 자체를 전송
				$.ajax({
					url: '/board/free/uploadAjaxAction',
						processData : false,
						contentType : false,
						data : formData,
						type : 'POST',
						dataType : 'json',
						success : function(result){
							console.log(result);

							// 업로드 된 파일이름 출력
							showUploadedFile(result);

							// input 태그 초기화
							$(".uploadDiv").html(cloneObj.html());
						}
				}) // $.ajax
			});
			
		});
	</script>

</body>

</html>