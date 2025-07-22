package com.example.photo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.photo.Adapter.VPadapter
import com.example.photo.bean.photoData
import com.example.photo.databinding.ActivityPhotoBinding


@Route(path = "/photo/photo")
class photo : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoBinding
    private lateinit var myphotoData  : photoData

    @Autowired
    @JvmField
    var picUrls : ArrayList<String> = arrayListOf()

    @Autowired
    @JvmField
    var icon : String = ""

    @Autowired
    @JvmField
    var author : String = ""

    @Autowired
    @JvmField
    var title : String = ""

    @Autowired
    @JvmField
    var likecount : Int = 0

    @Autowired
    @JvmField
    var collectcount : Int = 0

    @Autowired
    @JvmField
    var tag : String = ""

    @Autowired
    @JvmField
    var isliked : Boolean = false

    @Autowired
    @JvmField
    var uid : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ARouter.getInstance().inject(this)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initphotoData()
        initadapter()
        initData()
    }

    fun initphotoData(){
        myphotoData = photoData(
            picUrls,
            icon,
            author,
            title,
            likecount,
            collectcount,
            tag,
            isliked,
            uid
        )
        Log.d("photoData", "$myphotoData")
    }

    fun initData(){
        binding.tvUsername.text = myphotoData.author
        binding.tvDescription.text = myphotoData.title
        binding.tvLikes.text = myphotoData.likecount.toString()
        binding.tvCollect.text = myphotoData.collectcount.toString()
        binding.tvHashtag.text =  "#"+myphotoData.tag
        Glide.with(binding.ivUserAvatar.context)
            .load(myphotoData.icon)
            .circleCrop()
            .into(binding.ivUserAvatar)
        binding.tvImageCounter.text = myphotoData.itemList.size.toString()
    }

    fun initadapter(){
        binding.viewPager.adapter = VPadapter(myphotoData.itemList)
    }

}