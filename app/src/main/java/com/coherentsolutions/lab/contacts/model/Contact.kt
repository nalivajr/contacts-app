package com.coherentsolutions.lab.contacts.model

import java.io.Serializable

data class Contact(
    var id: Int,
    var name: String
) : Serializable
