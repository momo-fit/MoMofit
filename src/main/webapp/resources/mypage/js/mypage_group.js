// 진행중모임/종료된모임 다르게보이기 
function change_end()
{
	$('.mypage-group-end').show();
	$('.mypage-group-ing').hide();
	
} 
function change_ing()
{
	$('.mypage-group-end').hide();
	$('.mypage-group-ing').show();
} 


$(function () {

	
	$(".chat-textarea").keypress(function(e){
		if(e.keyCode==13) {
			$(".chat-textarea-btn").click();
			$(".chat-textarea").val('');
			return false;
		}
		
	});

})
