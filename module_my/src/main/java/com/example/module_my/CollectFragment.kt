package com.example.module_my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.example.data.AppDatabase
import com.example.module_my.Adapter.CollectAdapter
import com.example.module_my.ViewModel.CollectViewModel
import com.example.module_my.ViewModel.CollectViewModelFactory
import com.example.module_my.ViewModel.FavoritesViewModel
import com.example.module_my.ViewModel.FavoritesViewModelFactory
import com.example.module_my.databinding.FragmentCollectfragmentBinding

class CollectFragment : Fragment() {
    private var _binding: FragmentCollectfragmentBinding? = null
    private val binding get() = _binding!!
    private var param1: String? = null
    private var param2: String? = null
    private val madapter : CollectAdapter = CollectAdapter()
    private lateinit var database : AppDatabase
    private lateinit var viewModel: CollectViewModel

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
        _binding = FragmentCollectfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = AppDatabase.getInstance(requireContext())
        val factory = CollectViewModelFactory(database.collectVideoDao())
        viewModel = ViewModelProvider(this, factory).get(CollectViewModel::class.java)
        initAdapter()
        upData()
    }

    fun initAdapter(){
        binding.crecyclerView.apply {
            adapter = madapter
            layoutManager = GridLayoutManager(requireContext(),3)
            madapter.onItemClick = {
                ARouter.getInstance()
                    .build("/module_video/VideoActivity")
                    .withString("playuri",it.palyurl)
                    .withString("cover",it.cover)
                    .withInt("uid",it.videoId)
                    .withString("title",it.title)
                    .withString("author",it.author)
                    .withString("authoricon",it.authoricon)
                    .withString("tags",it.tags)
                    .withString("des",it.des)
                    .withInt("likecount",it.likecount)
                    .withInt("collectcount",it.collectcount)
                    .withBoolean("isLike",it.isLike)
                    .withBoolean("isCollect",it.isCollect)
                    .withString("shareUrl",it.shareUrl)
                    .navigation()
            }
        }
    }

    fun upData(){
        viewModel.collectVideo.observe(viewLifecycleOwner){
            (binding.crecyclerView.adapter as CollectAdapter).submitList(it)
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
            CollectFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}