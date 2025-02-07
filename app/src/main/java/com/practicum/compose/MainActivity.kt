package com.practicum.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private val contactEL = Contact(
        name = "Евгений",
        surname = "Андреевич",
        familyName = "Лукашин",
        isFavorite = true,
        phone = "+7 495 495 95 95",
        address = "г. Москва, 3-я улица Строителей, д.25, кв. 12",
        email = "ELukashin@practicum.ru",
    )

    private val contactVK = Contact(
        name = "Василий",
        familyName = "Кузякин",
        imageRes = R.drawable.android,
        address = "Ивановская область, дер. Крутово, д. 4",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ContactDetails(contactEL)
        }
    }

    @Composable
    fun ProfileImage(contact: Contact) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.width(100.dp).height(height = 70.dp),
                painter = painterResource(id = contact.imageRes ?: R.drawable.circle),
                contentScale = contact.imageRes?.let { ContentScale.Crop } ?: ContentScale.Fit,
                contentDescription = null,
            )
            if (contact.imageRes == null) {
                Text(
                    style = TextStyle(
                        fontSize = 22.sp,
                    ),
                    fontWeight = FontWeight.Medium,
                    text = contact.name.take(1) + contact.familyName.take(1)
                )
            }
        }
    }

    @Composable
    fun InfoRow(title: String, text: String) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic
                ),
                text = "$title:  "
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                textAlign = TextAlign.Start,
                text = text
            )
        }
    }

    @Composable
    fun ContactDetails(contact: Contact) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ProfileImage(contact)
                Row(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(
                        style = TextStyle(
                            fontSize = 20.sp,
                        ),
                        fontWeight = FontWeight.Medium,
                        text = "${contact.name} ${contact.surname.orEmpty()}"
                    )
                }
                Row(
                    modifier = Modifier.padding(bottom = 50.dp)
                ) {
                    Text(
                        style = TextStyle(
                            fontSize = 25.sp,
                        ),
                        text = contact.familyName
                    )
                    if (contact.isFavorite) Image(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = android.R.drawable.star_big_on),
                        contentDescription = null
                    )
                }
            }

            InfoRow(
                title = stringResource(id = R.string.phone),
                text = contact.phone
            )
            InfoRow(
                title = stringResource(id = R.string.address),
                text = contact.address
            )
            contact.email?.let { phone ->
                InfoRow(
                    title = stringResource(id = R.string.email),
                    text = phone
                )
            }

        }
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactDetailsFullPreview() {
        ContactDetails(contactEL)
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactDetailsPartialPreview() {
        ContactDetails(contactVK)
    }
}