package com.example.module_video

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.data.AppDatabase
import com.example.module_video.Adapter.ViewPagerAdapter
import com.example.module_video.ViewModel.CollectViewModel
import com.example.module_video.ViewModel.FavoriteViewModel
import com.example.module_video.ViewModel.relatedViewModel
import com.example.module_video.databinding.ActivityVideoBinding
import com.example.module_video.model.invokeitem


@Route(path = "/module_video/VideoActivity")
class VideoActivity : AppCompatActivity() {
    private lateinit var myInvokeItem: invokeitem
    private lateinit var binding: ActivityVideoBinding
    private val viewModel by lazy { ViewModelProvider(this).get(relatedViewModel::class.java) }
    private  lateinit var maxList : List<invokeitem>
    private lateinit var  dataBase:AppDatabase
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var collectViewModel: CollectViewModel
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    @Autowired
    @JvmField
    var playuri : String = ""

    @Autowired
    @JvmField
    var cover : String = ""

    @Autowired
    @JvmField
    var uid: Int = 0

    @Autowired
    @JvmField
    var title: String = ""

    @Autowired
    @JvmField
    var author: String = ""

    @Autowired
    @JvmField
    var authoricon: String = ""

    @Autowired
    @JvmField
    var tags: String = ""

    @Autowired
    @JvmField
    var des: String = ""

    @Autowired
    @JvmField
    var likecount: Int = 0

    @Autowired
    @JvmField
    var collectcount: Int = 0

    @Autowired
    @JvmField
    var isLike: Boolean = false

    @Autowired
    @JvmField
    var isCollect: Boolean = false

    @Autowired
    @JvmField
    var shareUrl: String = ""

    @Autowired
    @JvmField
    var source: Int = 0

    @Autowired
    @JvmField
    var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initDataBase()
        initdataclass()
        test()
        initadapter()
        choiceFromSource(myInvokeItem)
    }

    fun initdataclass(){
        myInvokeItem = invokeitem(
            playuri,
            cover,
            uid,
            title,
            author,
            authoricon,
            tags,
            des,
            likecount,
            collectcount,
            isLike,
            isCollect,
            shareUrl,
            source,
            currentPosition
        )
    }


    fun choiceFromSource(item:invokeitem){
        if(item.source == 0){
            getListData()
            observeViewModel()
        }else if(item.source == 1){
            observeFavoriteViewModel()
        }else if(item.source == 2){
            observeCollectViewModel()
        }
    }

    fun observeCollectViewModel(){
        collectViewModel.collectList.observe(this){
            maxList = it
            viewPagerAdapter.updateData(maxList)
            //initadapter()
        }
    }

    fun observeFavoriteViewModel(){
        favoriteViewModel.favoriteVideos.observe(this){
            maxList = it
            //initadapter()
            viewPagerAdapter.updateData(maxList)
        }
    }

    fun observeViewModel(){
        viewModel.relatedList.observe(this){
            maxList = it
            //initadapter()
            viewPagerAdapter.updateData(maxList)
        }
    }

    fun getListData(){
         viewModel.getdata(myInvokeItem)
    }

    fun test(){
        Log.d("whjwhj", "test: $myInvokeItem")
    }

    fun initadapter(){
        viewPagerAdapter = ViewPagerAdapter(this, emptyList())
        binding.viewpager2.apply {
                adapter = viewPagerAdapter
                orientation = ViewPager2.ORIENTATION_VERTICAL
                if(myInvokeItem.source != 0){
                    currentItem = myInvokeItem.currentPosition
                }else{
                    currentItem = 0
                }
                setCurrentItem(currentItem,false)
        }
    }

    fun initDataBase(){
        dataBase = AppDatabase.getInstance(this)
        favoriteViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FavoriteViewModel(dataBase.favoriteVideoDao()) as T
            }
        }).get(FavoriteViewModel::class.java)
        collectViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CollectViewModel(dataBase.collectVideoDao()) as T
            }
        }).get(CollectViewModel::class.java)
    }

    override fun onDestroy() {
        Log.d("LeakFix", "Activity destroying")
        binding.viewpager2.adapter= null
        super.onDestroy()
    }
}