# dog的Readme

## 前言

好的，那么如你所见，这便是我们联合开发的开眼app(?),我在其中主要负责了module_square（社区模块）,module_found（发现模块）与module_Hot（热门模块）。

## 介绍

### module_square（社区模块)

![image-20250727175459940](C:\Users\joker\AppData\Roaming\Typora\typora-user-images\image-20250727175459940.png)

社区模块是一个recycleview，使用的瀑布流布局(StaggeredGridLayout)，网络请求应用了retrofit与协程，同时使用了paging3来加载更多内容。

### module_found（发现模块）

![image-20250727175416976](C:\Users\joker\AppData\Roaming\Typora\typora-user-images\image-20250727175416976.png)

![image-20250727175444524](C:\Users\joker\AppData\Roaming\Typora\typora-user-images\image-20250727175444524.png)

发现模块分为两部分，分类与专题，依照前人的智慧，搜索功能没有实现(也不能实现说是)。这两部分都是使用rv，其中分类使用的是GridLayoutManager，而专题使用的是HORIZONTAL的LinearLayoutManager，分类点击去是协调者布局，可以实现上层内容的折叠，内容部分网络请求依然是使用协程还有paging3，同时还设置了一个悬浮按钮用于回升，在这一部分的内容在专题模块是相似的，具体只是布局的不同，这里就不分别介绍了。

### module_Hot（热门模块）

![image-20250727175401350](C:\Users\joker\AppData\Roaming\Typora\typora-user-images\image-20250727175401350.png)

这一模块仍然是使用协程，页面使用的是vp2与fragment的结合，这里使用了一下动态实例化fragment实例，并写了一个transfromer用于页面切换。

## 技术栈

MVVM架构

paging3 + flow实现下滑加载更多

Retrofit + flow进行网络请求

为vp设置自定义的PageTransformer

FloatingActionButton回到RecycleView顶部

SwipeRefreshLayout下拉刷新

## 不足

一些功能没有实现，例如本想在社区模块中搞一个banner，不过最后被接口劝退了(主要是自身能力不足，没办法很好地处理数据)，代码也是臃肿的，很多重复的地方很少封装，对于所用的内容是为用而用，对其背后的内容不很了解。

## 心得体会

在网校也是混了一年了，磕磕绊绊地也是走到这里了，其实，在通过五一考核时我就很惊讶，我竟然可以通过，这种感觉在这次地暑假考核中也是被放大了，除我以外地其他人都很强，群除我佬是对的。在这次地开发过程中，我与杰哥一起共创难关，对于我而言，是收获颇丰的。总的来说，非常感谢红岩网校能够给我大一生活带来这种充满意义的体验，非常感谢各位学长学姐，以及大家。



