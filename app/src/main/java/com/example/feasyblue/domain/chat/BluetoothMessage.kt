package com.example.feasyblue.domain.chat

data class BluetoothMessage(
    val message: ByteArray,
    val senderName: String,
    val isFromLocalUser: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BluetoothMessage

        if (!message.contentEquals(other.message)) return false
        if (senderName != other.senderName) return false
        if (isFromLocalUser != other.isFromLocalUser) return false

        return true
    }

    override fun hashCode(): Int {
        var result = message.contentHashCode()
        result = 31 * result + senderName.hashCode()
        result = 31 * result + isFromLocalUser.hashCode()
        return result
    }
}
