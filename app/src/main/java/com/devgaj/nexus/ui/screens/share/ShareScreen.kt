// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus.ui.screens.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

data class ChatMessage(
    val sender: String,
    val message: String,
    val isSystem: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareScreen() {
    var messageText by remember { mutableStateOf("") }
    var chatMessages by remember { mutableStateOf(listOf(
        ChatMessage("System", "Welcome to MeshLink.", true),
        ChatMessage("System", "Secure P2P Mesh Active.", true)
    )) }

    // Simulation of encryption/decryption key
    val secretKey = "NEXUS_OFFLINE_KEY"

    fun encrypt(text: String): String {
        return Base64.getEncoder().encodeToString(text.toByteArray())
    }

    fun decrypt(encoded: String): String? {
        return try {
            String(Base64.getDecoder().decode(encoded))
        } catch (e: Exception) {
            null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MeshLink", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary) },
                actions = {
                    IconButton(onClick = { /* Info */ }) {
                        Icon(Icons.Default.Info, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        },
        bottomBar = {
            Surface(tonalElevation = 2.dp) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        placeholder = { Text("Encrypted message...") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(24.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    FloatingActionButton(
                        onClick = {
                            if (messageText.isNotBlank()) {
                                val encrypted = encrypt(messageText)
                                // In a real app, 'encrypted' would be sent over P2P
                                val decrypted = decrypt(encrypted)
                                
                                chatMessages = chatMessages + ChatMessage("Me", decrypted ?: "Error decrypting message")
                                messageText = ""

                                // Simulate a response from "System" for demonstration
                                if (chatMessages.size % 3 == 0) {
                                    chatMessages = chatMessages + ChatMessage("System", "Error decrypting message", true)
                                }
                            }
                        },
                        shape = RoundedCornerShape(24.dp),
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(Icons.Default.Send, contentDescription = null, tint = Color.White)
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(chatMessages) { msg ->
                ChatBubble(msg)
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val isMe = message.sender == "Me"
    val isSystem = message.isSystem
    
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isMe) Alignment.End else Alignment.Start
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = when {
                isSystem -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                isMe -> MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                else -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
            },
            tonalElevation = if (isSystem) 0.dp else 1.dp
        ) {
            Text(
                text = "${message.sender}: ${message.message}",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                fontSize = 14.sp,
                color = if (isSystem) Color.LightGray else Color.White
            )
        }
    }
}
