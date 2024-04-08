import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DetailScreen(navController: NavController){
    Column ( modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold, color = Color.Red)
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Go to Top News Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(){
    val mockNavController = rememberNavController()
    DetailScreen(mockNavController)
}