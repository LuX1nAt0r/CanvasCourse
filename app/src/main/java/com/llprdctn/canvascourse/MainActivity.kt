package com.llprdctn.canvascourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.llprdctn.canvascourse.ui.theme.CanvasCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                Clock(
                    modifier = Modifier.fillMaxSize(),
                    second = 1,
                    minute = 1,
                    hour = 1,

                )


            /*var weight by remember {
                mutableStateOf(80)
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()

                ,
                fontSize = 48.sp,
                textAlign = TextAlign.Center,
                text = weight.toString()
            )
            Box(modifier = Modifier
                .fillMaxSize()
            ) {


                Scale(
                    style = ScaleStyle(
                        scaleWidth = 150.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .align(Alignment.Center)

                ) {
                    weight = it
                }
            }*/
        }
    }
}
