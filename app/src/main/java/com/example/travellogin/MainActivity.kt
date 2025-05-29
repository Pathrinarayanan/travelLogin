package com.example.travellogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travellogin.ui.theme.TravelLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val controller = rememberNavController()
            TravelLoginTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(controller, startDestination = "onboarding", Modifier.fillMaxSize() ){
                        composable("onboarding") {
                            Onboarding(controller)
                        }
                        composable("login"){
                            LoginScreen(controller)
                        }
                        composable("signup"){
                            SignUpScreen(controller)
                        }
                    }

                }
            }
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Onboarding(controller: NavController){
    val items = listOf(
        OnboardingItem("Discover the world with us", R.drawable.onboarding_1),
        OnboardingItem("To the most famous places of the world", R.drawable.onboarding_2),
        OnboardingItem("With cheap and affordable prices", R.drawable.onboarding3)
    )
    var currentPage by remember { mutableStateOf(0) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp, horizontal = 20.dp)
    ){
        AnimatedContent(
            targetState = currentPage,
            modifier =Modifier,
            transitionSpec = {
                slideInHorizontally { it } + fadeIn() with slideOutHorizontally{-it} + fadeOut()
            }
        ) { page ->
            Column {
                Image(
                    painter = painterResource(items[page].img),
                    contentDescription = null,
                    Modifier.size(350.dp, 400.dp)
                )
                Text(
                    items[page].title,
                    Modifier.padding(top = 20.dp),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 50.sp
                )
            }
        }
        Progress(currentPage)
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                Box(
                    Modifier
                        .height(45.dp)
                        .width(80.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Black)
                        .clickable {
                            if (currentPage < items.lastIndex) {
                                currentPage++;
                            } else {
                                controller.navigate("login")
                            }
                        }
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        Modifier.size(30.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }


@Composable
fun SignUpScreen(controller: NavHostController) {
    var passwordVisibility  by remember{mutableStateOf(false)}
    Column (Modifier
        .fillMaxSize()
        .padding(vertical = 50.dp, horizontal = 20.dp),
        horizontalAlignment  = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(R.drawable.welcome),
            contentDescription = null,
            Modifier.size(200.dp, 175.dp)
        )
        Text(
            "Get Started", Modifier.padding(top = 30.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 40.sp
        )
        Text(
            "by creating a free account.",
            Modifier.padding(top =4.dp),
            fontSize = 18.sp,
        )
        OutlinedTextField(
            "",
            onValueChange = {

            },
            Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            label = {
                Text("Full name")
            },
            trailingIcon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = null,
                    Modifier,
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xffe6e6e6),
                unfocusedContainerColor = Color(0xffe6e6e6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        OutlinedTextField(
            "",
            onValueChange = {

            },
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            label = {
                Text("Valid email")
            },
            trailingIcon = {
                Icon(
                    Icons.Filled.MailOutline,
                    contentDescription = null,
                    Modifier,
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xffe6e6e6),
                unfocusedContainerColor = Color(0xffe6e6e6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        OutlinedTextField(
            "",
            onValueChange = {

            },
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            label = {
                Text("Phone Number")
            },
            trailingIcon = {
                Icon(
                    Icons.Filled.PhoneAndroid,
                    contentDescription = null,
                    Modifier,
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xffe6e6e6),
                unfocusedContainerColor = Color(0xffe6e6e6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        OutlinedTextField(
            "",
            onValueChange = {

            },
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            label = {
                Text("Strong Password")
            },
            visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val  icon = if(passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                Icon(
                    icon,
                    contentDescription = null,
                    Modifier.clickable{
                        passwordVisibility = !passwordVisibility
                    },
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xffe6e6e6),
                unfocusedContainerColor = Color(0xffe6e6e6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Row (
            Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                Modifier,
                verticalAlignment = Alignment.CenterVertically
            ){
                CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
                    Checkbox(false, onCheckedChange = {})
                }
                Text("By checking the box you agree to our Terms and Conditions", Modifier.padding(start =10.dp), fontSize = 11.sp)
            }
        }

        Box(
            Modifier
                .padding(top = 20.dp)
                .height(50.dp)
                .wrapContentWidth()
                .background(Color.Black, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ){
            Text("DONE", Modifier
                .padding(horizontal = 100.dp, vertical = 10.dp),
                fontSize = 18.sp,
                color  = Color.White, fontWeight = FontWeight.Bold)
        }
        Row(Modifier.padding(top =10.dp)){
            Text("Already a member?", Modifier, color = Color.Black)
            Text(" Login", Modifier.padding(start = 5.dp).clickable{
                controller.navigate("login")
            }
                , color = Color(0xffff3951))

        }

    }
}

@Composable
fun LoginScreen(controller: NavHostController) {
    var passwordVisibility  by remember{mutableStateOf(false)}
    Column (Modifier
        .fillMaxSize()
        .padding(vertical = 50.dp, horizontal = 20.dp),
        horizontalAlignment  = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(R.drawable.welcome),
            contentDescription = null,
            Modifier.size(200.dp, 175.dp)
        )
        Text(
            "Welcome Back", Modifier.padding(top = 30.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 40.sp
        )
        Text(
            "sign in to access your account",
            Modifier.padding(top =4.dp),
            fontSize = 18.sp,
        )
        OutlinedTextField(
            "",
            onValueChange = {
                
            },
            Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            label = {
                Text("Enter your email")
            },
            trailingIcon = {
                Icon(
                    Icons.Filled.MailOutline,
                    contentDescription = null,
                    Modifier,
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xffe6e6e6),
                unfocusedContainerColor = Color(0xffe6e6e6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        OutlinedTextField(
            "",
            onValueChange = {

            },
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            label = {
                Text("Enter your Password")
            },
            visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val  icon = if(passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                Icon(
                    icon,
                    contentDescription = null,
                    Modifier.clickable{
                        passwordVisibility = !passwordVisibility
                    },
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xffe6e6e6),
                unfocusedContainerColor = Color(0xffe6e6e6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Row (
            Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                Modifier,
                verticalAlignment = Alignment.CenterVertically
            ){
                CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
                    Checkbox(false, onCheckedChange = {})
                }
                Text("Remember Me", Modifier.padding(start =10.dp))
            }
            Spacer(Modifier.weight(1f))
            Text("Forgot Password?", Modifier, color = Color(0xffff3951))
        }

        Row(
            Modifier.padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(Modifier
                .height(1.dp)
                .width(70.dp)
                .background(Color.Black))
            Text("or sign in with", Modifier.padding(horizontal = 10.dp))
            Spacer(Modifier
                .height(1.dp)
                .width(70.dp)
                .background(Color.Black))
        }

        val logos = listOf(R.drawable.google_icon, R.drawable.facebook,R.drawable.x_icon)
        Row(
            Modifier.padding(vertical = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(37.5.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            logos.forEach {
                Image(
                    painter = painterResource(it),
                    contentDescription = null,
                    Modifier.size(35.dp)
                )
            }
        }
        Box(
            Modifier
                .padding(top = 20.dp)
                .height(50.dp)
                .wrapContentWidth()
                .background(Color.Black, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ){
            Text("DONE", Modifier
                .padding(horizontal = 100.dp, vertical = 10.dp),
                fontSize = 18.sp,
                color  = Color.White, fontWeight = FontWeight.Bold)
        }
        Row(Modifier.padding(top =16.dp)){
            Text("Don't you have an account?", Modifier, color = Color.Black)
            Text(" SignUp", Modifier.padding(start = 5.dp).clickable{
                controller.navigate("signup")
            }, color = Color(0xffff3951))

        }

        }
    }




@Composable
fun Progress(index : Int){
    Row(
        Modifier.padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        for (i in 0..2) {
            AnimatedVisibility(visible = index == i,
                enter = slideInHorizontally { it } + fadeIn()
                ) {
                Box(
                    Modifier
                        .height(10.dp)
                        .width(30.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xffFF4B4B))
                )
            }
            if(index != i) {
                Box(
                    Modifier
                        .height(10.dp)
                        .width(15.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Black)
                )
            }

        }
    }
}


data class OnboardingItem(
    val title :String,
    val img : Int
)