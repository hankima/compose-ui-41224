package loc.example.composeuidemo20240411app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import loc.example.composeuidemo20240411app.ui.theme.ComposeUIDemo20240411AppTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUIDemo20240411AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Greeting(
    heading: String,
    name: String,
    onChange: (String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = heading,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            TextField(value = name, onValueChange = onChange, placeholder = {
                Text(text = if (name.isBlank()) "Your name" else "")
            })
            Spacer(modifier = Modifier.width(8.dp))
            Button(modifier = Modifier.align(Alignment.CenterVertically), onClick = onClick) {
                Text(text = "Done")
            }
        }
    }
}

@Composable
fun Content() {
    var heading by rememberSaveable { mutableStateOf("Welcome. What is your name?") }
    var name by rememberSaveable { mutableStateOf("") }
    Greeting(heading = heading, name, onChange = {
        Log.d(TAG, "onCreate: $it")
        name = it
    }, onClick = {
        heading = "Hello $name"
    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeUIDemo20240411AppTheme {
        Greeting(heading = "Android", name = "", onChange = {}, onClick = {})
    }
}