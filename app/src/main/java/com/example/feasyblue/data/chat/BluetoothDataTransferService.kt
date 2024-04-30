package com.example.feasyblue.data.chat

import android.bluetooth.BluetoothSocket
import android.util.Log
import com.example.feasyblue.domain.chat.BluetoothMessage
import com.example.feasyblue.domain.chat.TransferFailedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException

class BluetoothDataTransferService(
    private val socket: BluetoothSocket
) {
    fun listenForIncomingMessages(): Flow<BluetoothMessage> {
        return flow {
            if (!socket.isConnected) {
                return@flow
            }
            val buffer = ByteArray(2048) // Increased buffer size
            while (true) {
                val byteCount = try {
                    socket.inputStream.read(buffer)
                } catch (e: IOException) {
                    throw TransferFailedException()
                }
                val messageBytes = buffer.copyOfRange(0, byteCount)
                val message = BluetoothMessage(
                    message = messageBytes,
                    senderName = "Server",
                    isFromLocalUser = false
                )
                emit(message)

                // Print the response to Logcat
                Log.d("BluetoothChat", "Received message: ${message.message.contentToString()}")
            }
        }.flowOn(Dispatchers.IO)
    }


    suspend fun sendMessage(message: ByteArray): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                socket.outputStream.write(message)
            } catch (e: IOException) {
                return@withContext false
            }

            true
        }
    }
}