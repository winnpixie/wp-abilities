package io.github.winnpixie.wpabilities.listeners;

import io.github.winnpixie.wpabilities.PlayerData;
import io.github.winnpixie.wpabilities.WPAbilitiesPlugin;
import io.github.winnpixie.wpabilities.abilities.impl.HollowPurpleAbility;
import io.github.winnpixie.wpabilities.abilities.impl.OculaserAbility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerActionListener implements Listener {
    private final WPAbilitiesPlugin plugin;

    public PlayerActionListener(WPAbilitiesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        ItemStack mainHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();

        if (offHand.getType() == Material.RED_WOOL && mainHand.getType() == Material.BLUE_WOOL) {
            HollowPurpleAbility hollowPurple = PlayerData.getData(player)
                    .abilityManager.getAbility(HollowPurpleAbility.class);

            if (hollowPurple.isReady()) hollowPurple.toggle(player);
            return;
        }

        OculaserAbility oculaser = PlayerData.getData(player)
                .abilityManager.getAbility(OculaserAbility.class);
        if (oculaser.isReady()) oculaser.toggle(player);
    }
}
