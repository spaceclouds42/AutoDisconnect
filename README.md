### Installation
Installing Fabric Mod Loader:
1. [Download the Fabric installer from the download page.](https://fabricmc.net/use/)
2. Open the installer. In the window you need to configure the mapping and loader version (as per advice in the MultiMC section) and the install location (the default should be fine on most platforms).
*NOTE You need to enable snapshots in order to make the installer show mapping options for Minecraft snapshots.*
3. Press Install. A new game version and profile will be created in the launcher's menu, which you can now use to launch Fabric.
4. [Download the Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api/files)
5. Place the downloaded jar in your `.minecraft/mods/` folder

Installing AutoDisconnect
1. [Download Mod](https://github.com/SpaceClouds42/AutoDisconnect/releases/)
2. Place the downloaded jar in your `.minecraft/mods/` folder

### Mod Usage
Settings revert to defaults after every restart. Any time you are autodisconnected, the system that disconnected you is disabled (either player or health)

Player disconnect *(Disconnect when a player enters render distance)*
 - default: off
 - on: `/autodisconnect players true`
 - off: `/autodisconnect players false`

Health disconnect *(Disconnect when health falls below threshold)*
 - default: disabled, 10.0 threshold
 - on: `/autodisconnect health true`
 - off: `/autodisconnect health false`
 - set threshold: `/autodisconnect health 10` (sets threshold to 10 hp, which is half health)

### Issues
If you encounter any bugs, have a suggestion, or have any problems at all, you can contact me on discord at `SpaceClouds42#7337` or [submit an issue report](https://github.com/SpaceClouds42/AutoDisconnect/issues)
