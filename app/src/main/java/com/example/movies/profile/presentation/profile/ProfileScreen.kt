package com.example.movies.profile.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.navigation.Routes
import com.example.movies.profile.presentation.profile.components.ProfileHeader
import com.example.movies.profile.presentation.profile.components.ProfileSettings
import com.example.movies.profile.presentation.profile.components.SettingItem
import com.example.movies.profile.presentation.profile.components.UserInfoSection


@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        ProfileHeader()
        Spacer(Modifier.height(20.dp))
        UserInfoSection(
            name = "Eren Turkmen",
            email = "ertuken@gmail.com",
            imageRes = R.drawable.man_image
        )
        Spacer(Modifier.height(20.dp))
        ProfileSettings(
            settings = listOf(
                SettingItem(
                    text = "Change Password",
                    iconRes = R.drawable.ic_arrow_forward,
                    onClick = { navController.navigate(Routes.ChangePassword.route)}
                ),
                SettingItem(
                    text = "Privacy",
                    iconRes = R.drawable.ic_arrow_forward,
                    onClick = { navController.navigate(Routes.Privacy.route) }
                ),
                SettingItem(
                    text = "Terms & Conditions",
                    iconRes = R.drawable.ic_arrow_forward,
                    onClick = { navController.navigate(Routes.TermsAndCondition.route) }
                ),
                SettingItem(
                    text = "Sign Out",
                    iconRes = R.drawable.ic_logout,
                    onClick = { /* Handle sign out click */ }
                )
            )
        )
    }
}



