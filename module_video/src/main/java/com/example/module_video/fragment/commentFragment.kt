package com.example.module_video.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.module_video.Adapter.commentAdapter
import com.example.module_video.R
import com.example.module_video.ViewModel.commentViewModel
import com.example.module_video.ViewModel.relatedViewModel
import com.example.module_video.apiService.commentService
import com.example.module_video.databinding.FragmentCommentBinding
import com.example.module_video.model.Comment
import com.example.module_video.model.invokeitem

class commentFragment : Fragment() {
    private lateinit var invokeData: invokeitem
    private val commentViewModel by lazy { ViewModelProvider(this).get(commentViewModel::class.java) }
    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding!!
    private lateinit var commentList: List<Comment>
    private lateinit var adapter: commentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            invokeData = it.getParcelable("invokeData")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapterWithPlaceholder()
        observeViewModel()
        initViewModel()
    }

    fun initViewModel(){
        commentViewModel.getcomment(invokeData)
    }

    fun observeViewModel(){
        commentViewModel.commentList.observe(viewLifecycleOwner){ comments ->
            comments?.let {
                commentList = it
                Log.d("commentFragment", "Received comment list with size: ${commentList.size}")
                if(commentList.isNotEmpty()) {
                    Log.d("commentFragment", "Received comment list with size: ${commentList[1].data?.message}")
                }

                adapter.submitList(commentList)
                checkDataState(commentList)
            }
        }
    }

    private fun initAdapterWithPlaceholder() {
        adapter = commentAdapter()
        binding.recyclerView.apply {
            this.adapter = this@commentFragment.adapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
        adapter.submitList(emptyList())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun checkDataState(ccomm: List<Comment>) {
        if (ccomm.size == 0) {
            binding.viewSwitcher.setDisplayedChild(0);
        } else {
            binding.viewSwitcher.setDisplayedChild(1);
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(data: invokeitem) : commentFragment {
            val args = Bundle()
            args.putParcelable("invokeData", data)
            val fragment = commentFragment()
            fragment.arguments = args
            return fragment
        }
    }
}