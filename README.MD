# 6502 CPU Emulator

# Introduction

This project…

This was inspired by this [youtube playlist](https://www.youtube.com/watch?v=qJgsuQoy9bc&list=PLLwK93hM93Z13TRzPx9JqTIn33feefl37).

## Lessons

### C is cool!

Being able to compare the [primitive datatypes in Java](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html) and the [equivalent in C++](https://en.cppreference.com/w/cpp/language/types) shows that despite Java being a statically-typed language with lots of functionality, C (or C++ in this case). 

### Java doesn’t let you access the internals

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

### Creating our own classes for Byte and Short

given the ability for the C++ emulator to return a byte after a Boolean statement, we’ve used our own classes for byte and short (ByteMemory and WordMemory). This allows us to take advantage of polymorphism with our constructors - and pass bytes, ints or booleans and still get the desired outcome.
