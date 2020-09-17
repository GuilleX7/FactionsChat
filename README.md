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

This project uses Maven for building. However, some dependencies (Factions and MassiveCore) have to be supplied in a local repository. This proccess in completely automated.

For compiling, just type `mvn clean` in order to install dependencies in the local Maven repository, and then type `mvn install`.

### License

GPL v3