import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.themedataaplication.navigation.Routes
import com.example.themedataaplication.view.LoginPage
import com.example.themedataaplication.view.SmartHomeDashboard


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.HOME) {
            HomePage(navController = navController)
        }
        composable(Routes.LOGIN) {
            LoginPage(navController = navController)
        }
        composable(Routes.SMARTHOMEDASH) {
            SmartHomeDashboard(navController = navController)
        }
        composable(Routes.SMARTPATIENT) {
            PatientDetailsScreen(navController = navController)
        }
    }
}
