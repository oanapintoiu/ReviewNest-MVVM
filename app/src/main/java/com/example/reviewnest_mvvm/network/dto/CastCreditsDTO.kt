package com.example.reviewnest_mvvm.network.dto

import com.google.gson.annotations.SerializedName

data class CastCreditsDTO(
    @SerializedName("cast")
    val cast: List<CastMemberDTO>
)

data class CastMemberDTO(
    @SerializedName("name")
    val name: String,

    @SerializedName("profile_path")
    val profilePath: String?
)
