
/*************JS扩展方法*****************/
String.prototype.TrimEnd = function (v) {
    if (this.substring(this.length - 1, this.length) == v) {
        return this.substring(0, this.length - 1);
    }
    else {
        return this;
    }
}
String.prototype.Trim = function () {
    return this.replace(/(^[\s]*)|([\s]*$)/g, "");
}
String.prototype.LenCH = function () {
    return this.replace(/[^\x00-\xff]/g, "xx").length;
}

String.prototype.IsNaN = function () {
    var filter = /^\d+$/;
    return filter.test(this);
}
String.prototype.IsPhone = function () {
    var filter = /^1[3|4|5|8][0-9]\d{8,8}$/;
    return filter.test(this);
}
String.prototype.IsTelephone = function () {
    var filter = /^0[0-9]{2,3}-[2-9][0-9]{7}(-[0-9]{1,4})?$/
    return filter.test(this);
}
String.prototype.IsEmail = function () {
    var filter = /^(?:\w+\.?)*\w+@(?:\w+\.)+\w+$/;
    return filter.test(this);
}
String.prototype.IsQQ = function () {
    var filter = /^[1-9][0-9]{4,12}$/;
    return filter.test(this);
}
String.prototype.IsNullOrEmpty = function () {
    if (typeof (this) == "undefined") {
        return true;
    }
    else if (this.length == 0) {
        return true;
    }
    return false;
}

