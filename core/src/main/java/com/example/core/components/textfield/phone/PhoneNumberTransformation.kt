package com.example.core.components.textfield.phone

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation


class PhoneNumberTransformation(private val prefix: String = "+7 ") : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return phoneNumberFilter(text, prefix)
    }
}

private fun phoneNumberFilter(number: AnnotatedString, prefix: String): TransformedText {

    var out = prefix
    for (i in number.text.indices) {
        if (i == 0) out += "("
        out += number.text[i]
        if (i == 2) out +=") "
        if (i == 5 || i == 7) out += " "
    }

    val prefixOffset = prefix.length

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 0) return offset + prefixOffset
            if (offset <= 2 ) return offset + prefixOffset + 1
            if (offset <= 3) return offset + prefixOffset + 2
            if (offset <= 6) return offset + prefixOffset + 3
            if (offset <= 8) return offset + prefixOffset + 4
            if (offset <= 11) return offset + prefixOffset + 5

            return 17
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= prefixOffset - 1) return prefixOffset
            return offset - prefixOffset
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}