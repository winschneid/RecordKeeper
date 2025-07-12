package com.example.recordkeeper.ui.compose

import android.widget.CalendarView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.recordkeeper.ui.theme.RecordKeeperTheme
import com.example.recordkeeper.util.DateUtils
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar

@Composable
fun DatePickerField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    var showCalendar by remember { mutableStateOf(false) }
    
    // Convert string to LocalDate for display
    val displayValue = if (value.isNotBlank()) {
        DateUtils.parseDate(value)?.let { date ->
            "${date.year}年${date.monthValue}月${date.dayOfMonth}日"
        } ?: value
    } else {
        ""
    }
    
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = displayValue,
            onValueChange = { },
            label = { Text(label) },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "日付を選択"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        
        // 透明なクリック領域をOutlinedTextFieldの上に配置
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { showCalendar = true }
        )
    }
    
    if (showCalendar) {
        Dialog(onDismissRequest = { showCalendar = false }) {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "日付を選択",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    AndroidView(
                        factory = { context ->
                            CalendarView(context).apply {
                                // Set current date if value exists
                                value.takeIf { it.isNotBlank() }?.let { dateString ->
                                    DateUtils.parseDate(dateString)?.let { localDate ->
                                        val calendar = Calendar.getInstance().apply {
                                            set(localDate.year, localDate.monthValue - 1, localDate.dayOfMonth)
                                        }
                                        date = calendar.timeInMillis
                                    }
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) { calendarView ->
                        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                            onValueChange(DateUtils.formatDate(selectedDate))
                            showCalendar = false
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = { showCalendar = false },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "キャンセル",
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DatePickerFieldPreview() {
    RecordKeeperTheme {
        var date by remember { mutableStateOf("") }
        DatePickerField(
            value = date,
            onValueChange = { date = it },
            label = "日付を選択"
        )
    }
}