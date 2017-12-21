var shopCartVM = new Vue({
  el: "#shopCart",
  created: function(){
    this.getCartList()
  },
  data: {
    cartList: []
  },
  methods: {
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
    }
  }
})
