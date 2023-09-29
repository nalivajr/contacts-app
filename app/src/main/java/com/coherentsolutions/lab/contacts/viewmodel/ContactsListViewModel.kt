package com.coherentsolutions.lab.contacts.viewmodel

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coherentsolutions.lab.contacts.App
import com.coherentsolutions.lab.contacts.model.Contact

class ContactsListViewModel : ViewModel() {

    val contacts = MutableLiveData<ArrayList<Contact>>()

    fun loadContacts() {
        val cursor = App.application.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        if (cursor?.moveToFirst() != true) {
            contacts.value = ArrayList()
            return
        }

        var loadedList = ArrayList<Contact>()

        do {
            val id = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID))
            val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
            loadedList.add(Contact(id, name))
        } while(cursor.moveToNext())

        contacts.value = loadedList
    }
}