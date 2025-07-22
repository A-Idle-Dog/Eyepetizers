package com.example.module_video

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.module_video.Adapter.ViewPagerAdapter
import com.example.module_video.ViewModel.relatedViewModel
import com.example.module_video.databinding.ActivityVideoBinding
import com.example.module_video.model.invokeitem


@Route(path = "/module_video/VideoActivity")
class VideoActivity : AppCompatActivity() {
    private lateinit var myInvokeItem: invokeitem
    private lateinit var binding: ActivityVideoBinding
    private val viewModel by lazy { ViewModelProvider(this).get(relatedViewModel::class.java) }
    private  lateinit var maxList : List<invokeitem>

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
        initdataclass()
        observeViewModel()
        test()
        getListData()

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
            shareUrl
        )
    }

    fun observeViewModel(){
        viewModel.relatedList.observe(this){
            maxList = it
            initadapter()
        }
    }

    fun getListData(){
         viewModel.getdata(myInvokeItem)
    }

    fun test(){
        Log.d("whjwhj", "test: $myInvokeItem")
    }

    fun initadapter(){
        binding.viewpager2.apply {
                adapter = ViewPagerAdapter(this@VideoActivity, maxList)
                orientation = ViewPager2.ORIENTATION_VERTICAL
        }
    }
}