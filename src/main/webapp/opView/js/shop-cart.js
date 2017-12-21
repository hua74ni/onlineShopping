var shopCartVM = new Vue({
  el: "#shopCart",
  created: function(){
    this.getCartList()
    this.getUser()
  },
  data: {
    cartList: [],
    user: {}
  },
  computed: {
    total: function(){
      var length = this.cartList.length
      var total = 0
      for (var i = 0; i < length; i++) {
          total += this.cartList[i].goodsNum * this.cartList[i].goodsPrice
      }
      return total
    }
  },
  methods: {
    getUser: function(){
      var self = this
      self.$req('currentUser')
        .then(function(data){
          console.log(data)
          self.user = data || {}
        })
    },
    logout: function () {
      var self = this
      self.$req('logout')
        .then(function(data){
          self.$notify({
            title: '提示',
            message: '登出成功'
          })
          window.location.pathname = "/onlineShopping/opView/product-list.html"
          self.user = {}
          self.getCartList()
        })
    },
    getCartList: function () {
      var self = this
      this.$req('getTempOrder', {})
        .then(function(data){
          self.cartList = data
        })
    },
    addToCart: function (id) {
      var self = this;
      this.$req('addTempOrder', {
        goodsId: id
      })
      .then(function(){
        self.$notify.success({
          title: '提示',
          message:'添加成功'
        })
        self.getCartList()
      })
    },
    delCart: function (id) {
      var self = this
      this.$req('deleteTempOrder', {
        orderId: id
      })
      .then(function(){
        self.$notify.success({
          title: '提示',
          message: '删除成功'
        })
        self.getCartList()
      })
    },
    delCartNoRresh: function (index, id) {
      var self = this
      self.cartList.splice(index, 1)
      this.$req('deleteTempOrder', {
        orderId: id
      })
      .then(function(){
        self.$notify.success({
          title: '提示',
          message: '删除成功'
        })
      })
    }
  }
})
