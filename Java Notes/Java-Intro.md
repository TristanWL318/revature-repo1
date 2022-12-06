# Java
Java is a well known enterprise programming language. As a language it is well designed to scale in size and capabilities. Here are some key details:
- Java is a **strongly typed language**
    - once a data type is set, it cannot be changed
- Java is a **statically typed language**
    - you must declare the data type you are working with every time you create data in your Java application
- Java has automatic memory management
    - Java will handle all memory management and garbage collection
    - garbage collection is the removal of data from memory that are no long in use
- Write Once, Run Anywhere
    - any machine that has a JRE/JVM is able to run a Java application

## JDK, JRE, JVM
The Java Development Kit (JDK) contains the libraries and tools needed to be able to write Java applications. One of the key tools is the Hava compiler: this allows the source code (.java files) into byte code that are executed (.class files). It also provides a debugger, used to solve issued with the code.

A Java Runtime Environment (JRE) is provided with the JDK. It contains runtime libraries necessary to execute the byte code (.class files). Contained within the JRE is the Java Virtual Machine (JVM), the tool that ensures the byte code (.class files) will run on any machine.

The Java Virtual Machine (JVM) is the tool that turns the byte code (.class files) into machine code particular to the operating system. All JVMs are contained in their respective JRE.