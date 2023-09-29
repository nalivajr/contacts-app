package com.coherentsolutions.lab.contacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.coherentsolutions.lab.contacts.databinding.FragmentContactDetailsBinding

class ContactsDetailsFragment : Fragment() {

    private val args by navArgs<ContactsDetailsFragmentArgs>()

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding: FragmentContactDetailsBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.name.text = args.contact.name
    }

}