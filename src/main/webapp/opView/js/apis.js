var API_ADDRESS = {
  base: '/onlineShopping',
  path: {
    'login': {
      url: '/login.do',
      method: 'post'
    },
    'logout': {
      url: '/logout.do',
      method: 'post'
    },
    //获取已经登录用的信息 同时也可以作为判断该用户是否登录
    'currentUser': {
      url: '/getCurrentLoginUser.do',
      method: 'post'
    },
    'userInfo': {
      url: '/user/loadUser.do',
      method: 'post'
    },
    'addUser': {
      url: '/user/addUser.do',
      method: 'post'
    },
    'delUser': {
      url: '/user/deleteUser.do',
      method: 'post'
    },
    'updateUser': {
      url: '/user/updateUser.do',
      method: 'post'
    },
    'checkUser': {
      url: '/user/checkUserCode.do',
      method: 'post'
    },
    'shopInfo': {
      url: '/shop/loadShop.do',
      method: 'post'
    },
    'addShop': {
      url: '/shop/addShop.do',
      method: 'post'
    },
    'delShop': {
      url: '/shop/deleteShop.do',
      method: 'post'
    },
    'updateShop': {
      url: '/shop/updateShop.do',
      method: 'post'
    },
    'loadImage': {
      url: '/shop/loadImage.do',
      method: 'post'
    },
    'checkShopName': {
      url: '/shop/checkShopName.do',
      method: 'post'
    },
    'goodsInfo': {
      url: '/goods/loadGoods.do',
      method: 'post'
    },
    'addGoods': {
      url: '/goods/addGoods.do',
      method: 'post'
    },
    'delGoods': {
      url: '/goods/deleteGoods.do',
      method: 'post'
    },
    'updateGoods': {
      url: '/goods/updateGoods.do',
      method: 'post'
    },
    'getGoodsList': {
      url: '/goods/getGoodsByUserId.do',
      method: 'post'
    },
    'loadOrder': {
      url: '/order/loadOrder.do'
    },
    'goodsHomePage': {
        url: '/goods/queryGoodsHomePage.do',
        method: 'post'
    },
    'addTempOrder': {
        url: '/order/addTempOrder.do',
        method: 'post'
    },
    'deleteTempOrder': {
        url: '/order/deleteTempOrder.do',
        method: 'post'
    },
    'getTempOrder': {
        url: '/order/getTempOrder.do',
        method: 'post'
    },
    'getGoodsAllType': {
        url: '/goods/getGoodsAllType.do',
        method: 'post'
    },
    'getFeedBackByGoodsId': {
        url: '/feedBack/getFeedBackByGoodsId.do',
        method: 'post'
    },
    'checkUserIsFeedBack': {
        url: '/feedBack/checkUserIsFeedBack.do',
        method: 'post'
    },
    'generatingOrder': {
        url: '/order/generatingOrder.do',
        method: 'post'
    },
    'batchAddOder': {
        url: '/order/batchAddOder.do',
        method: 'post'
    },
    'getOrderByUserId': {
        url: '/order/getOrderByUserId.do',
        method: 'post'
    },
    'addGoods': {
        url: '/goods/addGoods.do',
        method: 'post'
    },
    'addFeedBack': {
        url: '/feedBack/addFeedBack.do',
        method: 'post'
    },
    'revertFeedBack': {
        url: '/feedBack/revertFeedBack.do',
        method: 'post'
    },
    'IsGoodsByUserId': {
        url: '/feedBack/isGoodsByUserId.do',
        method: 'post'
    },
    'cancelOrder': {
        url: '/order/cancelOrder.do',
        method: 'post'
    },
    'confirmOrder': {
        url: '/order/confirmOrder.do',
        method: 'post'
    }
  }
}
