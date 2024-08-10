package com.example.applicationlogin.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Visibility
import com.example.applicationlogin.R

@Composable
fun AppsAlternatives() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.width(200.dp)
    ) {
        AppsAlternativesIcon(R.drawable.ic_google)
        AppsAlternativesIcon(R.drawable.ic_facebook, 24.dp)
        AppsAlternativesIcon(R.drawable.ic_apple)
    }
}

@Composable
fun AppsAlternativesIcon(id: Int, size: Dp = 20.dp) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .width(60.dp)
            .height(45.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(size),
            painter = painterResource(id = id), contentDescription = "R.drawable.ic_google"
        )
    }

}


@Composable
fun ButtonPrimary(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    lambda: () -> Unit
) {
    Button(
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .shadow(
                elevation = if (enabled) 8.dp else 0.dp,
                spotColor = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp),
                clip = true,
                ambientColor = MaterialTheme.colorScheme.primary,
            ),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        onClick = { lambda() }) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 20.sp, fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,

            )
    }
}

@Composable
fun RowScope.ButtonPrimaryRowScope(text: String, lambda: () -> Unit) {
    ButtonPrimary(text, Modifier.weight(1f), true, lambda)
}


@Composable
fun ButtonSecondary(
    text: String, modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    textcolor: Color = MaterialTheme.colorScheme.tertiary, lambda: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = { lambda() }) {
        Text(
            text = text,
            fontSize = fontSize,
            color = textcolor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun RowScope.ButtonSecondaryRowScope(
    text: String, fontSize: TextUnit = 20.sp,
    textcolor: Color = MaterialTheme.colorScheme.tertiary, lambda: () -> Unit
) {
    ButtonSecondary(text, Modifier.weight(1f), fontSize, textcolor, lambda)
}


@Composable
fun AlternativeText() {
    Text(
        text = "Or continue with",
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun ForgotPasswrodText() {
    Text(
        text = "Forgot your password?",
        textAlign = TextAlign.End,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Input(label: String, value: String, keyboardType: KeyboardType, lambda: (String) -> Unit) {

    var isVisible: Boolean by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        trailingIcon = {
            if (keyboardType == KeyboardType.Password) {
                IconButton(onClick = { isVisible = !isVisible }) {
                    val typeIcon =
                        if (isVisible) R.drawable.ic_visibilityoff else R.drawable.ic_visibility
                    val tintColor =
                        if (isVisible) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
                    Icon(
                        painter = painterResource(id = typeIcon),
                        contentDescription = typeIcon.toString(),
                        tint = tintColor
                    )
                }
            }
        },
        visualTransformation = if (keyboardType == KeyboardType.Password && !isVisible)
            PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        value = value,
        placeholder = {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.SemiBold,
            )
        },
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedBorderColor = Color.Transparent
        ),
        onValueChange = { lambda(it) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DescriptionText(
    text: String, size: TextUnit = 20.sp, width: Dp = 230.dp,
    fontWeight: FontWeight = FontWeight.ExtraBold
) {
    Text(

        text = text,
        color = Color.Black,
        fontSize = size, fontWeight = fontWeight,
        textAlign = TextAlign.Center,
        modifier = Modifier.width(width)
    )
}

@Composable
fun TitleText(text: String, size: TextUnit = 28.sp, fontWeight: FontWeight = FontWeight.W900) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontSize = size, fontWeight = fontWeight
    )
}