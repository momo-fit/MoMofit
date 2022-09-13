function selectEmail(ele){
    var $ele = $(ele);
    var $email2 = $('input[name=email2]');

    if($ele.val() == "1"){
        $email2.attr('readonly', false);
        $email2.val('');
    } else {
        $email2.attr('readonly', true);
        $email2.val($ele.val());
    }
}

// 메일 발송 ajax
$('.btn-primary1').click(()=> {

    const email1 = $('#email1').val();
    const email2 = $('#addr').val();
    const email = email1 + '@' + email2
    const checkNum = $(".form-control");

    if( email1  == ''  || email2  == '' ){
        alert("이메일을 입력해 주세요.");
    } else {
        $.ajax({
            type: 'get',
            url: '/mailCheck?email='+email,
            
            success: ((data) => {
                checkNum.attr('disabled', false);
                currentNum=data;
                alert("전송되었습니다.");            
            }),
            error: ((data) => {
                alert("발송이 실패 했습니다.");
            })
        }) // ajax

    } // if-else
    
})

// 인증처리
$('#pinNumCheck').click(()=> {
    const message = $("#pinNum");
    const inputNum = $("#form-control").val();

    console.log(inputNum);

    if(inputNum === currentNum) {
        message.text('인증완료 되었습니다.');
        message.css('color','green');
        $("#form-control").attr('readonly', true);
        $('input[name=email1]').attr('readonly', true);
        $('input[name=email2]').attr('readonly', true);

    } else {
        message.text('인증번호가 불일치 합니다. 다시 입력하세요');
        message.css('color','red');
    }

})


$('#idBtn').click(()=> {
    const id = $('#id').val();
    const idResult = $('#idResult');
    
    if(id == '') {
        alert('아이디를 입력 해주세요.');
    } else {
        $.ajax ({
            type: 'POST',
            url: '/signup/idCheck',
            data : {'id': id},
            async: false,
            success: ((data) => {
                if(data == true) {
                    idResult.text('사용 가능한 ID 입니다.');
                    idResult.css('color','green');
                } else {
                    idResult.text('이미 사용중인 ID 입니다.');
                    idResult.css('color','red');
                }
            }),
            error: ((data) => alert('인증을 할수 없습니다.'))
        })// ajax
	} // if

}) 

$('#nickBtn').click(()=> {
    const nickname = $('#nick_name').val();
    const nickNameResult = $('#nickNameResult');
    
    if(id == '') {
        alert('닉네임을 입력 해주세요.');
    } else {
        $.ajax ({
            type: 'POST',
            url: '/signup/nickNameCheck',
            data : {'nickname': nickname},
            async: false,
            success: ((data) => {
                if(data == true) {
                    nickNameResult.text('사용 가능한 닉네임 입니다.');
                    nickNameResult.css('color','green');
                } else {
                    nickNameResult.text('이미 사용중인 닉네임 입니다.');
                    nickNameResult.css('color','red');
                }
            }),
            error: ((data) => alert('인증을 할수 없습니다.'))
        })// ajax
	} // if

}) 

// 비밀번호 확인
$('.pass2').keyup(()=> {
    const pwCheck = $('#pwCheck');
    const pass1 = $('.pass1').val();
    const pass2 = $('.pass2').val();
    
    if(pass1 != pass2) {
        pwCheck.text("비밀번호가 일치하지 않습니다.")
        pwCheck.css('color','red');
    } else {
        pwCheck.text('일치합니다.');
        pwCheck.css('color','green');
    }

})