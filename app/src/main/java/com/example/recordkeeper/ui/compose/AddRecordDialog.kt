package com.example.recordkeeper.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.recordkeeper.R
import com.example.recordkeeper.data.entity.LiveRecord
import com.example.recordkeeper.data.entity.MovieRecord
import com.example.recordkeeper.data.entity.RamenRecord
import com.example.recordkeeper.ui.theme.RecordKeeperTheme
import com.example.recordkeeper.util.DateUtils
import com.example.recordkeeper.viewmodel.RecordViewModel

@Composable
fun AddRecordDialog(
    viewModel: RecordViewModel,
    selectedTab: Int,
    onDismiss: () -> Unit
) {
    var currentTab by remember { mutableIntStateOf(selectedTab) }
    
    val tabs = listOf(
        stringResource(R.string.live_tab),
        stringResource(R.string.movie_tab),
        stringResource(R.string.ramen_tab)
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.add_record),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                
                ScrollableTabRow(
                    selectedTabIndex = currentTab,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    edgePadding = 0.dp
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = currentTab == index,
                            onClick = { currentTab = index },
                            text = { 
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.labelMedium,
                                    maxLines = 1
                                )
                            },
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                when (currentTab) {
                    0 -> AddLiveRecordForm(
                        viewModel = viewModel,
                        onDismiss = onDismiss
                    )
                    1 -> AddMovieRecordForm(
                        viewModel = viewModel,
                        onDismiss = onDismiss
                    )
                    2 -> AddRamenRecordForm(
                        viewModel = viewModel,
                        onDismiss = onDismiss
                    )
                }
            }
        }
    }
}

@Composable
fun AddLiveRecordForm(
    viewModel: RecordViewModel,
    onDismiss: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var artist by remember { mutableStateOf("") }
    var venue by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(DateUtils.formatDate(DateUtils.getCurrentDate())) }
    var rating by remember { mutableIntStateOf(5) }
    var memo by remember { mutableStateOf("") }
    
    Column {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("ライブタイトル") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = artist,
            onValueChange = { artist = it },
            label = { Text("アーティスト") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = venue,
            onValueChange = { venue = it },
            label = { Text("会場") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        DatePickerField(
            value = date,
            onValueChange = { date = it },
            label = "日付",
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "評価",
            style = MaterialTheme.typography.bodyMedium
        )
        
        InteractiveRatingBar(
            rating = rating,
            onRatingChanged = { rating = it }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = memo,
            onValueChange = { memo = it },
            label = { Text("メモ") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Button(
                onClick = {
                    if (title.isNotBlank() && artist.isNotBlank() && venue.isNotBlank() && date.isNotBlank()) {
                        viewModel.insertLiveRecord(
                            LiveRecord(
                                title = title,
                                artist = artist,
                                venue = venue,
                                date = date,
                                rating = rating,
                                memo = memo
                            )
                        )
                        onDismiss()
                    }
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
fun AddMovieRecordForm(
    viewModel: RecordViewModel,
    onDismiss: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var theater by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(DateUtils.formatDate(DateUtils.getCurrentDate())) }
    var rating by remember { mutableIntStateOf(5) }
    var memo by remember { mutableStateOf("") }
    
    Column {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("映画タイトル") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = director,
            onValueChange = { director = it },
            label = { Text("監督") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = theater,
            onValueChange = { theater = it },
            label = { Text("映画館") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        DatePickerField(
            value = date,
            onValueChange = { date = it },
            label = "日付",
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "評価",
            style = MaterialTheme.typography.bodyMedium
        )
        
        InteractiveRatingBar(
            rating = rating,
            onRatingChanged = { rating = it }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = memo,
            onValueChange = { memo = it },
            label = { Text("メモ") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Button(
                onClick = {
                    if (title.isNotBlank() && director.isNotBlank() && theater.isNotBlank() && date.isNotBlank()) {
                        viewModel.insertMovieRecord(
                            MovieRecord(
                                title = title,
                                director = director,
                                theater = theater,
                                date = date,
                                rating = rating,
                                memo = memo
                            )
                        )
                        onDismiss()
                    }
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
fun AddRamenRecordForm(
    viewModel: RecordViewModel,
    onDismiss: () -> Unit
) {
    var shopName by remember { mutableStateOf("") }
    var menuName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(DateUtils.formatDate(DateUtils.getCurrentDate())) }
    var rating by remember { mutableIntStateOf(5) }
    var memo by remember { mutableStateOf("") }
    
    Column {
        OutlinedTextField(
            value = shopName,
            onValueChange = { shopName = it },
            label = { Text("店名") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = menuName,
            onValueChange = { menuName = it },
            label = { Text("メニュー名") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("場所") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        DatePickerField(
            value = date,
            onValueChange = { date = it },
            label = "日付",
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "評価",
            style = MaterialTheme.typography.bodyMedium
        )
        
        InteractiveRatingBar(
            rating = rating,
            onRatingChanged = { rating = it }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = memo,
            onValueChange = { memo = it },
            label = { Text("メモ") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Button(
                onClick = {
                    if (shopName.isNotBlank() && menuName.isNotBlank() && location.isNotBlank() && date.isNotBlank()) {
                        viewModel.insertRamenRecord(
                            RamenRecord(
                                shopName = shopName,
                                menuName = menuName,
                                location = location,
                                date = date,
                                rating = rating,
                                memo = memo
                            )
                        )
                        onDismiss()
                    }
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddLiveRecordFormPreview() {
    RecordKeeperTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "記録を追加",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                AddLiveRecordFormPreviewContent()
            }
        }
    }
}

@Composable
private fun AddLiveRecordFormPreviewContent() {
    var title by remember { mutableStateOf("") }
    var artist by remember { mutableStateOf("") }
    var venue by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(DateUtils.formatDate(DateUtils.getCurrentDate())) }
    var rating by remember { mutableIntStateOf(5) }
    var memo by remember { mutableStateOf("") }
    
    Column {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("ライブタイトル") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = artist,
            onValueChange = { artist = it },
            label = { Text("アーティスト") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = venue,
            onValueChange = { venue = it },
            label = { Text("会場") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        DatePickerField(
            value = date,
            onValueChange = { date = it },
            label = "日付",
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "評価",
            style = MaterialTheme.typography.bodyMedium
        )
        
        InteractiveRatingBar(
            rating = rating,
            onRatingChanged = { rating = it }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = memo,
            onValueChange = { memo = it },
            label = { Text("メモ") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick = { },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "キャンセル",
                    style = MaterialTheme.typography.labelLarge
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "保存",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
