# BetterJump 
### BetterJump - Elevate your gameplay with customizable double jumps and jump boosts!

<p>Use it to enhance your lobby or reward your loyal players. The choice is yours on how to utilize it!</p> <br>

<img src="https://github.com/user-attachments/assets/cc49022f-244f-448c-a065-7cf15f0999b2" width="550" /> <br> <br> 
✅ Fully Customizable: Adjust jump velocities, particles, and cooldowns. <br>
✅ Dynamic Jump Boosts: Customizable particle effects and velocities. <br>
✅ Stylish Double Jumps: Configurable cooldowns and visual effects. <br>
✅ Advanced Cooldown Management: Efficient cooldown systems with permission options. <br>
✅ Configurable Sound Effects: Custom sounds for jumps and boosts. <br>
✅ World Whitelisting: Control jump abilities in specific worlds. <br>
✅ Permission-Based Controls: Manage who can use jumps and bypass cooldowns. <br> <br>

<img src="https://github.com/user-attachments/assets/ff6cc097-19f8-4abf-aaa7-5fed23ff160a" width="550" /> <br> <br>
<details>
<summary>Open <bold>config.yml</bold></summary>

```
# BetterJump - Made by Aurorus
# Support: https://aurocode.com/dc

# General settings for the BetterJump plugin
settings:
  # Enable or disable the double jump feature
  enableDoubleJump: true  # Set to true to allow players to double jump, false to disable

  # Enable or disable the jump boost feature
  enableJumpBoost: false  # Set to true to allow players to use jump boost, false to disable

  # List of worlds where double jump and jump boost are enabled
  # Uncomment and add your world names to limit the functionality to specific worlds
  worlds:
  #   - "world"
  #   - "world_nether"
  #   - "world_the_end"

  # Double Jump settings
  doubleJump:
    # Cooldown time in seconds between double jumps (set to 0 to disable cooldown)
    cooldown: 5

    # The vertical velocity applied to the player when performing a double jump (set in blocks)
    velocity: 1.0

    # Sound settings for double jump
    sound:
      enableSound: true                   # Set to true to enable sounds for double jump
      soundOnJump: "ENTITY_FIREWORK_ROCKET_LAUNCH" # The sound that plays when a player double jumps

    # Particle settings for double jump
    particle:
      enableParticles: true  # Set to true to enable particle effects on double jump
      particleType: "SPELL_MOB"  # Type of particle that will be displayed
      particleCount: 10  # Number of particles spawned when double jump occurs

  # Jump Boost settings
  jumpBoost:
    # Cooldown time in seconds between jump boosts (set to 0 to disable cooldown)
    cooldown: 5

    # The vertical boost percentage applied during a jump boost
    velocity: 1.0

    # Sound settings for jump boost
    sound:
      enableSound: true  # Set to true to enable sounds for jump boost
      soundOnJump: "ENTITY_FIREWORK_ROCKET_LAUNCH"  # The sound that plays when jump boost is used

    # Particle settings for jump boost
    particle:
      enableParticles: true  # Set to true to enable particle effects for jump boost
      particleType: "SPELL_MOB"  # Type of particle to be shown when jump boost is used
      particleCount: 5  # Number of particles spawned on jump boost

# Permissions settings
permissions:
  # Permission required to use the double jump feature
  useDoubleJump: "betterjump.use.doublejump"  # Players with this permission can use double jump

  # Permission required to use the jump boost feature
  useJumpBoost: "betterjump.use.jumpboost"    # Players with this permission can use jump boost

  # Permission to bypass cooldown restrictions for both double jump and jump boost
  bypassCooldown: "betterjump.bypass.cooldown"  # Players with this permission bypass cooldowns
```
</details>
<br> 
<img src="https://github.com/user-attachments/assets/1bdc44f3-d30d-418a-a91c-46234a9b5ea1" width="550" /> <br> <br>
📌 For support, detailed instructions, and customization tips, visit our <a href="https://aurocode.com/dc">Discord server</a>.
