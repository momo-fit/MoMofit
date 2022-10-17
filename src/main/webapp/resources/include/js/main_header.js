$(function () {
    $(".dropdown-content").on({

        "mouseover" : function () {
            $(this).prev().css({
                color: "rgb(52,152,219)",
                textDecoration : "underline"
            });
        },

        "mouseout" : function () {
            $(this).prev().css({
                color: "#252525",
                textDecoration : "none"
            });
        }   
    })
}); 

$('#search_submit').click(()=> {
    let searchFormHeader = $('.searchFormHeader');

    var value = $('#search_value').val();    

    searchFormHeader.find("input[name='type']").val("N");
    searchFormHeader.find("input[name='keyword']").val(value);
    searchFormHeader.find("input[name='pageNum']").val(1);
    searchFormHeader.submit();

})


