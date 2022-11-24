package com.lambda.huds

import com.lambda.SCA
import com.lambda.client.event.SafeClientEvent
import com.lambda.client.plugin.api.PluginLabelHud
import com.lambda.util.SCAUtil
import com.lambda.client.util.color.ColorHolder

internal object BadPlayers: PluginLabelHud(
    name = "SCA Enemies",
    category = Category.MISC,
    description = "Shows who is a SCA Enemy",
    pluginMain = SCA
) {
    private var reload by setting("Reload", false)
    private val shortrange by setting("Short Range", 25, 1..250, 1)
    private val mediumrange by setting("Medium Range", 50, 1..250, 1)
    private val longrange by setting("Long Range", 100, 1..250, 1)
    private val shortRangeColor by setting("Short Range Color", ColorHolder(255, 0, 0))
    private val mediumRangeColor by setting("Medium Range Color", ColorHolder(255, 165, 0))
    private val longRangeColor by setting("Long Range Color", ColorHolder(255, 255, 0))
    private val playersLength = 0


    override fun SafeClientEvent.updateText() {

        displayText.add("SCA Enemies Near By:")

        displayText.currentLine++
        
        for (player in mc.world.playerEntities) {
            if (SCAUtil.isEnemy(player.name)) {

                if (mc.player.getDistance(player) <= shortrange) {
                    displayText.add(SCAUtil.getName(player.name), shortRangeColor)
                } else if (mc.player.getDistance(player) <= mediumrange) {
                    displayText.add(SCAUtil.getName(player.name), mediumRangeColor)
                } else if (mc.player.getDistance(player) <= longrange) {
                    displayText.add(SCAUtil.getName(player.name), longRangeColor)
                } else {
                    displayText.add(SCAUtil.getName(player.name), longRangeColor)
                }
                
                displayText.currentLine++
            }
        }        
    }
}