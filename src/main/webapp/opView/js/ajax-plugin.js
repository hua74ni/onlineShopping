(function($, apis){
  var plugins = {}
  plugins.install = function (vue, options) {
    var notify = vue.prototype.$notify

    Object.defineProperty(vue.prototype, "$req", {
      value: req
    })

    function req (path, data, formdata) {
      var isFormData = formdata === 'formdata'
      if (apis.path.hasOwnProperty(path)) {
        return new Promise(function(resolve, reject){
          $.ajax(
            apis.base + apis.path[path].url,
            {
              dataType: 'json',
              contentType: isFormData ? false : 'application/json',
              processData: isFormData ? false : true,
              method: apis.path[path].method,
              data: formdata === 'formdata' ? data : JSON.stringify(data),
              success: function (data) {
                console.log(data)
                if (data.statusCode !== 200) {
                  console.log(data.statusCode)
                  notify.error({
                    title: '请求错误',
                    message: data.message
                  })
                  reject()
                } else {
                  resolve(data.data)
                }
              },
              error: function () {
                notify.error({
                  title: '网络错误',
                  message: '网络错误'
                })
                reject()
              }
            })
        })
      } else {
        throw new Error('错误的请求地址:' + path)
      }
    }
  }

  var paramsPlugin = {}
  paramsPlugin.install = function (vue) {
    Object.defineProperty(vue.prototype, '$param', {
      value: p
    })

    function p () {
      var params = window.location.search.slice(1).split('&')
      var paramsObj = {}

      for (var i = 0; i < params.length; i++) {
        var p = params[i].split('=')

        paramsObj[p[0]] = p[1]
      }

      return paramsObj
    }
  }

  Vue.use(plugins)
  Vue.use(paramsPlugin)
}(jQuery, API_ADDRESS));

(function() {
  /**
   * Decimal adjustment of a number.
   *
   * @param {String}  type  The type of adjustment.
   * @param {Number}  value The number.
   * @param {Integer} exp   The exponent (the 10 logarithm of the adjustment base).
   * @returns {Number} The adjusted value.
   */
  function decimalAdjust(type, value, exp) {
    // If the exp is undefined or zero...
    if (typeof exp === 'undefined' || +exp === 0) {
      return Math[type](value);
    }
    value = +value;
    exp = +exp;
    // If the value is not a number or the exp is not an integer...
    if (value === null || isNaN(value) || !(typeof exp === 'number' && exp % 1 === 0)) {
      return NaN;
    }
    // If the value is negative...
    if (value < 0) {
      return -decimalAdjust(type, -value, exp);
    }
    // Shift
    value = value.toString().split('e');
    value = Math[type](+(value[0] + 'e' + (value[1] ? (+value[1] - exp) : -exp)));
    // Shift back
    value = value.toString().split('e');
    return +(value[0] + 'e' + (value[1] ? (+value[1] + exp) : exp));
  }

  // Decimal round
  if (!Math.round10) {
    Math.round10 = function(value, exp) {
      return decimalAdjust('round', value, exp);
    };
  }
  // Decimal floor
  if (!Math.floor10) {
    Math.floor10 = function(value, exp) {
      return decimalAdjust('floor', value, exp);
    };
  }
  // Decimal ceil
  if (!Math.ceil10) {
    Math.ceil10 = function(value, exp) {
      return decimalAdjust('ceil', value, exp);
    };
  }
})();

(function(){

function dateFormat(format) {
    var date = this
    var map = {
        "M": date.getMonth() + 1, //月份
        "d": date.getDate(), //日
        "h": date.getHours(), //小时
        "m": date.getMinutes(), //分
        "s": date.getSeconds(), //秒
        "q": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    format = format.replace(/([yMdhmsqS])+/g, function(all, t){
        var v = map[t];
        if(v !== undefined){
            if(all.length > 1){
                v = '0' + v;
                v = v.substr(v.length-2);
            }
            return v;
        }
        else if(t === 'y'){
            return (date.getFullYear() + '').substr(4 - all.length);
        }
        return all;
    });
    return format;
}

Date.prototype.format = dateFormat
})();
