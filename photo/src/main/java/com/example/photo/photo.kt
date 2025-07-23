package com.example.photo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
    var title : String = "该图片没有标题哦"

    @Autowired
    @JvmField
    var likecount : Int = 0

    @Autowired
    @JvmField
    var collectcount : Int = 0

    @Autowired
    @JvmField
    var tag : String = "该图片没有标签哦"

    @Autowired
    @JvmField
    var isliked : Boolean = false

    @Autowired
    @JvmField
    var uid : Int = 0

    @Autowired
    @JvmField
    var createTime : String = ""

    @Autowired
    @JvmField
    var city : String = ""

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
            uid,
            createTime,
            city,
        )
        Log.d("photoData", "$myphotoData")
    }

    @SuppressLint("SetTextI18n")
    fun initData(){
        binding.tvUsername.text = myphotoData.author
        binding.tvDescription.text = if (myphotoData.title.isNullOrEmpty()) "该图片没有标题哦" else myphotoData.title
        binding.tvLikes.text = myphotoData.likecount.toString()
        binding.tvCollect.text = myphotoData.collectcount.toString()
        val tagText = if (myphotoData.tag.isNullOrEmpty()) "该图片没有标签哦" else myphotoData.tag
        binding.tvHashtag.text = "#$tagText"
        Glide.with(binding.ivUserAvatar.context)
            .load(myphotoData.icon)
            .circleCrop()
            .into(binding.ivUserAvatar)
        binding.btnShare.setOnClickListener {
            animateShare()
        }
        binding.btnLike.setOnClickListener {
            if (!myphotoData.isliked){
                myphotoData.isliked = true
                binding.tvLikes.text = (myphotoData.likecount + 1).toString()
            }else{
                myphotoData.isliked = false
                binding.tvLikes.text = (myphotoData.likecount).toString()
            }
            colorlike(myphotoData)
            animateLike(!myphotoData.isliked)
        }
        binding.btnCollect.setOnClickListener {
            if (!myphotoData.isliked){
                myphotoData.isliked = true
                binding.tvCollect.text = (myphotoData.collectcount + 1).toString()
            }else{
                myphotoData.isliked = false
                binding.tvCollect.text = (myphotoData.collectcount).toString()
            }
            colorcollect(myphotoData)
            animateCollect(!myphotoData.isliked)
        }
    }

    private fun animateLike(isLiked: Boolean) {
        val startColor = if (isLiked) {
            ContextCompat.getColor(this, R.color.red)
        } else {
            ContextCompat.getColor(this, R.color.white)
        }

        val endColor = if (isLiked) {
            ContextCompat.getColor(this, R.color.white)
        } else {
            ContextCompat.getColor(this, R.color.red)
        }

        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor).apply {
            duration = 300
            addUpdateListener { animator ->
                binding.btnLike.setColorFilter(animator.animatedValue as Int)
            }
        }
        val scaleX = ObjectAnimator.ofFloat(binding.btnLike, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.btnLike, "scaleY", 1f, 1.2f, 1f)
        if (!isLiked) {
            showLikeParticle()
        }
        AnimatorSet().apply {
            playTogether(colorAnimation, scaleX, scaleY)
            start()
        }
    }
    private fun showLikeParticle() {
        val location = IntArray(2)
        binding.btnLike.getLocationOnScreen(location)

        showExplosionAnimation(
            location[0] + binding.btnLike.width / 2,
            location[1] + binding.btnLike.height / 2
        )
    }
    private fun showExplosionAnimation(x: Int, y: Int) {
        val rootView = (this as Activity).window.decorView as ViewGroup
        repeat(20) {
            val particle = ImageView(this).apply {
                setImageResource(R.drawable.love)
            }
            val angle = Math.random() * Math.PI * 2
            val distance = 100 + Math.random() * 100

            val params = FrameLayout.LayoutParams(20, 20).apply {
                leftMargin = x - 10
                topMargin = y - 10
            }
            rootView.addView(particle, params)
            val xAnim = ObjectAnimator.ofFloat(particle, "translationX", (Math.cos(angle) * distance).toFloat())
            val yAnim = ObjectAnimator.ofFloat(particle, "translationY", (Math.sin(angle) * distance).toFloat())
            val alphaAnim = ObjectAnimator.ofFloat(particle, "alpha", 1f, 0f)
            AnimatorSet().apply {
                playTogether(xAnim, yAnim, alphaAnim)
                duration = 800
                interpolator = AccelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        rootView.removeView(particle)
                    }
                })
                start()
            }
        }
    }

    private fun animateCollect(isCollect: Boolean) {
        val startColor = if (isCollect) {
            ContextCompat.getColor(this, R.color.yellow)
        } else {
            ContextCompat.getColor(this, R.color.white)
        }

        val endColor = if (isCollect) {
            ContextCompat.getColor(this, R.color.white)
        } else {
            ContextCompat.getColor(this, R.color.yellow)
        }

        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor).apply {
            duration = 300
            addUpdateListener { animator ->
                binding.btnCollect.setColorFilter(animator.animatedValue as Int)
            }
        }
        val scaleX = ObjectAnimator.ofFloat(binding.btnCollect, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.btnCollect, "scaleY", 1f, 1.2f, 1f)

        if (!isCollect) {
            showCollectParticle()
        }
        AnimatorSet().apply {
            playTogether(colorAnimation, scaleX, scaleY)
            start()
        }
    }

    private fun showCollectParticle() {
        val location = IntArray(2)
        binding.btnCollect.getLocationOnScreen(location)
        showExplosionAnimation(
            location[0] + binding.btnCollect.width / 2,
            location[1] + binding.btnCollect.height / 2
        )
    }

    fun initadapter(){
        binding.viewPager.adapter = VPadapter(myphotoData.itemList)
    }

    private fun colorcollect(item: photoData) {
        var color = 0
        if (item.isliked){
            color = ContextCompat.getColor(this, R.color.yellow)
        }else{
            color = ContextCompat.getColor(this, R.color.white)
        }
        binding.btnCollect.setColorFilter(color)
    }

    fun colorlike(item: photoData){
        var color = 0
        if(item.isliked){
            color = ContextCompat.getColor(this, R.color.red)
        }else{
            color = ContextCompat.getColor(this, R.color.white)
        }
        binding.btnLike.setColorFilter(color)
    }

    private fun animateShare(){
        val scaleX = ObjectAnimator.ofFloat(binding.btnShare, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.btnShare, "scaleY", 1f, 1.2f, 1f)
        AnimatorSet().apply {
            playTogether(scaleX, scaleY)
            start()
        }
    }
}
