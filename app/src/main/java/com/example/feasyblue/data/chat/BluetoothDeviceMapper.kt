package com.example.feasyblue.data.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.example.feasyblue.domain.chat.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}