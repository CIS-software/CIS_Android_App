package first.android.cis.data.storage

import first.android.cis.data.storage.models.Tokens

interface TokensStorage {
    fun save(tokens: Tokens)

    fun get(): Tokens

    fun delete()

}