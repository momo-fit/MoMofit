$('#search_id_email').keydown(function() {
    if (event.keyCode === 13) {
      event.preventDefault();
    };
});
   
function replaceFun(input, index, character) {
    return character + character + input.substr(index+character.length);
}


$('.search_id_button').click(()=> {

    const email = $('#search_id_email').val().replace('@', ',');
    console.log(email);

    const findIdResult = $('#resultId');

    if(email == '') {
        alert("이메일을 입력해 주세요.")
    } else {
        $.ajax ({
            type: 'POST',
            url: '/find/findId',
            data: {'email':email},
            async: false,
            success: ((data) => {
                if(data != ''){
                    result = replaceFun(data,1, '*');
                    console.log(result);
                    findIdResult.text(result);
                } else {
                    findIdResult.text("등록되어 있지 않은 이메일 입니다.")
                    findIdResult.css('color','red');
                }
            })

        }) // ajax
    
    } // else-if
  
})