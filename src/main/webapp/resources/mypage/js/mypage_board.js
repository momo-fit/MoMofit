// 체크박스 전체선택하기 
function selectAll(selectAll)  {
	const checkboxes = document.getElementsByName('board-check');
	
	checkboxes.forEach((checkbox) => {
		checkbox.checked = selectAll.checked;
	})
}

$(function () {

	
	$(".bs-text").keypress(function(e){
		if(e.keyCode==13)
		$(".bs-btn").click();
	});

})

