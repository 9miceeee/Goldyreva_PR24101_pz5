
package com.example.atelier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.atelier.ui.theme.ATELIERTheme

data class ArchiveItem(
    val title: String,
    val year: String,
    val description: String
)

private val archiveItems = listOf(
    ArchiveItem(
        "Alexander McQueen",
        "1999",
        "Коллекция No. 13 — один из знаковых показов дизайнера, " +
                "известный сочетанием моды, искусства и технологий."
    ),
    ArchiveItem(
        "Christian Dior",
        "1947",
        "New Look изменил послевоенную моду благодаря " +
                "выразительному силуэту и акценту на линии талии."
    ),
    ArchiveItem(
        "Yves Saint Laurent",
        "1966",
        "Коллекция Mondrian объединила высокую моду " +
                "и идеи современного искусства."
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ATELIERTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AtelierScreen()
                }
            }
        }
    }
}

@Composable
fun AtelierScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "ATELIER",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "THE FASHION ARCHIVE",
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "История высокой моды",
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = "Архив знаковых коллекций, дизайнеров " +
                            "и показов, изменивших мир моды.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            item {
                Image(
                    painter = painterResource(id = R.drawable.runway),
                    contentDescription = "Исторический показ высокой моды",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                Text(
                    text = "Из архива",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Нажми на коллекцию, чтобы прочитать описание.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            items(archiveItems) { item ->
                ArchiveCard(item = item)
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "ATELIER · DIGITAL FASHION ARCHIVE",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
        }
    }
}

@Composable
fun ArchiveCard(item: ArchiveItem) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = item.year,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = if (expanded) "Свернуть ▲" else "Подробнее ▼",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
