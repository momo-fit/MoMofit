$('#search_id_email').keydown(function() {
    if (event.keyCode === 13) {
      event.preventDefault();
    };
});
   
function replaceFun(input, index, character) {
    return character + character + input.substr(index+character.length);
}


$('.button_mail').click(()=> {

    const id = $('#search_pw_id').val();
    const email = $('#search_pw_email').val().replace('@', ',');
    console.log(email , " + ", id);

    if(email == '') {
        alert("이메일을 입력해 주세요.")
    } else {
        $.ajax ({
            type: 'POST',
            url: '/find/findPW',
            data: {
                    'email':email,
                    'id': id
                },
            async: false,
            success: ((data) => {
                if(data == true) {
                    alert("메일이 발송되었습니다.");
                    location.href = "/signin";
                } else {
                    alert("아이디 또는 이메일을 확인해 주세요");
                }
            }),
            error: ((data) => {
                alert("메일 발송에 실패했습니다.");
            })

        }) // ajax
    
    } // else-if
  
})