package com.example.data.storage

import com.example.data.storage.models.Tokens

interface TokensStorage {
    fun save(tokens: Tokens)

    fun get(): Tokens

    fun delete()

}