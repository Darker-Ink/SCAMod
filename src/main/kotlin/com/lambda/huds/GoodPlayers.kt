package com.lambda.huds

import com.lambda.SCA
import com.lambda.client.event.SafeClientEvent
import com.lambda.client.plugin.api.PluginLabelHud
import com.lambda.util.SCAUtil
import com.lambda.client.util.color.ColorHolder

internal object GoodPlayers: PluginLabelHud(
    name = "SCA Members",
    category = Category.MISC,
    description = "Shows who is a SCA Member",
    pluginMain = SCA
) {
    private
    var reload by setting("Reload", false)

    override fun SafeClientEvent.updateText() {
        if (reload) {
            SCAUtil.reload()
            reload = false;
        }

        displayText.add("SCA Members Near By:")

        displayText.currentLine++

        for (player in mc.world.playerEntities) {
            if (SCAUtil.isFriendly(player.name)) {
                displayText.add(SCAUtil.getName(player.name), ColorHolder(0, 255, 0))
                displayText.currentLine++
            }
        }
    }
}