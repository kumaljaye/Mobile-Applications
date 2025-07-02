package com.example.simplecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplecalculator.ui.theme.SimpleCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun clone(savedInstanceState: Bundle?) {
        super.clone(savedInstanceState)
        setContent {
            SimpleCalculatorTheme {
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var displayValue by remember { mutableStateOf("0") }
    var currentInput by remember { mutableStateOf("") }
    var previousInput by remember { mutableStateOf("") }
    var currentOperator by remember { mutableStateOf<String?>(null) }

    fun onNumberClick(number: String) {
        if (displayValue == "0" && number != ".") {
            displayValue = number
        } else if (!displayValue.contains(".") || number != ".") { // Allow only one decimal point
            displayValue += number
        }
        currentInput += number
    }

    fun onOperatorClick(operator: String) {
        if (currentInput.isNotEmpty()) {
            if (previousInput.isNotEmpty() && currentOperator != null) {
                // If there's a pending operation, calculate it first
                onEqualsClick()
            }
            previousInput = displayValue
            currentOperator = operator
            currentInput = "" // Reset current input for the next number
            // Display the operator or the previous number, depending on your preference
            // For simplicity, we'll keep showing the last entered number or result
        }
    }

    fun onEqualsClick() {
        if (currentInput.isNotEmpty() && previousInput.isNotEmpty() && currentOperator != null) {
            val num1 = previousInput.toDoubleOrNull()
            val num2 = currentInput.toDoubleOrNull()

            if (num1 != null && num2 != null) {
                val result = when (currentOperator) {
                    "+" -> num1 + num2
                    "-" -> num1 - num2
                    "*" -> num1 * num2
                    "/" -> if (num2 != 0.0) num1 / num2 else "Error" // Handle division by zero
                    else -> "Error"
                }
                displayValue = if (result is Double && result.toString().endsWith(".0")) {
                    result.toInt().toString() // Display as Int if it's a whole number
                } else {
                    result.toString()
                }
                previousInput = displayValue // Store result for chained operations
                currentInput = displayValue // The result becomes the new current input
                currentOperator = null
            } else {
                displayValue = "Error"
            }
        }
    }

    fun onClearClick() {
        displayValue = "0"
        currentInput = ""
        previousInput = ""
        currentOperator = null
    }

    fun onDeleteClick() {
        if (displayValue.length > 1 && displayValue != "0") {
            displayValue = displayValue.dropLast(1)
            if (currentInput.isNotEmpty()) {
                currentInput = currentInput.dropLast(1)
            }
        } else if (displayValue.length == 1 && displayValue != "0") {
            displayValue = "0"
            currentInput = ""
        }
    }


    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom)
    ) {
        Text(
                text = displayValue,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.End
        )

        // Calculator buttons
        val buttonRows = listOf(
                listOf("7", "8", "9", "/"),
                listOf("4", "5", "6", "*"),
                listOf("1", "2", "3", "-"),
                listOf("0", ".", "=", "+")
        )

        buttonRows.forEach { row ->
                Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                ) {
            row.forEach { buttonText ->
                    CalculatorButton(
                            text = buttonText,
                            onClick = {
                                    when (buttonText) {
                                    in "0".."9", "." -> onNumberClick(buttonText)
                in listOf("+", "-", "*", "/") -> onOperatorClick(buttonText)
                "=" -> onEqualsClick()
                            }
                        },
                modifier = Modifier.weight(1f)
                    )
            }
        }
        }

        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalculatorButton(text = "C", onClick = { onClearClick() }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "DEL", onClick = { onDeleteClick() }, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CalculatorButton(
        text: String,
        onClick: () -> Unit,
modifier: Modifier = Modifier,
backgroundColor: androidx.compose.ui.graphics.Color = MaterialTheme.colors.secondary
) {
Button(
        onClick = onClick,
        modifier = modifier
                .padding(4.dp)
            .aspectRatio(1f), // Makes buttons square-ish
colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
Text(text = text, style = MaterialTheme.typography.h6)
    }
            }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleCalculatorTheme {
        CalculatorScreen()
    }
}