package com.example.profilepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.profilepage.databinding.FragmentEditProfileBinding
import com.example.profilepage.databinding.FragmentProfileBinding
import kotlinx.coroutines.runBlocking

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by activityViewModels()

    private lateinit var myPref: MySharedPreferences
    private lateinit var myDataStore: MyDataStore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
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
            myPref.saveData("name", binding.editText.text.toString())

            runBlocking {
                myDataStore.saveData("name", binding.editText.text.toString())
            }

            binding.editText.setText("")
        }

        binding.navigateButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}