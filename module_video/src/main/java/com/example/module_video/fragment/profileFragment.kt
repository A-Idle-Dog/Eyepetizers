package com.example.module_video.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.module_video.databinding.FragmentProfileBinding
import com.example.module_video.model.invokeitem


class profileFragment : Fragment() {
    private lateinit var invokeData : invokeitem

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            invokeData = it.getParcelable("data")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){
        binding.contentTextView.text = "        "+invokeData.des
        binding.tag1TextView.text = "#"+invokeData.tags
        binding.titleTextView.text = invokeData.title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(data: invokeitem) : profileFragment {
            val args = Bundle()
            args.putParcelable("data",data)
            val fragment = profileFragment()
            fragment.arguments = args
            return fragment
        }

    }
}