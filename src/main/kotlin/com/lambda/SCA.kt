package com.lambda

import com.lambda.client.LambdaMod
import com.lambda.client.plugin.api.Plugin
import com.lambda.client.util.threads.BackgroundJob
import com.lambda.huds.GoodPlayers
import com.lambda.huds.BadPlayers
import com.lambda.util.SCAUtil

internal object SCA : Plugin() {

    override fun onLoad() {
        // Load any modules, commands, or HUD elements here
        hudElements.add(GoodPlayers)
        hudElements.add(BadPlayers)

        bgJobs.add(BackgroundJob("MinuteReload", 30000L) {
            LambdaMod.LOG.info("Hey, Its been a minute, reloading the SCA Players")
            SCAUtil.reload()
        })
    }

    override fun onUnload() {
        // Here you can unregister threads etc...
    }
}