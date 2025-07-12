package com.example.recordkeeper.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SuggestionTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    getSuggestions: suspend (String) -> List<String>,
    modifier: Modifier = Modifier
) {
    var suggestions by remember { mutableStateOf(emptyList<String>()) }
    var showSuggestions by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(value, isFocused) {
        if (value.isNotBlank() && value.length >= 1 && isFocused) {
            delay(300) // デバウンス
            scope.launch {
                try {
                    suggestions = getSuggestions(value)
                    showSuggestions = suggestions.isNotEmpty() && isFocused
                } catch (e: Exception) {
                    suggestions = emptyList()
                    showSuggestions = false
                }
            }
        } else {
            suggestions = emptyList()
            showSuggestions = false
        }
    }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                    if (!focusState.isFocused) {
                        showSuggestions = false
                    }
                }
        )

        DropdownMenu(
            expanded = showSuggestions && suggestions.isNotEmpty(),
            onDismissRequest = { 
                showSuggestions = false
                isFocused = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            suggestions.take(5).forEach { suggestion ->
                DropdownMenuItem(
                    text = { 
                        Text(
                            text = suggestion,
                            style = MaterialTheme.typography.bodyMedium
                        ) 
                    },
                    onClick = {
                        onValueChange(suggestion)
                        showSuggestions = false
                        isFocused = false
                    }
                )
            }
        }
    }
}