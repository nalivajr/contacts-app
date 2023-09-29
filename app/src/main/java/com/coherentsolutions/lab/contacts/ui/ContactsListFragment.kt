package com.coherentsolutions.lab.contacts.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.coherentsolutions.lab.contacts.databinding.FragmentContactsListBinding
import com.coherentsolutions.lab.contacts.viewmodel.ContactsListViewModel

class ContactsListFragment : Fragment() {

    private val viewModel by viewModels<ContactsListViewModel>()
    private val contactsAdapter = ContactsAdapter {
        findNavController().navigate(ContactsListFragmentDirections.contactsListToDetails(it))
    }

    private var _binding: FragmentContactsListBinding? = null
    private val binding: FragmentContactsListBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentContactsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (requireActivity().checkSelfPermission(android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            viewModel.loadContacts()
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS), 0)
        }

        binding.contactsList.adapter = contactsAdapter
        binding.contactsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.contacts.observe(viewLifecycleOwner) {
            contactsAdapter.setItems(it)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 0) {
            val index = permissions.indexOfFirst { it == android.Manifest.permission.READ_CONTACTS }
            if (index != -1 && grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                viewModel.loadContacts()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}