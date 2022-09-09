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
                console.log("data : " + data);
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