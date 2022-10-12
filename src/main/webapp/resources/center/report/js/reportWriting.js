// 이미지 업로드 미리보기
$('input[name="file"]').change(function(){
    $('.img').show();

    setImageFromFile(this, '#uplodeImg');
    
});


function setImageFromFile(input, expression) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();
        reader.onload = function (e) {
            $(expression).attr('src', e.target.result);
        }
        
        reader.readAsDataURL(input.files[0]);
    } // if

    
}


// 기본 클릭시
$('.xBtn').click(()=> {
    $('#uplodeImg').attr('src', '#');
    $('input[name="file"]').val("");

    $('.img').hide();
})