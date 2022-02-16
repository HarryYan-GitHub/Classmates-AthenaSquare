package contact.info.harry.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import contact.info.harry.domain.model.Student

@Composable
fun StudentItem(
    student: Student
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Filled.Person,
            contentDescription = "Icon",
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
        )
        Column {
            Text(
                text = student.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                RadioButton(selected = student.numBadges > 0, onClick = { })
                Spacer(modifier = Modifier.width(2.dp))
                RadioButton(selected = student.numBadges > 1, onClick = { })
                Spacer(modifier = Modifier.width(2.dp))
                RadioButton(selected = student.numBadges > 2, onClick = { })
                Spacer(modifier = Modifier.width(2.dp))
                RadioButton(selected = student.numBadges > 3, onClick = { })
                Spacer(modifier = Modifier.width(2.dp))
                RadioButton(selected = student.numBadges > 4, onClick = { })
                Spacer(modifier = Modifier.width(2.dp))
            }
        }
    }
    Spacer(modifier = Modifier.height(6.dp))
}