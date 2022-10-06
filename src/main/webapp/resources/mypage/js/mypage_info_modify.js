
const successNickMessage = '사용 가능한 닉네임 입니다.';
const failNickMessage = '이미 사용중인 닉네임 입니다.';

// 취미선택
let maxCount = 3;								
let count = 0;   								

function CountChecked(field){ 					
	
	if (field.checked) {	
		count += 1;			
		$(field).attr('name', "sports"+count);	
				
	} else {						
		count -= 1;		
		$(field).attr('name', "hobby");				
	}

	if (count > maxCount) {						
		alert("최대 3개까지만 선택가능합니다.");	
        field.checked = false;			
        count -= 1;		
		$(field).attr('name', "hobby");								
	}
}

// 기존 회원정보와 동일한 내용 일치작업
$(function() {  
    // 닉네임
    $('#nickNameResult').text(successNickMessage);
    $('#nickNameResult').css('color','green');

    $("#nick_name").on("propertychange change keyup paste input", function(){
        
        if($("#nick_name").val() == myUserNickname){
            $('#nickNameResult').text(successNickMessage);
            $('#nickNameResult').css('color','green');
        } else {
            $('#nickNameResult').text('');
        }
    });

    // 스포츠 체크 박스
    for(i=1; i<=9; i++){
        var targetSports = $(`input[id=label${i}]`);

        if(targetSports.val() == myUserSports1 
        || targetSports.val() == myUserSports2 
        || targetSports.val() == myUserSports3 
        ) {
            count += 1;
            $(`input[id=label${i}]`).attr('checked', true).attr('name','sports' + count);
        }


    } // for

})




$('#nickBtn').click(()=> {
    const nickname = $('#nick_name').val();
    const nickNameResult = $('#nickNameResult');

    
    if(nickname == '') {
        alert('닉네임을 입력 해주세요.');
    } else {
        $.ajax ({
            type: 'POST',
            url: '/signup/nickNameCheck',
            data : {'nickname': nickname},
            async: false,
            success: ((data) => {
                if(data == true) {
                    nickNameResult.text(successNickMessage);
                    nickNameResult.css('color','green');
                } else {
                    nickNameResult.text(failNickMessage);
                    nickNameResult.css('color','red');
                }
            }),
            error: ((data) => alert('인증을 할수 없습니다.'))
        })// ajax
	} // if

}) 

// 비밀번호 확인

const failPwMessage = '비밀번호가 일치하지 않습니다.';
const successPwMessage = '비밀번호가 일치합니다.';

$('.pass2').keyup(()=> {
    const pwCheck = $('#pwCheck');
    const pass1 = $('.pass1').val();
    const pass2 = $('.pass2').val();
    
    if(pass1 != pass2) {
        pwCheck.text(failPwMessage)
        pwCheck.css('color','red');
    } else {
        pwCheck.text(successPwMessage);
        pwCheck.css('color','green');
    }

})

function autoHypenPhone(str){
    str.value = str.value.replace(/[^0-9]/g, '');

}
function handleOnInput(e)  {
    e.value = e.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/, '')
}

// 필수 체크
$('.sign_up').click(()=> {
    
    if( $('#nickNameResult').text() != successNickMessage) {
		alert("닉네임 인증을 완료해 주세요.");
	} else if($('#pwCheck').text() == failPwMessage) {
		alert("비밀번호를 확인해 주세요.");
	} else {

        encryptPassword();

		$('.sign_up').attr('type', 'submit');
	}
})

function encryptPassword() {
    var crypt = new JSEncrypt(2048);

    //암호화할 문장
    var pass = $('.pass1').val();
    // console.log("암호화할 문장:",pass);

    crypt.setPublicKey(serverPublicKey);

    var encrypted = crypt.encrypt(pass);
    // console.log("서버 공개키로 암호화 : ", encrypted);

    $('#encrypted-pass').val(encrypted);

    $('.form-signin').attr('onsubmit', 'return true');
    $('.form-signin').submit();
}
