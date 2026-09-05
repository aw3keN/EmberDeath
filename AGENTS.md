# EmberDeath Agent Instructions

## OpenCode Skill

- Use the `paper-plugin-development` skill for plugin development, debugging, and release work.
- The skill contains the reusable Paper/Gradle workflow and the versioning rules below.

## Versioning

- Always increment the plugin version for every code, configuration, resource, or build change.
- Keep the version consistent in `build.gradle`, generated `plugin.yml`, and any release metadata.
- Use semantic versioning in the `MAJOR.MINOR.PATCH` format.
- Do not reuse an existing version number for a new change.

## Version Changelogs

- Create one Markdown changelog file for every version.
- Name the file `CHANGELOG-vX.Y.Z.md`, for example `CHANGELOG-v1.2.0.md`.
- Add the version's changes to its own file; do not overwrite previous version changelogs.
- Write changelogs in English, matching the language used by the plugin configuration and startup messages.

## Release Workflow

- Before finishing any change, update the version and create the matching changelog file.
- Build the plugin after the version update.
- Confirm the generated JAR name and `plugin.yml` version match the new version.
