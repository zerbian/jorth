# Jorth Programming Language
This programming lanuage inspired by Forth and Tsoding Dailys YouTube Series on [Porth](https://www.youtube.com/watch?v=8QP2fDBIxjM&list=PLpM-Dvs8t0VbMZA7wW9aR3EtBqe2kinu4).

The language is interpreted using a Java interpreter.

## Getting Started
Compile the interpreter:
```
cd src
javac Jorth
```
Run Example:
```
java Jorth ../examples/1-BasicOutput.jorth
```

## Manual
### General
#### Number Literals
```
1 -40 650
```
Integer numbers are getting pushed onto the stack.
### Arithmetic
All operations are in postfix notation and they consume their operands (pop from stack)
#### Add
```
1 5 +
```
```
6
```
#### Subtract
```
5 1 -
```
```
4
```
#### Multiply
```
5 7 *
```
```
35
```
#### Modulo Devision
```
7 3 mod
```
```
1
```
### Boolean
#### Equals
```
3 1 ==
```
```
0
```
returns `0` for false and `1` for true.
#### Less Than
```
10 3 <
```
```
1
```
### Stack Manipulation
#### Drop
```
10 11 drop
```
```
10
```
Pops from the stack and drops it.
#### Dup
```
3 dup
```
```
3 3
```
Dublicates top element of the stack.
#### Swap
```
1 7 swap
```
```
7 1
```
Swaps the top two elements of the stack.
#### Over
```
1 7 over
```
```
1 7 1
```
Pushes and copies the second stack element to the stack.
### Output
Use `print` two print the top element of the stack. `print` pops the element.
### Conditional Logic
#### If-Statement
```
10 10 == if
    1 print
end
```
```
1
```

`if` pops the first element of the stack and checks it if its a `0` or a `1` and either executes block inside `if` and `end` or skips to `end`.

#### While Loop
```
4 while dup 1 < do
    dup print
    1 -
end
```
```
3
2
1
```