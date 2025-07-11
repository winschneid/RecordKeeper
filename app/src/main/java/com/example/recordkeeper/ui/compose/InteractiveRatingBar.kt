package com.example.recordkeeper.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recordkeeper.ui.theme.RecordKeeperTheme

@Composable
fun InteractiveRatingBar(
    rating: Int,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    starColor: Color = Color(0xFFFFD700)
) {
    Row(modifier = modifier) {
        repeat(5) { index ->
            val isSelected = index < rating
            Icon(
                imageVector = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Star ${index + 1}",
                tint = if (isSelected) starColor else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onRatingChanged(index + 1) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InteractiveRatingBarPreview() {
    RecordKeeperTheme {
        var rating by remember { mutableIntStateOf(3) }
        
        Column {
            Text(
                text = "現在の評価: $rating",
                modifier = Modifier.padding(16.dp)
            )
            InteractiveRatingBar(
                rating = rating,
                onRatingChanged = { rating = it },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}