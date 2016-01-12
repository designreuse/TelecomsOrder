// 邮政编码验证
jQuery.validator.addMethod("isZipCode", function(value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮政编码");
// 固话  传真验证
jQuery.validator.addMethod("isTel", function(value, element) {
    var tel = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的号码");
// 手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
    var tel = /^1\d{10}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的号码");
// 银行账号验证
jQuery.validator.addMethod("isCard", function(value, element) {
    var tel = /^\d{16}|\d{19}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的银行账号");
// 固话 手机 两者验证
jQuery.validator.addMethod("isMobileOrTel", function(value, element) {
    var tel = /^1\d{10}|(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的号码");
// 非0整数
jQuery.validator.addMethod("nonzeroInteger", function(value, element) {
    var reg = /^-?[1-9]{1}\d*\b$/;
    return this.optional(element) || (reg.test(value));
}, "请填写非0整数");