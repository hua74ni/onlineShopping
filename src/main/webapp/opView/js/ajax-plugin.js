(function($, apis){
  var plugins = {}
  plugins.install = function (vue, options) {
    var notify = vue.prototype.$notify

    Object.defineProperty(vue.prototype, "$req", {
      value: req
    })

    function req (path, data) {
      if (apis.path.hasOwnProperty(path)) {
        return new Promise(function(resolve, reject){
          $.ajax(
            apis.base + apis.path[path].url,
            {
              dataType: 'json',
                headers: {
                  'Content-Type': 'application/json'
                },
              method: apis.path[path].method,
              data: JSON.stringify(data),
              success: function (data) {
                console.log(data)
                if (data.statusCode !== 200) {
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
      }
    }
  }
  Vue.use(plugins)
}(jQuery, API_ADDRESS))
