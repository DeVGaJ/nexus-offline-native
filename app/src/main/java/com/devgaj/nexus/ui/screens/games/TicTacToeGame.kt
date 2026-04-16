// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus.ui.screens.games

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicTacToeGame(onBack: () -> Unit) {
    var board by remember { mutableStateOf(List(9) { "" }) }
    var xIsNext by remember { mutableStateOf(true) }
    var winner by remember { mutableStateOf<String?>(null) }
    var isVsCpu by remember { mutableStateOf(true) }

    fun checkWinner(b: List<String>): String? {
        val lines = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )
        for (line in lines) {
            if (b[line[0]].isNotEmpty() && b[line[0]] == b[line[1]] && b[line[0]] == b[line[2]]) {
                return b[line[0]]
            }
        }
        if (b.all { it.isNotEmpty() }) return "Draw"
        return null
    }

    fun makeCpuMove(currentBoard: List<String>) {
        if (winner != null) return
        val emptyIndices = currentBoard.indices.filter { currentBoard[it].isEmpty() }
        if (emptyIndices.isNotEmpty()) {
            val cpuMoveIndex = emptyIndices.random()
            val newBoard = currentBoard.toMutableList()
            newBoard[cpuMoveIndex] = "O"
            board = newBoard
            xIsNext = true
            winner = checkWinner(newBoard)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tic Tac Toe", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Single Player (CPU)")
            Switch(checked = isVsCpu, onCheckedChange = { 
                isVsCpu = it 
                board = List(9) { "" }
                xIsNext = true
                winner = null
            })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = when {
                winner == "Draw" -> "It's a Draw!"
                winner != null -> if (isVsCpu && winner == "O") "CPU Won!" else "Winner: $winner"
                else -> if (isVsCpu && !xIsNext) "CPU is thinking..." else "Next Player: ${if (xIsNext) "X" else "O"}"
            },
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Game Board
        Column {
            for (row in 0 until 3) {
                Row {
                    for (col in 0 until 3) {
                        val index = row * 3 + col
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .border(1.dp, MaterialTheme.colorScheme.outline)
                                .clickable(enabled = board[index].isEmpty() && winner == null && (if (isVsCpu) xIsNext else true)) {
                                    val newBoard = board.toMutableList()
                                    newBoard[index] = if (xIsNext) "X" else "O"
                                    board = newBoard
                                    winner = checkWinner(newBoard)
                                    
                                    if (winner == null) {
                                        xIsNext = !xIsNext
                                        if (isVsCpu && !xIsNext) {
                                            // Delay CPU move slightly for better UX
                                            makeCpuMove(newBoard)
                                        }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = board[index],
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (board[index] == "X") Color.Cyan else Color.Magenta
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                board = List(9) { "" }
                xIsNext = true
                winner = null
            }) {
                Text("Reset Game")
            }
            
            OutlinedButton(onClick = onBack) {
                Text("Back to Arcade")
            }
        }
    }
}
