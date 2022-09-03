# 一、实践介绍
## 1.1项目核心信息
本项目实现了影视综艺榜单及其历史数据查询，实现个人页面展示、个人页面粉丝和关注列表、个人页面已发布视频列表及其详情页
## 1.2项目服务地址
[https://github.com/gujunhe/douyin](https://github.com/gujunhe/douyin)
## 1.3GitHub地址
[https://github.com/gujunhe/douyin](https://github.com/gujunhe/douyin)

# 二、实践分工
|团队成员|      主要贡献|
|--|--|
| 我 |负责基础架构、网络框架、room框架搭建、个人页面已发布视频列表及其详情页、粉丝和关注列表  |
|XX|电影榜单、粉丝和关注列表|
|XX|电视剧榜单、粉丝和关注列表|
|XX|综艺榜单、导航栏搭建|
|XX|榜单数据管理、导航栏搭建|
|XX|影视榜单布局、个人界面布局|
# 三、实践实现
## 3.1 技术选型与相关开发文档
### 3.1.1技术选型
**Navigation**
提供路由导航服务
**ViewModel**
具备生命周期感知能力的数据存储组件,用于存放应用程序页面所需的数据
![在这里插入图片描述](https://img-blog.csdnimg.cn/874aedccc676466eaeb38680cba2c054.png)
#### **Retrofit**
实现网络请求。
#### **Gson**
用来配合Retrofit、Room实现序列化和反序列化，自定义Type Converter将榜单List数据转化为Json存储到数据库中。
#### **Room**
数据库，与LiveData、ViewModel结合使用，当Room数据库中的数据发生变化时，能够通过LiveData组件通知View层，实现数据的自动更新。
#### **LiveData**
LiveData是一个可被观察的数据容器类，在ViewModel中的数据发生变化时通知页面。
#### **DataBinding**
使用声明性格式将布局中的界面组件绑定到应用中的数据源，使页面与布局文件之间的耦合度进一步降低。
#### **Paing**
列表分页组件,实现数据预加载、按需加载，结合Room使用，数据直接来源于Room数据库。当网络数据请求成功后，会直接将其写入Room数据库，由于使用了LiveData，当数据有变化时，ViewModel会自动得到通知，自动完成数据的更新。
#### **Picasso**
加载网络图片，实现占位图、图片自动缓存。
### 3.1.2开发文档
#### 协作模式
项目通过GitHub进行团队协作开发。
### 3.2 架构设计
本项目采用MVVM的架构，在ViewModel层和Model层之间引入Repository层。在Repository层处理本地数据和网络数据之间的业务逻辑，让Repository层对ViewModel层负责，使ViewModel只需要关心自己的业务逻辑，而不用关心数据的具体来源。数据在发生变化时，界面能够自动得到通知并进行更新，数据模型驱动界面更新。
![在这里插入图片描述](https://img-blog.csdnimg.cn/ba550c26f20a46559e91bdeacfb32dc3.png)
这里以影视综艺榜单代码为例

 1.  RankFragment持有RankViewModel，获取到来自RankViewModel的数据后将数据加载进recyclerview。
 

```java
rankViewModel.getRankItemByTypeAndVersion(type, "141").observe(getViewLifecycleOwner(), new Observer<List<RankItem.DataBean.ListBean>>() {
    @Override
    public void onChanged(List<RankItem.DataBean.ListBean> listBeans) {
        if(listBeans!=null) {
            myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(listBeans);
            recyclerView.setAdapter(myItemRecyclerViewAdapter);
        }
    }
});
```

 2. RankViewModel持有rankRepository，通过rankRepository获取到榜单数据。
 

```java
public LiveData<List<RankItem.DataBean.ListBean>> getRankItemByTypeAndVersion(String type,String version) {
    return rankRepository.getRankItemByTypeAndVersion(type,version);
}
```

 3. RankRepository进行数据请求。

```java
public LiveData<List<RankItem.DataBean.ListBean>> getRankItemByTypeAndVersion(String type,String version) {
    return rankRepository.getRankItemByTypeAndVersion(type,version);
}
```

```java
public void refreshitembyTypeAndVersion(String type,String version)
{
    apiService.getrankitem("application/json",MyApplication.clientToken.getValue(),type,version).enqueue(new Callback<RankItem>() {
        @Override
        public void onResponse(Call<RankItem> call, Response<RankItem> response) {
            if(response.body().getData().getList()!=null)
            {
                insertRankItem(response.body().getData().getList());//插入数据库
                Log.d(TAG,response.body().getData().getList().toString());
            }
        }
        @Override
        public void onFailure(Call<RankItem> call, Throwable t) {
        }
    });
}
```

```java
private  void insertRankItem(List<RankItem.DataBean.ListBean> list )
{
    AsyncTask.execute(new Runnable() {
        @Override
        public void run() {
            for(int i=0;i<list.size();i++) {
                Log.d(TAG, list.get(i).toString());
                rankItemDao.insertRankItem(list.get(i));
            }
        }
    });
}
```

 4.  RankItemDao获取数据返回的是LiveData对象，当数据库中的数据更新后，ViewModel会自动得到通知，自动完成数据的更新。

```java
@Dao
public interface RankItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRankItem(RankItem.DataBean.ListBean rankitem);
    //获取表中所有数据
    @Query("SELECT *FROM rankitem WHERE type = :type")
    LiveData<List<RankItem.DataBean.ListBean>> findAllbytype(String type);
}
```

## 3.3 项目代码介绍

 -  adapter：各种适配器，包括自定义的ViewBindingAdapter实现直接在布局文件中通过image调用静态方法加载网络图片，FollowingPagedListAdapter和FansPagedListAdapter实现对粉丝和关注列表进行分页加载。
![在这里插入图片描述](https://img-blog.csdnimg.cn/bcd2a71c0aee416bbfa9535ef953df9c.png)

 -  数据库：数据库Database、操作数据库的相关Dao、自定义@TypeConverter
![在这里插入图片描述](https://img-blog.csdnimg.cn/4ee49dd2de16477caf7d032be02ce692.png)

 -  model
![在这里插入图片描述](https://img-blog.csdnimg.cn/7334fc4d4afb4ea59559e7339f36dd29.png)

 -  network:ApiService 接口和RetrofitClient
![在这里插入图片描述](https://img-blog.csdnimg.cn/2d22e15fa9ef4a279c3cec158ff5f47b.png)

 - ui:activity和fragment以及对应的Viewmodel层、Repository层（部分没有使用），分页加载的SourceFactory，以及一些布局所需要的函数。
![在这里插入图片描述](https://img-blog.csdnimg.cn/3f6010c1cc164a10a3b4b186be41f163.png)

 -  view:自定义View，实现自定义底部导航栏、个人界面相关view、实现顶部下拉图片放大松手图片回弹的view
![在这里插入图片描述](https://img-blog.csdnimg.cn/cac98825fafe4151b0b67031faa506ff.png)
# 四、测试结果
## 功能测试
本项目所有已实现的功能经过测试无报错
## 性能测试
有使用Androidstudio自带的Profiler和手机自带的GPU渲染进行性能测试
# 五、演示demo
## 录屏
（影视榜单api超出限制，无法演示榜单版本切换）

[video(video-eqqTLysg-1662106841512)(type-csdn)(url-https://live.csdn.net/v/embed/236293)(image-https://video-community.csdnimg.cn/vod-84deb4/907a20fc53454342a5e9dbc73acd80c7/snapshots/b4bcf811df0b4f1796ead0881aeb4e9d-00004.jpg?auth_key=4815706654-0-0-ef6677a552e9cdf1d16681073ed2936b)(title-演示demo)]
## 核心功能截图
### 影视综艺榜单页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/4fc44a52f49f4d88a4686d416d819b0a.png)

### 个人页面及个人页面已发布视频列表
![在这里插入图片描述](https://img-blog.csdnimg.cn/bc4fa1fc4e5f455fae814d87bf8a03dc.png)
### 关注及粉丝列表
![在这里插入图片描述](https://img-blog.csdnimg.cn/9aed27a7348c403fa734dfb7bbbb286a.png)
### 个人已发布视频详情页
![在这里插入图片描述](https://img-blog.csdnimg.cn/ac4b622d9e84493492c8fe83ecf94d3d.png)
# 六、实践总结与反思
## 6.1 目前仍存在的问题
- 对clientsecret、access_token没有加密保存。
- 界面不够美观。
- 没有编写 access_token过期后自动刷新的代码。
##  6.2 已识别出的优化项
- 减少页面布局的嵌套，优化recyclerview的加载速度。
- 对一些可以复用的代码进行可扩展的封装，减少代码量，提高开发效率。
- 优化项目结构
## 6.3 架构演进的可能性
## 6.4 项目过程中的反思与总结
第一次当组长通过团队协作完成项目，在通过github进行团队协作中还是遇到了不少困难，同时也学到了很多。在项目刚开始构建时，本想用模块化进行开发，以方便团队协作、开发和维护，但是后来觉得项目不大，就没有采用，后面导致团队协作遇到困难。

在模仿抖音个人界面实现下拉放大、松手自动回弹的效果时，查找了很多资料和方案，最后实现了比较好的效果，在这个过程中加深了对自定义view的理解，让自己的技术得到了提升。

由于是第一次完成一个不算小的项目，在架构方面、代码封装方面做得不太好，只是为了完成所要求的功能，没有对代码进行太多的扩展，在写代码时也没有编写详细的注释，这些方面应该要去改善。
# 七、其他补充资料（选填）
