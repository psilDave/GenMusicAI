package com.psil.genmusicai.data.data

import com.google.gson.annotations.SerializedName

data class MusicRequest(
    @SerializedName("prompt")
    val prompt: String,
    @SerializedName("make_instrumental")
    val makeInstrumental: Boolean = false,
    @SerializedName("wait_audio")
    val waitAudio: Boolean = false
)
