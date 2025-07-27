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
import com.example.data.Bean.FavoriteVideo
import com.example.module_my.Adapter.FavoriteAdapter
import com.example.module_my.ViewModel.FavoritesViewModel
import com.example.module_my.ViewModel.FavoritesViewModelFactory
import com.example.module_my.databinding.FragmentLikefragmentBinding

class LikeFragment : Fragment() {
    private var _binding: FragmentLikefragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: AppDatabase
    private lateinit var viewModel: FavoritesViewModel
    private var madapter: FavoriteAdapter = FavoriteAdapter()
    private var list:List<FavoriteVideo> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLikefragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = AppDatabase.getInstance(requireContext())
        val dao = database.favoriteVideoDao()
        val factory = FavoritesViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(FavoritesViewModel::class.java)
        initAdapter()
        viewModel.favoriteVideos.observe(viewLifecycleOwner){
            list = it
            (binding.likerecyclerView.adapter as FavoriteAdapter).submitList(it)
            switcher(list)
        }

    }

    fun initAdapter(){
        binding.likerecyclerView.apply {
            adapter = madapter
            layoutManager = GridLayoutManager(context,3)
            madapter.onItemClick = {it,position ->
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
                    .withInt("source",1)
                    .withInt("currentPosition",position)
                    .navigation()
            }
        }
    }


    fun switcher(list: List<FavoriteVideo>){
        if(list.size==0){
            binding.seitcher.setDisplayedChild(0)
        }else{
            binding.seitcher.setDisplayedChild(1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}