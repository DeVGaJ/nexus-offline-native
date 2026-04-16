// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus.ui.screens.games

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SudokuGame(onBack: () -> Unit) {
    // A simple pre-defined puzzle (0 represents empty)
    val initialBoard = listOf(
        5, 3, 0, 0, 7, 0, 0, 0, 0,
        6, 0, 0, 1, 9, 5, 0, 0, 0,
        0, 9, 8, 0, 0, 0, 0, 6, 0,
        8, 0, 0, 0, 6, 0, 0, 0, 3,
        4, 0, 0, 8, 0, 3, 0, 0, 1,
        7, 0, 0, 0, 2, 0, 0, 0, 6,
        0, 6, 0, 0, 0, 0, 2, 8, 0,
        0, 0, 0, 4, 1, 9, 0, 0, 5,
        0, 0, 0, 0, 8, 0, 0, 7, 9
    )

    var board by remember { mutableStateOf(initialBoard.map { if (it == 0) "" else it.toString() }) }
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    var message by remember { mutableStateOf("Fill the grid!") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sudoku", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(message, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
        
        Spacer(modifier = Modifier.height(16.dp))

        // Sudoku Grid
        Box(modifier = Modifier.aspectRatio(1f).border(2.dp, MaterialTheme.colorScheme.outline)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(9),
                modifier = Modifier.fillMaxSize()
            ) {
                items(81) { index ->
                    val isFixed = initialBoard[index] != 0
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .border(0.5.dp, MaterialTheme.colorScheme.outlineVariant)
                            .background(
                                when {
                                    selectedIndex == index -> MaterialTheme.colorScheme.primaryContainer
                                    isFixed -> MaterialTheme.colorScheme.surfaceVariant
                                    else -> MaterialTheme.colorScheme.surface
                                }
                            )
                            .clickable { if (!isFixed) selectedIndex = index },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = board[index],
                            fontSize = 18.sp,
                            fontWeight = if (isFixed) FontWeight.Bold else FontWeight.Normal,
                            color = if (isFixed) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Number Input
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            (1..9).forEach { num ->
                Button(
                    onClick = {
                        selectedIndex?.let { idx ->
                            val newBoard = board.toMutableList()
                            newBoard[idx] = num.toString()
                            board = newBoard
                        }
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(36.dp)
                ) {
                    Text(num.toString())
                }
            }
        }
        
        Button(
            onClick = {
                selectedIndex?.let { idx ->
                    val newBoard = board.toMutableList()
                    newBoard[idx] = ""
                    board = newBoard
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Clear Cell")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                // Simple check (doesn't validate full rules, just completeness for now)
                if (board.any { it.isEmpty() }) {
                    message = "Still some empty cells!"
                } else {
                    message = "Looks good! (Validation logic pending)"
                }
            }) {
                Text("Check")
            }
            
            OutlinedButton(onClick = onBack) {
                Text("Back")
            }
        }
    }
}
