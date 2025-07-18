package com.example.recordkeeper.ui.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recordkeeper.ui.theme.RecordKeeperTheme

@Composable
fun RatingStars(
    rating: Int,
    modifier: Modifier = Modifier,
    starColor: Color = Color(0xFFFFD700)
) {
    Row(modifier = modifier) {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < rating) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Star ${index + 1}",
                tint = if (index < rating) starColor else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingStarsPreview() {
    RecordKeeperTheme {
        Row {
            RatingStars(rating = 5)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingStarsVariousPreview() {
    RecordKeeperTheme {
        androidx.compose.foundation.layout.Column {
            RatingStars(rating = 1)
            RatingStars(rating = 2)
            RatingStars(rating = 3)
            RatingStars(rating = 4)
            RatingStars(rating = 5)
        }
    }
}