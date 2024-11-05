package com.psil.genmusicai.data.data

import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("audio_url")
    val audioUrl: String,
    @SerializedName("video_url")
    val videoUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("model_name")
    val modelName: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("prompt")
    val prompt: String,
    @SerializedName("gpt_description_prompt")
    val gptDescriptionPrompt: String,
    @SerializedName("lyric")
    val lyric: String,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("type")
    val type: String
) {
    fun getFormattedMessage(): String {
        return "$title\n[imagem]$imageUrl\n[link]$audioUrl"
    }
}
