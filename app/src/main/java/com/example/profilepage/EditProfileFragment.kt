package com.example.profilepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.profilepage.databinding.FragmentEditProfileBinding
import kotlinx.coroutines.runBlocking

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by activityViewModels()

    private lateinit var myPref: MySharedPreferences
    private lateinit var myDataStore: MyDataStore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myPref = MySharedPreferences(requireContext())
        myDataStore = MyDataStore(requireContext())
        init()
    }

    private fun init() {
        binding.button.setOnClickListener {
//            binding.textView.text = myPref.getData("name")
            runBlocking {
                binding.textView.text = myDataStore.getData("name")
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}