# EmberDeath v1.1.0

## Changes

- Added a detailed startup report with plugin, server, feature, and configuration information.
- Split the plugin implementation into dedicated configuration, listener, service, and utility packages.
- Added configurable punishment modes: `KICK` and permanent `BAN`.
- Added separate configurable kick and ban reasons.
- Preserved formatted colors and HEX colors on the ban disconnect screen.
- Added validation and safe fallbacks for invalid punishment modes and sound names.
