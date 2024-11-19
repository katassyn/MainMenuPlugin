package com.maks.mainmenuplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class MainMenuPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("MainMenuPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MainMenuPlugin has been disabled!");
    }

    // Handle player joining
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        giveMenuItem(player);
    }

    // Give the Main Menu item to the player in the top-right inventory slot
    private void giveMenuItem(Player player) {
        ItemStack menuItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = menuItem.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Main Menu");
        menuItem.setItemMeta(meta);

        // Place the item in slot 17 (top-right corner of main inventory)
        if (!player.getInventory().contains(menuItem)) {
            player.getInventory().setItem(17, menuItem);
        }
    }

    // Prevent dropping the Main Menu item
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (isMainMenuItem(item)) {
            event.setCancelled(true);
        }
    }

    // Handle clicks in the inventory
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        // Check if the event involves the Main Menu item
        ItemStack currentItem = event.getCurrentItem();
        ItemStack cursorItem = event.getCursor();

        if (isMainMenuItem(currentItem) || isMainMenuItem(cursorItem)) {
            event.setCancelled(true);
            event.setCursor(null); // Dodaj tę linię
            event.getView().setCursor(null); // Opcjonalnie, aby upewnić się, że kursor jest wyczyszczony

            // If the player clicked on the Main Menu item, open the custom menu
            if (isMainMenuItem(currentItem)) {
                openCustomMenu(player);
            }
        }

        // Handle clicks in the custom menu
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Main Menu")) {
            event.setCancelled(true);
            event.setCursor(null); // Dodaj tę linię
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem == null || !clickedItem.hasItemMeta()) return;

            String displayName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

            switch (displayName) {
                case "Trinkets":
                    player.closeInventory();
                    player.performCommand("trinkets");
                    break;
                case "Ingredient Pouch":
                    player.closeInventory();
                    player.performCommand("ingredient_pouch");
                    break;
                case "Premium Menu":
                    player.closeInventory();
                    player.performCommand("vipmenu");
                    break;
                case "Trash":
                    player.closeInventory();
                    player.performCommand("trash");
                    break;
                case "Suicide":
                    player.closeInventory();
                    player.performCommand("suicide");
                    break;
                default:
                    break;
            }
        }
    }


    // Helper method to check if an item is the Main Menu item
    private boolean isMainMenuItem(ItemStack item) {
        if (item != null && item.hasItemMeta()) {
            String displayName = ChatColor.stripColor(item.getItemMeta().getDisplayName());
            return displayName.equals("Main Menu");
        }
        return false;
    }

    // Implement commands
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("suicide")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setHealth(0.0);
                return true;
            } else {
                sender.sendMessage("Only players can execute this command.");
                return true;
            }
        } else if (label.equalsIgnoreCase("menu")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                openCustomMenu(player);
                return true;
            } else {
                sender.sendMessage("Only players can execute this command.");
                return true;
            }
        }
        return false;
    }

    // Create and open the custom menu
    private void openCustomMenu(Player player) {
        Inventory customMenu = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Main Menu");

        // /trinkets
        ItemStack trinkets = new ItemStack(Material.FLOWER_BANNER_PATTERN);
        ItemMeta trinketsMeta = trinkets.getItemMeta();
        trinketsMeta.setDisplayName(ChatColor.GOLD + "Trinkets");
        trinkets.setItemMeta(trinketsMeta);
        customMenu.setItem(0, trinkets);

        // /ingredient_pouch
        ItemStack ingredientPouch = new ItemStack(Material.BUNDLE); // Use another material if BUNDLE is unavailable
        ItemMeta pouchMeta = ingredientPouch.getItemMeta();
        pouchMeta.setDisplayName(ChatColor.GREEN + "Ingredient Pouch");
        ingredientPouch.setItemMeta(pouchMeta);
        customMenu.setItem(1, ingredientPouch);

        // /premium_menu
        ItemStack vipMenu = new ItemStack(Material.ANVIL);
        ItemMeta vipMeta = vipMenu.getItemMeta();
        vipMeta.setDisplayName(ChatColor.BLUE + "Premium Menu");
        vipMenu.setItemMeta(vipMeta);
        customMenu.setItem(3, vipMenu);

        // /trash
        ItemStack trash = new ItemStack(Material.HOPPER);
        ItemMeta trashMeta = trash.getItemMeta();
        trashMeta.setDisplayName(ChatColor.GRAY + "Trash");
        trash.setItemMeta(trashMeta);
        customMenu.setItem(5, trash);

        // /suicide
        ItemStack suicide = new ItemStack(Material.BONE);
        ItemMeta suicideMeta = suicide.getItemMeta();
        suicideMeta.setDisplayName(ChatColor.RED + "Suicide");
        suicide.setItemMeta(suicideMeta);
        customMenu.setItem(8, suicide);

        // Fill remaining slots with a filler item
        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        for (int i = 0; i < customMenu.getSize(); i++) {
            if (customMenu.getItem(i) == null) {
                customMenu.setItem(i, filler);
            }
        }

        player.openInventory(customMenu);
    }
}