Array.prototype.toSimpleString = function () {
    if (this.length) {
        var str = "";
        var len = this.length - 1;
        for (var i = 0; i < len; i++) {
            str += this[i] + ",";
        }
        if (len >= 0) {
            str += this[len];
        }
        return str;
    } else {
        return "";
    }
}
/*******************JQuery扩展************************/
var apiAjax = {
    parseParam: function (param) {
        var res = { url: '', data: '' };
        res.url = apiHost + "/" + param.action.split('.').join('/');
        var paramsArr = new Array();
        if (typeof (param.data) == "object") {
            for (var p in param.data) {
                var pvalue = param.data[p];
                if (jQuery.isFunction(pvalue)) {
                    continue;
                }
                if (typeof (pvalue) != "object") {
                    paramsArr.push(p + "=" + pvalue);
                }
                else {
                    for (var arrValue in pvalue) {
                        var v = pvalue[arrValue];
                        if (jQuery.isFunction(v)) {
                            continue;
                        }
                        var val = typeof (pvalue) == "string" ? encodeURIComponent(v.Trim()) : v;
                        paramsArr.push(p + "=" + val);
                    }
                }
            }
        }
        res.data = param.data;//paramsArr.join('&');
        //if (typeof (param.data) != "object") {
        //    res.data += '&' + param.data;
        //}
        return res;
    },
    sendAjax: function (param, type, dataType, _success) {
        param = this.parseParam(param);
        if (type == null) {
            type = "POST";
        }
        var ajaxParam = {
            type: type,
            url: param.url,
            data: param.data,
            timeout: 120 * 1000
        };

        if (dataType == null) {
            ajaxParam.dataType = "json";
        }
        else {
            ajaxParam.dataType = "text";
        }

        ajaxParam.success = function (res) {
            _success(res);
        }
        ajaxParam.error = function (jqXHR, textStatus, errorThrown) {
            if (textStatus == "parsererror") {
                alert("数据结果转换错误");
            }
            if (textStatus == "timeout") {
                alert("请求超时");
            }
            if (textStatus == "abort") {
                alert("请求中断");
            }
            if (textStatus == "error") {
            }
        }
        jQuery.ajax(ajaxParam);
    },
    get: function (param, success, dataType) {
        this.sendAjax(param, "get", dataType, success);
    },
    post: function (param, success, dataType) {
        this.sendAjax(param, "post", dataType, success);
    }
};
/*******************表单操作************************/
(function ($) {
    $.fn.toObject = function (p) {
        for (var i = 0; i < this.length; i++) {
            var arr = $(this[i]).serializeArray();
            var temp = new Array();
            for (var j = 0; j < arr.length; j++) {
                var name = arr[j].name;
                var item = temp[name];
                if (typeof (item) == 'undefined') {
                    temp[name] = new Array();
                    item = temp[name];
                }
                item.push(arr[j].value);
            }

            var obj = {};
            for (var name in temp) {
                if (temp[name].length == 1) {
                    var info = temp[name][0];
                    info = info.replace(/(\r\n)/g, "★");
                    info = info.replace(/\'/g, "\\'");
                    var fun = "obj." + name + "='" + info + "';";
                    eval(fun);
                }
                else {
                    var _arr = new Array();
                    for (var k = 0; k < temp[name].length; k++) {
                        _arr.push("'" + temp[name][k] + "'");
                    }
                    var arrStr = "[" + _arr.join(',') + "]";
                    var fun = "obj." + name + "=" + arrStr + ";";
                    eval(fun);
                }
            }
            return obj;
        }
    };
    $.fn.bindForm = function (data) {
        $(this).each(function (index, form) {
            form = $(form);
            for (var p in data) {
                var input = $("#" + p, form);
                var val = data[p];
                input.val(val);
            }
        });
    };
})(jQuery);


//自动和当前域名一致
var apiHost = "http://" + getHost(location.href) + "/";

/*****************共用方法*******************/
function getHost(url) {
    var host = "null";
    if (typeof url == "undefined" || null == url) {
        return host;
    }
    var regex = /.*\:\/\/([^\/]*).*/;
    if (url.indexOf("://") == -1) {
        url = "http://" + url;
    }
    var match = url.match(regex);
    if (typeof match != "undefined" && null != match) {
        host = match[1];
    }
    return host;
}

/*提示控件统一封装*/
/* option:参数集合 | msg：提示内容
*/
var alertapi = {
    option: {
        "closeButton": true,
        "debug": false,
        "positionClass": "toast-top-right",/*弹窗位置调用自己想象*/
        "showDuration": "1000",
        "hideDuration": "1000",
        "timeOut": "2000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut",
        "shortCutFunction": "warning",  /* warning 警告   success 成功   info  错误 error */
        "msg": "",
        "title": "",
    },
    /*初始化弹出框控件*/
    start: function (option) {
        if (option != undefined && option != null && typeof (option) == "object") {
            for (var p in option) {
                for (var m in alertapi.option) {
                    if (m == p) {
                        alertapi.option[m] = option[p];
                    }
                }
            }
        }
        if (typeof (option) == "string") {
            alertapi.option.msg = option;
        }
        toastr[alertapi.option.shortCutFunction](alertapi.option.msg, alertapi.option.title, alertapi.option);
    },
    warning: function (msg) {
        alertapi.start({ "shortCutFunction": "warning", "msg": msg });
    },
    success: function (msg) {
        alertapi.start({ "shortCutFunction": "success", "msg": msg });
    },
    info: function (msg) {
        alertapi.start({ "shortCutFunction": "info", "msg": msg });
    },
    error: function (msg) {
        alertapi.start({ "shortCutFunction": "error", "msg": msg });
    }
}

//界面提示
tipapi = {
    error: function (element) {
        element.parent().removeClass("success");
        element.parent().addClass("error");
    },
    success: function (id) {
        var i = $("#" + id);
        if (i.parent().hasClass("error")) {
            i.parent().removeClass("error");
            i.parent().addClass("success");
        }
    }
}

//绑定Select控件
var BindSelect = function (ctrlName, url) {
    var control = $('#' + ctrlName);
    //设置Select2的处理
    control.select2({
        allowClear: true
    });

    //绑定Ajax的内容
    $.getJSON(url, function (data) {
        //control.empty();
        $.each(data, function (i, item) {
            control.append("<option value='" + item.Value + "'>&nbsp;" + item.Text + "</option>");
        });
    });
}