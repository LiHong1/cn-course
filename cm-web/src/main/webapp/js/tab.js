$(document).ready(function () {

    $("#tabfirst li").each(function (index) {
        $(this).mouseover(function () {
            $("#tabfirst li").removeClass("tabin");

            $(this).addClass("tabin");


        });
        $(this).click(function () {
            $(this).removeClass("tabin");
            $("#tabfirst li").removeClass("tabclick");
            $(this).addClass("tabclick");
            $("#area > div").removeClass("contentin");
            $("#area > div").eq(index).addClass("contentin");


        });
    });


});