package com.example.reviewnest_mvvm.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reviewnest_mvvm.ui.theme.Purple80
import com.example.reviewnest_mvvm.ui.theme.White

@Composable
fun ExpandableReviewText(
    text: String,
    minimizedMaxLines: Int = 3
) {
    var expanded by remember { mutableStateOf(false) }
    var textOverflows by remember { mutableStateOf(false) }

    Box {
        Text(
            text = text,
            maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { result ->
                textOverflows = result.hasVisualOverflow && !expanded
            },
            fontSize = 14.sp,
            color = White,
        )
        if (textOverflows && !expanded) {
            Text(
                text = "See more...",
                color = Purple80,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clickable { expanded = true }
                    .background(Color.Black)
                    .padding(horizontal = 10.dp, vertical = 2.dp)
            )
        } else if (expanded) {
            Text(
                text = "...See less",
                color = Purple80,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clickable { expanded = false }
                    .background(Color.Black.copy(alpha = 0.7f))
                    .padding(horizontal = 10.dp, vertical = 2.dp)
            )
        }
    }
}
