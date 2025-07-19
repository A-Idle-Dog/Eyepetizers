package com.example.module_video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.tabcontent.adapter = tabAdapter(requireActivity())
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