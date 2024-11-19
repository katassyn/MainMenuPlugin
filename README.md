# MainMenuPlugin

**MainMenuPlugin** is a Minecraft server plugin that adds an interactive main menu accessible to players via a special item in their inventory.

## Features

- **Automatic Menu Item**: Upon joining the server, each player receives a Nether Star named **"Main Menu"** placed in the top-right slot of their inventory.
- **Interactive Menu**: Clicking on the **"Main Menu"** item opens a custom menu with various options.
- **Item Protection**: Players cannot drop or move the **"Main Menu"** item.
- **Available Menu Commands**:
  - **Trinkets**: Opens the Trinkets menu.
  - **Ingredient Pouch**: Opens the Ingredient Pouch.
  - **Premium Menu**: Access to the VIP premium menu.
  - **Trash**: Opens a trash bin for disposing of unwanted items.
  - **Suicide**: Allows the player to perform suicide.

## Installation

1. **Download the Plugin**: Copy the `.jar` file of the plugin into your server's `plugins` folder.
2. **Restart the Server**: Restart your server or use the `/reload` command to load the plugin.

## Usage

- **Receiving the Menu Item**: Players automatically receive the **"Main Menu"** item upon joining the server.
- **Opening the Menu**:
  - Left-click or right-click the **"Main Menu"** item in your inventory.
  - Or use the command `/menu`.
- **Navigating the Menu**:
  - Click on the desired option in the menu to execute its corresponding action.
  - Available options:
    - **Trinkets**
    - **Ingredient Pouch**
    - **Premium Menu**
    - **Trash**
    - **Suicide**

## Commands

- `/menu` – Opens the main menu.
- `/suicide` – Causes the player to commit suicide.

## Permissions

The plugin does not require any special permissions. All features are available to every player.

## Configuration

Currently, the plugin does not have a configuration file. All settings are default and defined within the plugin's code.

## Troubleshooting

If the **"Main Menu"** item appears in the player's hand after clicking in the menu or if you encounter other unwanted effects:

- Ensure you are using the latest version of the plugin.
- Check if the server console logs any errors related to the plugin.
- If the problem persists, report it in the Issues section on GitHub with detailed information.

## Contribution

Any suggestions, bug reports, or pull requests are welcome!

## License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

## Author

- **Maks**
