package com.example.recordkeeper.ui.compose

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp

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