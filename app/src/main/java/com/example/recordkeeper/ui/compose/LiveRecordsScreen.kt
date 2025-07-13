package com.example.recordkeeper.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.recordkeeper.data.entity.LiveRecord
import com.example.recordkeeper.ui.theme.RecordKeeperTheme
import com.example.recordkeeper.viewmodel.RecordViewModel

@Composable
fun LiveRecordsScreen(viewModel: RecordViewModel) {
    val liveRecords by viewModel.liveRecords.collectAsState(initial = emptyList())
    
    if (liveRecords.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "まだライブの記録がありません",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(liveRecords) { record ->
                LiveRecordCard(
                    record = record,
                    onDelete = { viewModel.deleteLiveRecord(record) },
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun LiveRecordCard(
    record: LiveRecord,
    onDelete: () -> Unit,
    viewModel: RecordViewModel
) {
    var artistCount by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(record.artist) {
        if (record.artist.isNotBlank()) {
            scope.launch {
                // アーティストのライブ回数を取得
                artistCount = viewModel.getArtistLiveCount(record.artist)
            }
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = record.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Row {
                        Text(
                            text = record.artist,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f)
                        )
                        if (artistCount > 1) {
                            Text(
                                text = "${artistCount}回目",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Text(
                        text = record.venue,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "削除",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = record.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                RatingStars(rating = record.rating)
            }
            
            if (record.memo.isNotEmpty()) {
                Text(
                    text = record.memo,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LiveRecordCardPreview() {
    RecordKeeperTheme {
        LiveRecordCard(
            record = LiveRecord(
                id = 1,
                title = "BABYMETAL WORLD TOUR 2024",
                artist = "BABYMETAL",
                venue = "東京ドーム",
                date = "2024-12-25",
                rating = 5,
                memo = "最高のライブでした！"
            ),
            onDelete = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LiveRecordsScreenEmptyPreview() {
    RecordKeeperTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "まだライブの記録がありません",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LiveRecordsScreenWithDataPreview() {
    RecordKeeperTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleLiveRecords) { record ->
                LiveRecordCard(
                    record = record,
                    onDelete = {},
                    viewModel = null!! // Preview用のダミー
                )
            }
        }
    }
}

private val sampleLiveRecords = listOf(
    LiveRecord(
        id = 1,
        title = "BABYMETAL WORLD TOUR 2024",
        artist = "BABYMETAL",
        venue = "東京ドーム",
        date = "2024-12-25",
        rating = 5,
        memo = "最高のライブでした！"
    ),
    LiveRecord(
        id = 2,
        title = "あいみょん全国ツアー",
        artist = "あいみょん",
        venue = "さいたまスーパーアリーナ",
        date = "2024-11-15",
        rating = 4,
        memo = "感動しました"
    ),
    LiveRecord(
        id = 3,
        title = "米津玄師 KICK BACK",
        artist = "米津玄師",
        venue = "横浜アリーナ",
        date = "2024-10-20",
        rating = 5,
        memo = ""
    )
)