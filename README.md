# FactionsChat
 FactionsChat is a very lightweight Bukkit chat plugin for Factions
 
## Features

- Support for ally and faction chat
- Customizable messages and channel format
- Very lightweight

## Permissions

The following permissions are used:

- factionschat.chat: allows the user to talk and switch between channels
- factionschat.reload: allows the user to reload the plugin configuration
- factionschat.*: gives all the permissions to the user

## Compiling and building

This project uses Maven for building. However, some dependencies (Factions plugin...) have to be supplied manually because I wasn't able to find any active repository for them. Those are:

- Factions.jar
- MassiveCore.jar

Which can be found and downloaded here: https://www.spigotmc.org/resources/factions3-for-1-13.63602/

Put these two files in a folder named "lib" in the root directory, so Maven can link them. After that, just type  `mvn install`

### License

GPL v3