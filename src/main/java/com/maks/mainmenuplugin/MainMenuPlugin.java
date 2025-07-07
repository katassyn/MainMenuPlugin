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

    // Debugging flag - set to 1 for debug mode, 0 for production
    private int debuggingFlag = 0;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] MainMenuPlugin has been enabled with debugging active!");
        } else {
            getLogger().info("MainMenuPlugin has been enabled!");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("MainMenuPlugin has been disabled!");
    }

    // Handle player joining
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] Player joined: " + player.getName());
        }
        giveMenuItem(player);
    }

    // Give the Main Menu item to the player in the top-right inventory slot
    private void giveMenuItem(Player player) {
        ItemStack menuItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = menuItem.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Main Menu");
        menuItem.setItemMeta(meta);
        // Place the item in slot 17 (top-right corner of main inventory)
        player.getInventory().setItem(17, menuItem);
        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] Gave menu item to player: " + player.getName());
        }
    }

    // Prevent dropping the Main Menu item
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (isMainMenuItem(item)) {
            event.setCancelled(true);
            if (debuggingFlag == 1) {
                getLogger().info("[DEBUG] Prevented player from dropping main menu item: " + event.getPlayer().getName());
            }
        }
    }

    // Handle clicks in the inventory
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        // Pobierz przedmiot w slocie klikniętym
        ItemStack currentItem = event.getCurrentItem();
        // Pobierz przedmiot na kursorze
        ItemStack cursorItem = event.getCursor();

        // Sprawdzamy oba przypadki: nether star w slocie lub na myszce
        if (isMainMenuItem(currentItem) || isMainMenuItem(cursorItem)) {
            // Anulujemy interakcję
            event.setCancelled(true);

            // Jeśli kliknięto w nether star w ekwipunku (a nie na kursorze), otwieramy menu
            if (isMainMenuItem(currentItem)) {
                if (debuggingFlag == 1) {
                    getLogger().info("[DEBUG] Player clicked main menu item: " + player.getName());
                }
                openCustomMenu(player);
            }

            // Zwracamy, aby przerwać dalsze przetwarzanie
            return;
        }

        // Obsługa kliknięć w customowym menu
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Main Menu")) {
            // Anulujemy interakcję w customowym menu
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem == null || !clickedItem.hasItemMeta()) {
                return;
            }

            String displayName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

            if (debuggingFlag == 1) {
                getLogger().info("[DEBUG] Player " + player.getName() + " clicked menu item: " + displayName);
            }

            // Logika akcji w menu
            switch (displayName) {
                case "Trinkets":
                    player.closeInventory();
                    player.performCommand("trinkets");
                    break;
                case "Ingredient Pouch":
                    player.closeInventory();
                    player.performCommand("ingredient_pouch");
                    break;
                case "Skill Tree":
                    player.closeInventory();
                    player.performCommand("skilltree");
                    break;
                case "Ascendancy Skill Tree":
                    player.closeInventory();
                    player.performCommand("skilltree2");
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
                if (debuggingFlag == 1) {
                    getLogger().info("[DEBUG] Player used suicide command: " + player.getName());
                }
                return true;
            } else {
                sender.sendMessage("Only players can execute this command.");
                return true;
            }
        } else if (label.equalsIgnoreCase("menu")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (debuggingFlag == 1) {
                    getLogger().info("[DEBUG] Player used menu command: " + player.getName());
                }
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

        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] Opening custom menu for player: " + player.getName());
        }

        // /trinkets
        ItemStack trinkets = new ItemStack(Material.FLOWER_BANNER_PATTERN);
        ItemMeta trinketsMeta = trinkets.getItemMeta();
        trinketsMeta.setDisplayName(ChatColor.GOLD + "Trinkets");
        trinketsMeta.setLore(new java.util.ArrayList<String>());
        trinkets.setItemMeta(trinketsMeta);
        customMenu.setItem(0, trinkets);
        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] Added Trinkets item to slot 0");
        }

        // /ingredient_pouch
        ItemStack ingredientPouch = new ItemStack(Material.BUNDLE); // Use another material if BUNDLE is unavailable
        ItemMeta pouchMeta = ingredientPouch.getItemMeta();
        pouchMeta.setDisplayName(ChatColor.GREEN + "Ingredient Pouch");
        ingredientPouch.setItemMeta(pouchMeta);
        customMenu.setItem(1, ingredientPouch);
        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] Added Ingredient Pouch item to slot 1");
        }

        // /skilltree - NEW
        ItemStack skillTree = new ItemStack(Material.KNOWLEDGE_BOOK);
        ItemMeta skillTreeMeta = skillTree.getItemMeta();
        skillTreeMeta.setDisplayName(ChatColor.AQUA + "Skill Tree");
        skillTree.setItemMeta(skillTreeMeta);
        customMenu.setItem(3, skillTree);
        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] Added Skill Tree item to slot 3");
        }

        // /skilltree 2 - NEW
        ItemStack ascendancyTree = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta ascendancyTreeMeta = ascendancyTree.getItemMeta();
        ascendancyTreeMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Ascendancy Skill Tree");
        ascendancyTree.setItemMeta(ascendancyTreeMeta);
        customMenu.setItem(4, ascendancyTree);
        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] Added Ascendancy Skill Tree item to slot 4");
        }

        // /premium_menu - MOVED from slot 3 to slot 5
        ItemStack vipMenu = new ItemStack(Material.ANVIL);
        ItemMeta vipMeta = vipMenu.getItemMeta();
        vipMeta.setDisplayName(ChatColor.BLUE + "Premium Menu");
        vipMenu.setItemMeta(vipMeta);
        customMenu.setItem(5, vipMenu);
        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] Added Premium Menu item to slot 5");
        }

        // /trash - MOVED from slot 5 to slot 7
        ItemStack trash = new ItemStack(Material.HOPPER);
        ItemMeta trashMeta = trash.getItemMeta();
        trashMeta.setDisplayName(ChatColor.GRAY + "Trash");
        trash.setItemMeta(trashMeta);
        customMenu.setItem(7, trash);
        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] Added Trash item to slot 7");
        }

        // /suicide
        ItemStack suicide = new ItemStack(Material.BONE);
        ItemMeta suicideMeta = suicide.getItemMeta();
        suicideMeta.setDisplayName(ChatColor.RED + "Suicide");
        suicide.setItemMeta(suicideMeta);
        customMenu.setItem(8, suicide);
        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] Added Suicide item to slot 8");
        }

        // Fill remaining slots with a filler item
        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        for (int i = 0; i < customMenu.getSize(); i++) {
            if (customMenu.getItem(i) == null) {
                customMenu.setItem(i, filler);
                if (debuggingFlag == 1) {
                    getLogger().info("[DEBUG] Added filler item to slot " + i);
                }
            }
        }

        player.openInventory(customMenu);
        if (debuggingFlag == 1) {
            getLogger().info("[DEBUG] Menu opened successfully for player: " + player.getName());
        }
    }
}
