package com.example.module_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.example.module_home.ViewModel.viewmodel.RecommendViewModel
import com.example.module_home.adapter.homerecommendadapter
import com.example.module_home.databinding.FragmentHomeRecommendBinding
import kotlinx.coroutines.launch

class homeRecommendFragment : Fragment() {

    private var _binding: FragmentHomeRecommendBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    private val recommendadapter = homerecommendadapter()

    private val viewModel by lazy { ViewModelProvider(this).get(RecommendViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeRecommendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rrecyclerview.apply {
            adapter = recommendadapter
            layoutManager = LinearLayoutManager(requireContext())
            recommendadapter.onBannerItemCLick = {
                ARouter.getInstance()
                    .build("/module_video/VideoActivity")
                    .withString("playuri",it.data.playUrl)
                    .withString("cover",it.data.cover.feed)
                    .withInt("uid",it.data.id)
                    .withString("title",it.data.title)
                    .withString("author",it.data.author.name)
                    .withString("authoricon",it.data.author.icon)
                    .withString("tags",it.data.tags[0].name)
                    .withString("des",it.data.description)
                    .withInt("likecount",it.data.consumption.collectionCount)
                    .withInt("collectcount",it.data.consumption.realCollectionCount)
                    .withBoolean("isLike",it.data.played)
                    .withBoolean("isCollect",it.data.collected)
                    .withString("shareUrl",it.data.webUrl.raw)
                    .navigation()
            }
            recommendadapter.onFollewItemClick = {
                ARouter.getInstance()
                    .build("/module_video/VideoActivity")
                    .withString("playuri",it.data.content.data.playUrl)
                    .withString("cover",it.data.content.data.cover.feed)
                    .withInt("uid",it.data.content.data.id)
                    .withString("title",it.data.content.data.title)
                    .withString("author",it.data.content.data.author.name)
                    .withString("authoricon",it.data.content.data.author.icon)
                    .withString("tags",it.data.content.data.tags[0].name)
                    .withString("des",it.data.content.data.description)
                    .withInt("likecount",it.data.content.data.consumption.collectionCount)
                    .withInt("collectcount",it.data.content.data.consumption.realCollectionCount)
                    .withBoolean("isLike",it.data.content.data.played)
                    .withBoolean("isCollect",it.data.content.data.collected)
                    .withString("shareUrl",it.data.content.data.webUrl.raw)
                    .navigation()
            }
        }
        lifecycleScope.launch {
            viewModel.getPagingData().collect { rpagingData ->
                recommendadapter.submitData(rpagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            homeRecommendFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}