package com.example.feasyblue.data.chat

import com.example.feasyblue.domain.chat.BluetoothMessage

fun BluetoothMessage.toByteArray(): ByteArray {
    val nameBytes = senderName.encodeToByteArray()
    val messageBytes = message
    return byteArrayOf(nameBytes.size.toByte()) + nameBytes + messageBytes
}
