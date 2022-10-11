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

   <button id="uploadBtn">Upload</button>

   <script>
      $(document).ready(function () {

         

         $("#uploadBtn").on("click", function (e) {

            var formData = new FormData();
            var inputFile = $("input[name='uploadFile']");
            var files = inputFile[0].files;

            console.log(files);

            

            
         });
      });
   </script>

</body>

</html>