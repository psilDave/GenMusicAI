package com.psil.genmusicai.data.authenticator

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class Authenticator() : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return response.request.newBuilder().header(
            "Authorization",
            ""
        ).build()
    }
}