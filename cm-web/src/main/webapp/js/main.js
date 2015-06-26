function setHeight() {
    var center = window.parent.document.getElementById("center");
    var frame = window.parent.parent.document.getElementById("frame");
    frame.style.height = "0px";
    //center.style.height="0px";
    var h = Math.max(document.documentElement.scrollHeight, document.body.scrollHeight);
    //center.style.height =h+"px";
    frame.style.height = h + 200 + "px";
}
function changeTitle(title) {
    window.parent.parent.document.getElementsByTagName("title")[0].innerHTML = title;
}

$(function () {
    var right = $("#right").css("height");
    var left = $("#left").css("height");
    if (right != undefined && left != undefined) {
        right_height = right.replace("px", "");
        left_height = left.replace("px", "");
        if (parseInt(right_height) > parseInt(left_height))
            $("#left").css("height", right);
    }
});