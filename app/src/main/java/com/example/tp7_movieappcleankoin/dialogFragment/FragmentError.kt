package com.example.tp7_movieappcleankoin.dialogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.tp7_movieappcleankoin.databinding.ErrorFragmentBinding

class FragmentError : DialogFragment(){
    companion object{
        fun newInstance() : FragmentError{
            return FragmentError()
        }
    }
    private lateinit var binding: ErrorFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showsDialog = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ErrorFragmentBinding.inflate(inflater, container, false)
        //val view: View = inflater.inflate(R.layout.error_fragment,container,false)
        val view = binding.root

        binding.buttonBack.setOnClickListener{
            dismiss()
        }
        return view
    }
}