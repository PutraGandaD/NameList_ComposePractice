package com.putragandad.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.putragandad.composepractice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListNamesWithState(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListNamesWithState(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }

    var names by remember {
        mutableStateOf(listOf<String>())
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.weight(1f) // 0dp, occupy the rest of space
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if(name.isNotBlank()) {
                        names += name
                        name = ""
                    }
                }
            ) {
                Text(text = "Add")
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(names) { currentName ->
                Text(text = currentName, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = 1.dp
                )
            }
        }
    }
}

@Composable
fun ButtonClickWithState(modifier: Modifier = Modifier) {
    var count = remember { // use remember so it won't set the initial value to 0 again when recomposed
        mutableStateOf(0)
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.value.toString(), // recomposed if state "count" changed
            fontSize = 30.sp
        )
        Button(onClick = {
            count.value++
        }) {
            Text(text = "Click me + ${count.value}") // also recomposed if state changed
        }
    }
}

@Composable
fun GreetingColumn(name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize() // fill our parent
            //.size(400.dp) (for fixed size)
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            fontSize = 30.sp
        )
        Text(
            text = "Whatever",
            modifier = modifier
        )
    }
}

@Composable
fun GreetingRow(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .size(400.dp)
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            fontSize = 30.sp
        )
        Text(
            text = "Whatever",
            modifier = modifier
        )
    }
}

@Composable
fun GreetingImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = null,
        modifier = modifier
            .background(Color.Red)
    )
}

@Composable
fun GreetingBox(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(400.dp)
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
                .align(Alignment.BottomEnd),
            fontSize = 30.sp
        )
        Text(
            text = "Whatever",
            modifier = modifier
        )
    }
}

@Composable
fun GreetingLazyColumn(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(10) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                modifier = modifier.size(400.dp)
            )
        }
    }
}

@Composable
fun GreetingLazyRow(name: String, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxSize()
    ) {
        items(10) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                modifier = modifier.size(400.dp)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ComposePracticeTheme {
//        GreetingLazyColumn()
//    }
//}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    ComposePracticeTheme {
        ListNamesWithState()
    }
}