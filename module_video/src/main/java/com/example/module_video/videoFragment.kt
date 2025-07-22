package com.example.module_video

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.module_video.Adapter.tabAdapter
import com.example.module_video.databinding.FragmentVideoBinding
import com.example.module_video.model.invokeitem
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.material.tabs.TabLayoutMediator

class videoFragment : Fragment() {
    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!
    private lateinit var exoPlayer: SimpleExoPlayer
    private var invokeitem: invokeitem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            invokeitem = it.getParcelable(ARG_VIDEO_ITEM)
        }
    }

    companion object {
        private const val ARG_VIDEO_ITEM = "video_item"

        fun newInstance(invokeitem: invokeitem): videoFragment {
            return videoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_VIDEO_ITEM, invokeitem)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initvt()
        initplayer(invokeitem!!)
    }

    fun initvt(){
        binding.tabcontent.adapter = tabAdapter(requireActivity(), invokeitem!!)
        TabLayoutMediator(binding.tabLayout, binding.tabcontent) { tab, position ->
            tab.text = when(position) {
                0 -> "简介"
                else -> "评论"

            }
        }.attach()
    }

    fun initplayer(item: invokeitem){
        exoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
        binding.playerview.player = exoPlayer

        val mediaItem = MediaItem.Builder()
            .setUri(item.playuri)
            .build()

        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        binding.usernameTextView.text = item.author
        Glide.with(requireContext())
            .load(item.authoricon)
            .circleCrop()
            .into(binding.avatarImageView)
        binding.likeCount.text = item.likecount.toString()
        binding.collectCount.text = item.collectcount.toString()

        binding.likeIcon.setOnClickListener() {
            if(!item.isLike){
                item.isLike = true
                binding.likeCount.text = (item.likecount + 1).toString()
            }else{
                item.isLike = false
                binding.likeCount.text = (item.likecount).toString()
            }
            colorlike(item)
            animateLike(item.isLike)
        }

        binding.collectIcon.setOnClickListener(){
            if(item.isCollect){
                item.isCollect = false
                binding.collectCount.text = (item.collectcount).toString()
            }else{
                item.isCollect = true
                binding.collectCount.text = (item.collectcount + 1).toString()
            }
            colorcollect(item)
            animateCollect(item.isCollect)
        }

        binding.shareIcon.setOnClickListener(){
            animateShare()
            val data = item
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "我在开眼发现一个很棒的视频，快来看看吧！\n${data.playuri.replace("http", "https")}")
            requireContext().startActivity(Intent.createChooser(intent, "分享到"))

        }
    }

    private fun colorcollect(item: invokeitem) {
        var color = 0
        if (item.isCollect){
            color = ContextCompat.getColor(context, R.color.yellow)
        }else{
            color = ContextCompat.getColor(context, R.color.white)
        }
        binding.collectIcon.setColorFilter(color);
    }

    fun colorlike(item: invokeitem){
        var color = 0
        if(item.isLike){
            color = ContextCompat.getColor(context, R.color.red)
        }else{
            color = ContextCompat.getColor(context, R.color.white)
        }
        binding.likeIcon.setColorFilter(color);
    }

    private fun animateShare(){
        val scaleX = ObjectAnimator.ofFloat(binding.shareIcon, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.shareIcon, "scaleY", 1f, 1.2f, 1f)
        AnimatorSet().apply {
            playTogether(scaleX, scaleY)
            start()
        }
    }

    private fun animateLike(isLiked: Boolean) {
        val startColor = if (isLiked) {
            ContextCompat.getColor(context, R.color.white)
            } else {
                ContextCompat.getColor(context, R.color.red)
            }

        val endColor = if (isLiked) {
            ContextCompat.getColor(context, R.color.red)
            } else {
                ContextCompat.getColor(context, R.color.white)
            }

        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor).apply {
            duration = 300
            addUpdateListener { animator ->
                binding.likeIcon.setColorFilter(animator.animatedValue as Int)
            }
        }
        val scaleX = ObjectAnimator.ofFloat(binding.likeIcon, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.likeIcon, "scaleY", 1f, 1.2f, 1f)

        if (isLiked) {
            showLikeParticle()
        }

        AnimatorSet().apply {
            playTogether(colorAnimation, scaleX, scaleY)
            start()
        }
    }

    private fun showLikeParticle() {
        val location = IntArray(2)
        binding.likeIcon.getLocationOnScreen(location)

        showExplosionAnimation(
            location[0] + binding.likeIcon.width / 2,
            location[1] + binding.likeIcon.height / 2
        )
    }
    private fun showExplosionAnimation(x: Int, y: Int) {
        val rootView = (context as Activity).window.decorView as ViewGroup
        repeat(20) {
            val particle = ImageView(context).apply {
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
            ContextCompat.getColor(context, R.color.white)
        } else {
            ContextCompat.getColor(context, R.color.yellow)
        }

        val endColor = if (isCollect) {
            ContextCompat.getColor(context, R.color.yellow)
        } else {
            ContextCompat.getColor(context, R.color.white)
        }

        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor).apply {
            duration = 300
            addUpdateListener { animator ->
                binding.collectIcon.setColorFilter(animator.animatedValue as Int)
            }
        }
        val scaleX = ObjectAnimator.ofFloat(binding.collectIcon, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.collectIcon, "scaleY", 1f, 1.2f, 1f)

        if (isCollect) {
            showCollectParticle()
        }

        AnimatorSet().apply {
            playTogether(colorAnimation, scaleX, scaleY)
            start()
        }
    }

    private fun showCollectParticle() {
        val location = IntArray(2)
        binding.collectIcon.getLocationOnScreen(location)

        showExplosionAnimation(
            location[0] + binding.collectIcon.width / 2,
            location[1] + binding.collectIcon.height / 2
        )
    }

    override fun onPause() {
        super.onPause()
        exoPlayer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}