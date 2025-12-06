package com.muggle.tiktokcopy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * 手机号编辑框
 */
@Composable
fun PhoneNumberEditor(
    phoneNumber: String = "",
    regionCode: String = "+86",
    onTextChangeAct: (String) -> Unit = {},
    onClearAct: () -> Unit = {},
    onClickRegionCodeAct: () -> Unit = {}
) {
    var curTextStr by remember {
        mutableStateOf(phoneNumber)
    }

    var curRegionCode by remember {
        mutableStateOf(regionCode)
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 24.cdp)
            .fillMaxWidth()
            .height(52.cdp)
            .clip(shape = RoundedCornerShape(12.cdp))
            .background(Color(0x337f7f7f))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(18.cdp))
            // TODO: 添加点击选号码区域码功能
            PhoneNumberPrefix(curRegionCode) {
                onClickRegionCodeAct()
            }
            Spacer(modifier = Modifier.width(7.cdp))
            Spacer(
                modifier = Modifier
                    .height(10.cdp)
                    .width(0.67.cdp)
                    .background(Color(0xffababab))
            )
            Spacer(modifier = Modifier.width(7.cdp))
            // TODO: 复制粘贴功能
            BasicTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                value = curTextStr,
                onValueChange = { text ->
                    if (text.length <= 11) {
                        curTextStr = text
                        onTextChangeAct(text)
                    }
                },
                textStyle = TextStyle(
                    fontSize = TextUnit(18.csp.value, TextUnitType.Sp),
                    color = Color(0xff161823)
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                visualTransformation = object : VisualTransformation {
                    override fun filter(text: AnnotatedString): TransformedText {
                        var output = ""
                        for (i in text.text.indices) {
                            output += text.text[i]
                            if (i == 2 || i == 6) {
                                output += " "
                            }
                        }
                        return TransformedText(AnnotatedString(output), object : OffsetMapping {
                            override fun originalToTransformed(offset: Int): Int {
                                return when {
                                    offset < 3 -> offset

                                    offset in 3..6 -> offset + 1

                                    offset > 6 -> offset + 2

                                    else -> 13
                                }
                            }

                            override fun transformedToOriginal(offset: Int): Int {
                                return when {
                                    offset < 4 -> offset

                                    offset in 4..8 -> offset - 1

                                    offset > 8 -> offset - 2

                                    else -> 11
                                }
                            }
                        })
                    }
                },
                cursorBrush = SolidColor(Color(0xfffe2c55))
            )
        }

        if (curTextStr.isEmpty()) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(73.33.cdp))
                Text(text = "请输入手机号", fontSize = 14.csp, color = Color(0x7fababaf))
            }
        } else {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(309.33.cdp))
                Image(
                    modifier = Modifier
                        .size(18.cdp)
                        .clickable {
                            curTextStr = ""
                            onClearAct()
                        },
                    painter = painterResource(R.drawable.login_clear_input),
                    contentDescription = ""
                )
            }
        }
    }
}

/**
 * 手机区号展示组件
 */
@Composable
fun PhoneNumberPrefix(
    prefix: String = "+86", onClickRegionCodeAct: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(18.cdp)
            .clickable {
                onClickRegionCodeAct()
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = prefix, fontSize = 14.csp, color = Color(0xff161823))
        Spacer(modifier = Modifier.width(7.cdp))
        Icon(
            modifier = Modifier
                .size(10.cdp),
            painter = painterResource(R.drawable.login_down_arrow),
            contentDescription = ""
        )
    }

}


@Preview
@Composable
fun PreviewPhoneNumberEditor() {
//    PhoneNumberEditor()
}