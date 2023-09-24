fun main() {
    val levelingSystem = LevelingSystem() // Create an instance of the leveling system

    // Create two characters
    val player = Character("Player", 100, 20, 50)
    val enemy = Character("Enemy", 80, 15, 40)

    println("A wild $enemy appears! It's time for battle!")

    // Main game loop
    var isPlayerTurn = true
    while (player.health > 0 && enemy.health > 0) {
        if (isPlayerTurn) {
            // Player's turn
            println("Your turn, ${player.name}!")
            println("Choose your action: (1) Attack, (2) Cast Spell")
            val choice = readLine()?.toIntOrNull()

            when (choice) {
                1 -> {
                    player.attack(enemy)
                    if (enemy.health <= 0) {
                        val xpGain = Random.nextInt(10, 21)
                        player.gainExperience(xpGain)
                        levelingSystem.levelUp(player) // Check for level up
                    }
                }
                2 -> {
                    player.castSpell(enemy)
                    if (enemy.health <= 0) {
                        val xpGain = Random.nextInt(10, 21)
                        player.gainExperience(xpGain)
                        levelingSystem.levelUp(player) // Check for level up
                    }
                }
                else -> println("Invalid choice. Player loses a turn.")
            }
        } else {
            // Enemy's turn
            println("It's ${enemy.name}'s turn!")
            enemy.attack(player)
        }

        // Toggle turns
        isPlayerTurn = !isPlayerTurn
    }

    // Determine the winner
    if (player.health <= 0) {
        println("Game over! ${enemy.name} wins!")
    } else {
        println("Victory! ${player.name} wins!")
    }
}
