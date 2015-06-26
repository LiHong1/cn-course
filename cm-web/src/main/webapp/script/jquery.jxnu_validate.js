(function ($) {

    var __validate = $.fn.validate;
    $.fn.cms_validate = function (opts) {

        var __rules = $.extend({
            username: "required",
            password: "required",
            age: "digits",
            ach: "number",
            email: "email",
            url: "url"
        }, opts ? (opts.rules || {}) : {});
        var __messages = $.extend({
            username: "用户名不能为空",
            password: "用户密码不能为空",
            age: "年龄必须是整数",
            ach: "成绩必须为数字",
            email: "邮件的格式不正确",
            url: "链接地址不正确"
        }, opts ? (opts.messages || {}) : {});

        var __opts = $.extend((opts || {}), {
            rules: __rules,
            messages: __messages
        });
        //完成了prototype的继承
        $.extend($.fn.validate.prototype, __opts || {});
        __validate.call(this, __opts);
    };
})(jQuery);