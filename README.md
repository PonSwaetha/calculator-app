# Calculator

Calculator is a simple text-based calculator app made in Java. It features the basic arithmetic operators, as well as a few trigonometric and logarithmic functions. 
   

## Operands
   
Composed of real numbers which fit within the bounds of an 8-byte double variable.
   

## Binary operators
Syntax: *&lt;expression&gt; &lt;operator&gt;  &lt;expression&gt;*

<table>
    <tr>
        <th>Operation</th>
        <th>Symbol</th>
        <th>Example</th>
    </tr>
    <tr>
        <td>Addition</td>
        <td style="text-align:center">+</td>
        <td>1 + 2 = 3</td>
    </tr>
    <tr>
        <td>Subtraction</td>
        <td style="text-align:center">-</td>
        <td>2 - 3 = -1</td>
    </tr>    
    <tr>
        <td>Multiplication</td>
        <td style="text-align:center">*</td>
        <td>2 * 3 = 6</td>
    </tr>    
    <tr>
        <td>Division</td>
        <td style="text-align:center">/</td>
        <td>4 / 2 = 2</td>
    </tr>
    <tr>
        <td>Exponentiation</td>
        <td style="text-align:center">^</td>
        <td>2 ^ 3 = 8</td>
    </tr>
</table>
   

## Unary operators
Negation syntax: *&lt;expression&gt; &lt;operator&gt;* <br>
Function syntax: *&lt;operator&gt; (&lt;expression&gt;)*

<table>
    <tr>
        <th>Operation</th>
        <th>Symbol</th>
        <th>Example</th>
    </tr>
    <tr>
        <td>Negation</td>
        <td style="text-align:center">-</td>
        <td>-1 + 3 = 2</td>
    </tr>
    <tr>
        <td>Sine</td>
        <td style="text-align:center">sin()</td>
        <td>sin(30) = &frac12 </td>
    </tr>    
    <tr>
        <td>Cosine</td>
        <td style="text-align:center">cos()</td>
        <td>cos(90) = 0</td>
    </tr>    
    <tr>
        <td>Tangent</td>
        <td style="text-align:center">tan()</td>
        <td>tan(45) = 1</td>
    </tr>
    <tr>
        <td>Cotangent</td>
        <td style="text-align:center">cot()</td>
        <td>cot(90) = 0</td>
    </tr>
    <tr>
        <td>Natural log (log<sub>e</sub>)</td>
        <td style="text-align:center">ln()</td>
        <td>ln(e) = 1</td>
    </tr>
    <tr>
        <td>Decimal log (log<sub>10</sub>)</td>
        <td style="text-align:center">log()</td>
        <td>log(100) = 2</td>
    </tr>
</table>

Note: these mathematical functions are considered unary operators because they require only one term as input.
  
## Grouping "operator"
Syntax: *(&lt;expression&gt;)*
   
Use parentheses to elevate the evaluation precedence of an expression or sub-expression.
  

# Usage
Executable calculator-1.0.jar file is located in \target directory.

With command-line argument:
```
java -jar calculator-1.0.jar "(12-(2^2)*2)/-4"
-1
java -jar calculator-1.0.jar "2+4-(12/-3)"
10
java -jar calculator-1.0.jar "(1+(2+3)"
[Error] Mismatched parenthesis.
```

Without command-line argument:
```
java -jar calculator-1.0.jar
Welcome to Eli's Text-based Calculator v1.0
Supported operations: +, -, *, /, ^, sin(), cos(), tan(), cot(), ln(), log().
Type "EXIT" to exit the app.
>> (12-(2^2)*2)/-4
-1
>> 2+4-(12/-3)
10
>> (1+(2+3)
[Error] Mismatched parenthesis.
>> EXIT

```
   
## Rules
* Each operator must be used with its respective number of operands.
    - eg. The expression "*10/5*" is valid, but "*/5*" is missing its left operand.
* No mismatched grouping parentheses; each '(' requires a matching ')'.
* **Function-call parentheses are mandatory.**
* Trigonometric function arguments are in **degrees**.
* **Spaces are optional and have no effect on expression evaluation.**

# Credits

exp4j - https://www.objecthunter.net/exp4j/  
* implementation of Dijkstra's Shunting Yard Algorithm   


