# MainMenuPlugin

**MainMenuPlugin** is a Minecraft server plugin that adds an interactive main menu accessible to players via a special item in their inventory.

![MainMenuPlugin Banner](https://via.placeholder.com/800x200?text=MainMenuPlugin)

## Features

- **Automatic Menu Item**: Upon joining the server, each player receives a Nether Star named **"Main Menu"** placed in the top-right slot of their inventory.
- **Interactive Menu**: Clicking on the **"Main Menu"** item opens a custom menu with various options.
- **Item Protection**: Players cannot drop or move the **"Main Menu"** item.
- **Available Menu Commands**:
  - **Trinkets**: Opens the Trinkets menu.
  - **Ingredient Pouch**: Opens the Ingredient Pouch.
  - **Skill Tree**: Opens the Skill Tree menu.
  - **Ascendancy Skill Tree**: Opens the Ascendancy Skill Tree menu.
  - **Premium Menu**: Access to the VIP premium menu.
  - **Trash**: Opens a trash bin for disposing of unwanted items.
  - **Suicide**: Allows the player to perform suicide.

## Screenshots

![Main Menu](https://via.placeholder.com/400x300?text=Main+Menu+Screenshot)
![Menu Items](https://via.placeholder.com/400x300?text=Menu+Items+Screenshot)

## Installation

1. **Download the Plugin**: Copy the `.jar` file of the plugin into your server's `plugins` folder.
2. **Restart the Server**: Restart your server or use the `/reload` command to load the plugin.
3. **Dependencies**: This plugin has a soft dependency on PlayerDataPlugin, which provides some of the commands used in the menu.

## Usage

- **Receiving the Menu Item**: Players automatically receive the **"Main Menu"** item upon joining the server.
- **Opening the Menu**:
  - Left-click or right-click the **"Main Menu"** item in your inventory.
  - Or use the command `/menu`.
- **Navigating the Menu**:
  - Click on the desired option in the menu to execute its corresponding action.
  - Available options:
    - **Trinkets**: Represented by a Flower Banner Pattern item
    - **Ingredient Pouch**: Represented by a Bundle item
    - **Skill Tree**: Represented by a Knowledge Book item
    - **Ascendancy Skill Tree**: Represented by an Enchanted Book item
    - **Premium Menu**: Represented by an Anvil item
    - **Trash**: Represented by a Hopper item
    - **Suicide**: Represented by a Bone item

## Commands

- `/menu` – Opens the main menu.
- `/skilltree` – Opens the Skill Tree menu (provided by PlayerDataPlugin).
- `/skilltree2` – Opens the Ascendancy Skill Tree menu (provided by PlayerDataPlugin).
- `/suicide` – Causes the player to commit suicide.

## Permissions

The plugin does not require any special permissions. All features are available to every player.

## Configuration

Currently, the plugin does not have a configuration file. All settings are default and defined within the plugin's code.

### Future Configuration Options

In future versions, we plan to add a configuration file with the following options:
- Customizable menu item (material and name)
- Customizable menu title
- Ability to enable/disable specific menu options
- Permission-based access to menu options

## Technical Details

- **API Version**: 1.20
- **Java Version**: 1.8
- **Debug Mode**: The plugin has a debugging flag that can be set in the code (currently active).
- **Dependencies**: 
  - Paper/Spigot 1.20+
  - PlayerDataPlugin (soft dependency)

## Troubleshooting

If the **"Main Menu"** item appears in the player's hand after clicking in the menu or if you encounter other unwanted effects:

- Ensure you are using the latest version of the plugin.
- Check if the server console logs any errors related to the plugin.
- Verify that all dependency plugins are installed and up to date.
- If the problem persists, report it in the Issues section on GitHub with detailed information.

### Common Issues

1. **Menu item disappears**: If the menu item disappears from the player's inventory, they can use the `/menu` command to open the menu and receive a new item.
2. **Commands not working**: Ensure that all dependency plugins are properly installed and that the commands exist on your server.
3. **Conflicts with other plugins**: If you experience conflicts with other plugins that modify player inventories, try adjusting the load order in your server configuration.

## Version History

### v1.0.0 (Current)
- Initial release
- Basic menu functionality
- Integration with PlayerDataPlugin

### Planned Features
- Configuration file
- Custom permissions
- More menu options
- Menu customization options

## Contribution

Any suggestions, bug reports, or pull requests are welcome! To contribute:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

- **Maks** - [GitHub Profile](https://github.com/yourusername)

## Acknowledgments

- Thanks to the Bukkit/Spigot/Paper community for their excellent API
- Special thanks to all contributors and testers
