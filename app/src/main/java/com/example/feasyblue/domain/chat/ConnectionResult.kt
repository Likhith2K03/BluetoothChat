package com.example.feasyblue.domain.chat

sealed interface ConnectionResult {
    data object ConnectionEstablished: ConnectionResult
    data class Error(val message: String): ConnectionResult
}