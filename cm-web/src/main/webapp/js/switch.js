function init(joinAble, loginAble) {
    var switch1, switch2;
    switch1 = loginAble;
    switch2 = joinAble;

    if (switch1 == true) {
        switchON($("#switch1_on"), $("#switch1_state1"), $("#switch1_off"), $("#switch1_state2"));
    } else {
        switchOFF($("#switch1_on"), $("#switch1_state1"), $("#switch1_off"), $("#switch1_state2"));
    }
    if (switch2 == true) {
        switchON($("#switch2_on"), $("#switch2_state1"), $("#switch2_off"), $("#switch2_state2"));
    } else {
        switchOFF($("#switch2_on"), $("#switch2_state1"), $("#switch2_off"), $("#switch2_state2"));
    }


}
function switchON(on, state1, off, state2) {
    on.css("display", "block");
    state1.css("display", "block");
    off.css("display", "none");
    state2.css("display", "none");
}
function switchOFF(on, state1, off, state2) {
    on.css("display", "none");
    state1.css("display", "none");
    off.css("display", "block");
    state2.css("display", "block");
}
function switchChange(on, state1, off, state2) {

    if (on.css("display") == "none")
        switchON(on, state1, off, state2);
    else switchOFF(on, state1, off, state2);

}
