# 6502 CPU Emulator

# Introduction

This project…

This was inspired by this [youtube playlist](https://www.youtube.com/watch?v=qJgsuQoy9bc&list=PLLwK93hM93Z13TRzPx9JqTIn33feefl37).

## Lessons

### C is cool!

Being able to compare the [primitive datatypes in Java](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html) and the [equivalent in C++](https://en.cppreference.com/w/cpp/language/types) shows that despite Java being a statically-typed language with lots of functionality, C (or C++ in this case) allows us to get a lot closer to the underlying Assembly Code (and therefore the CPU) than Java lets us get to the bytecode that runs on our JVM.

### Java doesn’t let you access the internals (but the JVM/compiler is kind)

The C++ emulator makes a lot of use of the `unsigned char` to replicate a byte, and an `unsigned short`  to replicate a word. Looking again at the [primitive datatypes in Java](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html), we have `byte` as an  *8-bit signed two's complement integer* and we have a `short` which is *16-bit signed two's complement integer*. 

However, one issue with Java is that there is limited ability to access the primitive binary datatypes. This [page](https://docs.oracle.com/javase/8/docs/technotes/guides/language/binary-literals.html) from Oracle shows a way of declaring binary values explicitly, but this is is a bit fiddly and makes the code harder to read. So, we’re using type-casting here from integers to bytes or shorts. 

However, this is not too much of an issue. If we look at the two following classes `ByteConversion` and `IntegerDeclaration` - and then let’s see how the Java compiler translates this into Java bytecode. 

```java
	class ByteConversion {
	public static void main(String[] args) {
		byte testByte = (byte) 1;
	}
}
```

```java
class IntegerDeclaration {
	public static void main(String[] args) {
		int testInt = 1;
	}
}
```

The only difference between these two classes is that the variable declared in `ByteConversion` is `testByte` - where we’re typecasting a byte from an int (1 - *0000001 → 0001 for any anyone keeping count*), whereas in `IntegerDeclaration` we just declare the int value of 1. 

If we use the `javap` command (with the `-v` flag to give us more details) we get the following information about how Java compiles this into bytecode. 

```java
javap -c ByteConversion.class
Compiled from "ByteConversion.java"
class ByteConversion {
  ByteConversion();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_1
       1: istore_1
       2: return
}
```

Then, if we do the same for `IntegerDeclaration` :

```java
javap -c IntegerDeclaration.class
Compiled from "IntegerDeclaration.java"
class IntegerDeclaration {
  IntegerDeclaration();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_1
       1: istore_1
       2: return
}
```

So, whilst the code is perhaps a little less user-friendly or readable, there is no computational tax for  the type-casting. As we’re only using bytes to house 0s or 1s, this works fine in this case. We’ll assume the short datatype works in the same manner, but we’ll look at that later.


## Solutions

### Taking care about signed vs unsigned binary datatypes

```
from chatgpt

The expression address & 0xFFFF is using a bitwise AND operation to "mask" the address value and ensure it fits within a certain range. In binary terms, the operation is not changing the most significant bit; it's actually preserving the least significant 16 bits of the address value.

Let's break down the operation:

address is a short, which means it's a 16-bit value.
0xFFFF is a hexadecimal value that represents 16 bits all set to 1. In binary, it's 1111 1111 1111 1111.
When you perform a bitwise AND operation between address and 0xFFFF, each bit position is compared, and if both bits are 1, the result will be 1; otherwise, it will be 0.

Here's an example of how the operation works in binary terms:

plaintext
Copy code
address:  1101 0010 0100 1011 (example short value)
0xFFFF:   1111 1111 1111 1111

AND:      1101 0010 0100 1011 (address)
          1111 1111 1111 1111 (0xFFFF)
          ------------------
          1101 0010 0100 1011

Result:   1101 0010 0100 1011
As you can see, the bitwise AND operation preserves the least significant 16 bits of the address value and discards any bits beyond the 16th bit. This effectively ensures that the resulting value is within the valid range for your array index, which is what you want to achieve to avoid the ArrayIndexOutOfBoundsException.
```


### In Java, ints are immutable

```
again from chatgpt:

 In Java, primitive data types like int are passed by value, which means their values are copied when passed to a method. As a result, changes to the parameter within the method do not affect the original value outside the method.
```

we were trying to pass an int to a method, but causing an infinite loop

### Methods and classes
 
One of C++'s great advantages is it's use of pointers. // for J - maybe cut this

One of the key differences (as much style as functionality) is the need to break out the consitutent parts of the CPU into various classes. 

### Creating our own classes for `byte` and `short`

Given the ability for the C++ emulator to return a byte after a Boolean statement, we’ve used our own classes for `byte` and `short` (`ByteMemory` and `WordMemory`). This allows us to take advantage of polymorphism with our constructors - and pass bytes, ints or booleans and still get the desired outcome.

### To Do

- add tests for negative bytes/shorts
- analyse byte code for binary bitwise operations
