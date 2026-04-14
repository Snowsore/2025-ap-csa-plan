# AP Computer Science A — Diagnostic Test

**Duration: 60 minutes | Total: 100 points**  
**Name: ********\_\_******** Date: ********\_\_**********

> **Aligned to the 2025 AP CSA Course and Exam Description (CED)**
>
> This diagnostic covers all 10 official AP CSA units:
>
> | Unit | Title                                   | Exam Weight (MC) |
> | ---- | --------------------------------------- | ---------------- |
> | 1    | Primitive Types                         | 2.5–5%           |
> | 2    | Using Objects                           | 5–7.5%           |
> | 3    | Boolean Expressions and `if` Statements | 15–17.5%         |
> | 4    | Iteration                               | 17.5–22.5%       |
> | 5    | Writing Classes                         | 5–7.5%           |
> | 6    | Array                                   | 10–15%           |
> | 7    | ArrayList                               | 2.5–7.5%         |
> | 8    | 2D Array                                | 7.5–10%          |
> | 9    | Inheritance                             | 5–10%            |
> | 10   | Recursion                               | 5–7.5%           |
>
> Only syntax and concepts within the **AP Java Subset** are tested.  
> (`switch`, `continue`, `break`, ternary `?:`, etc. are **not** used.)

---

## Section I: Multiple Choice (85 pts)

---

### Q1 (4 pts)

> **Unit 1 — Primitive Types**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests knowledge of legal Java identifier rules — identifiers cannot start with a digit, cannot contain hyphens, and cannot use reserved words.

Which of the following are valid Java variable declarations? (Select all that apply)

A. `int 2ndPlace = 2;`  
B. `double my-score = 95.5;`  
C. `String firstName = "Alice";`  
D. `boolean _isValid = true;`  
E. `int class = 10;`

Answer: ****\_\_****

---

### Q2 (4 pts)

> **Unit 1 — Primitive Types**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of integer division. When both operands are `int`, the result is truncated to an integer before being widened to `double`.

What is the output of the following code?

```java
int a = 7;
int b = 2;
double c = a / b;
System.out.println(c);
```

A. `3.5`  
B. `3.0`  
C. `3`  
D. Compilation error

Answer: ****\_\_****

---

### Q3 (4 pts)

> **Unit 1 — Primitive Types**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of explicit casting between `int` and `double`, and how truncation works with `(int)`.

What is the output of the following code?

```java
int x = 10;
double y = 3.0;
int result = (int)(x / y);
System.out.println(result);
```

A. `3`  
B. `3.33`  
C. `4`  
D. Compilation error

Answer: ****\_\_****

---

### Q4 (4 pts)

> **Unit 2 — Using Objects**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests the critical difference between `==` (reference comparison) and `.equals()` (value comparison) for `String` objects created with `new`. AP CSA focuses on the fact that `new String()` creates a distinct object.

What is the output of the following code?

```java
String s1 = new String("Hello");
String s2 = new String("Hello");

System.out.println(s1 == s2);
System.out.println(s1.equals(s2));
```

A. `true true`  
B. `false true`  
C. `false false`  
D. `true false`

Answer: ****\_\_****

---

### Q5 (4 pts)

> **Unit 2 — Using Objects**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of how `Math` class static methods work — `Math.abs()`, `Math.pow()`, and `Math.sqrt()` — and the ability to evaluate nested method calls.

What is the value of `result` after this code executes?

```java
int a = -4;
double result = Math.sqrt(Math.abs(a)) + Math.pow(2, 3);
```

A. `6.0`  
B. `10.0`  
C. `12.0`  
D. Compilation error

Answer: ****\_\_****

---

### Q6 (4 pts)

> **Unit 2 — Using Objects**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of `String` methods `substring()` and `indexOf()`, which are heavily tested on the AP exam.

What is the output of the following code?

```java
String s = "AP Computer Science";
System.out.println(s.substring(3, 11));
System.out.println(s.indexOf("Science"));
```

A. `Computer` and `12`  
B. `Compute` and `12`  
C. `Computer` and `11`  
D. `omputer ` and `12`

Answer: ****\_\_****

---

### Q7 (4 pts)

> **Unit 3 — Boolean Expressions and `if` Statements**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests evaluation of compound Boolean expressions using `&&`, `||`, and `!`.

Which of the following expressions evaluates to `true`?

```java
int x = 5;
```

A. `x > 3 && x < 4`  
B. `x != 5 || x >= 5`  
C. `!(x == 5)`  
D. `x > 5 && x == 5`

Answer: ****\_\_****

---

### Q8 (4 pts)

> **Unit 3 — Boolean Expressions and `if` Statements**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of independent `if` statements vs. `if-else` chains. The `else` only pairs with the nearest preceding `if`.

What is the output of the following code?

```java
int x = 15;
if (x > 10)
    System.out.print("A");
if (x > 5)
    System.out.print("B");
else
    System.out.print("C");
```

A. `A`  
B. `AB`  
C. `AC`  
D. `ABC`

Answer: ****\_\_****

---

### Q9 (4 pts)

> **Unit 3 — Boolean Expressions and `if` Statements**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of `if / else if / else` chains where only the first matching branch executes, vs. separate `if` statements which are independent.

What is the output of the following code?

```java
int score = 85;
if (score >= 90)
    System.out.print("A");
else if (score >= 80)
    System.out.print("B");
else if (score >= 70)
    System.out.print("C");
else
    System.out.print("F");
```

A. `B`  
B. `BC`  
C. `ABC`  
D. `B C`

Answer: ****\_\_****

---

### Q10 (5 pts)

> **Unit 4 — Iteration**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests ability to trace a `for` loop that accumulates a result, and to recognize an odd-only summation pattern using modulus.

What is the output of the following code?

```java
int sum = 0;
for (int i = 1; i <= 10; i++) {
    if (i % 2 != 0) {
        sum += i;
    }
}
System.out.println(sum);
```

A. `20`  
B. `25`  
C. `30`  
D. `55`

Answer: ****\_\_****

---

### Q11 (5 pts)

> **Unit 4 — Iteration**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests ability to trace a `while` loop with integer division, requiring manual step-by-step execution to determine the final value.

What is the value of `count` after the following code executes?

```java
int count = 0;
int i = 10;
while (i > 0) {
    i /= 3;
    count++;
}
```

A. `2`  
B. `3`  
C. `4`  
D. Infinite loop

Answer: ****\_\_****

---

### Q12 (5 pts)

> **Unit 4 — Iteration**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests ability to trace nested `for` loops and understand how the inner loop bound depends on the outer loop variable.

What is the output of the following code?

```java
for (int i = 0; i < 3; i++) {
    for (int j = 0; j <= i; j++) {
        System.out.print("*");
    }
    System.out.println();
}
```

A.

```
*
*
*
```

B.

```
*
**
***
```

C.

```
***
**
*
```

D.

```
***
***
***
```

Answer: ****\_\_****

---

### Q13 (4 pts)

> **Unit 5 — Writing Classes**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of the `private` access modifier and encapsulation — private fields cannot be accessed directly from outside the class.

What is the issue with the following code?

```java
public class BankAccount {
    private double balance;

    public BankAccount(double initial) {
        balance = initial;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

// In main:
BankAccount acc = new BankAccount(100);
acc.balance = 999;
```

A. Runs normally — `balance` becomes `999`  
B. Compilation error — `balance` is `private`  
C. Runtime exception  
D. Outputs `100`

Answer: ****\_\_****

---

### Q14 (4 pts)

> **Unit 5 — Writing Classes**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of `static` variables (shared across all instances) vs. instance variables, and how `toString()` is implicitly called by `println`.

What is the output of the following code?

```java
public class Counter {
    private static int count = 0;
    private int id;

    public Counter() {
        count++;
        id = count;
    }

    public String toString() {
        return "Counter #" + id + " (total: " + count + ")";
    }
}

// In main:
Counter c1 = new Counter();
Counter c2 = new Counter();
Counter c3 = new Counter();
System.out.println(c1);
```

A. `Counter #1 (total: 1)`  
B. `Counter #1 (total: 3)`  
C. `Counter #3 (total: 3)`  
D. Compilation error

Answer: ****\_\_****

---

### Q15 (4 pts)

> **Unit 6 — Array**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of zero-based array indexing and basic element access to compute a sum.

What is the output of the following code?

```java
int[] arr = {5, 10, 15, 20, 25};
System.out.println(arr[2] + arr[4]);
```

A. `25`  
B. `35`  
C. `40`  
D. `ArrayIndexOutOfBoundsException`

Answer: ****\_\_****

---

### Q16 (4 pts)

> **Unit 6 — Array**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of array element shifting (a common pattern for deletion) and what happens to the last element when shifting left.

What are the contents of `arr` after the following code executes?

```java
int[] arr = {1, 2, 3, 4, 5};
for (int i = 0; i < arr.length - 1; i++) {
    arr[i] = arr[i + 1];
}
```

A. `{2, 3, 4, 5, 5}`  
B. `{2, 3, 4, 5, 0}`  
C. `{1, 1, 2, 3, 4}`  
D. `{0, 1, 2, 3, 4}`

Answer: ****\_\_****

---

### Q17 (4 pts)

> **Unit 7 — ArrayList**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of `ArrayList.add(index, element)` insertion and `ArrayList.remove(index)` with shifting indices after each operation.

What is the output of the following code?

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(10);
list.add(20);
list.add(30);
list.add(1, 15);
list.remove(2);
System.out.println(list);
```

A. `[10, 15, 30]`  
B. `[10, 15, 20]`  
C. `[10, 20, 30]`  
D. `[15, 20, 30]`

Answer: ****\_\_****

---

### Q18 (4 pts)

> **Unit 7 — ArrayList**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests awareness of the classic pitfall of removing elements while iterating forward through an `ArrayList`, which causes index shifting and may skip elements.

What is the issue with the following code?

```java
ArrayList<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.add("Charlie");
for (int i = 0; i < names.size(); i++) {
    if (names.get(i).equals("Bob")) {
        names.remove(i);
    }
}
System.out.println(names);
```

A. Compilation error  
B. Runtime exception  
C. Outputs `[Alice, Charlie]` — code works correctly  
D. May skip checking some elements due to index shift, but happens to produce the correct result in this case

Answer: ****\_\_****

---

### Q19 (4 pts)

> **Unit 8 — 2D Array**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests ability to traverse a 2D array using enhanced `for` loops and apply a conditional accumulation pattern.

What is the output of the following code?

```java
int[][] grid = {{1, 2, 3}, {4, 5, 6}};
int sum = 0;
for (int[] row : grid) {
    for (int val : row) {
        if (val % 2 == 0)
            sum += val;
    }
}
System.out.println(sum);
```

A. `6`  
B. `9`  
C. `12`  
D. `21`

Answer: ****\_\_****

---

### Q20 (5 pts)

> **Unit 9 — Inheritance**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of runtime polymorphism — the actual object type determines which overridden method is called, not the declared reference type.

What is the output of the following code?

```java
public class Animal {
    public String speak() {
        return "...";
    }
}

public class Dog extends Animal {
    public String speak() {
        return "Woof!";
    }
}

public class Cat extends Animal {
    public String speak() {
        return "Meow!";
    }
}

// In main:
Animal a = new Dog();
Animal b = new Cat();
System.out.println(a.speak());
System.out.println(b.speak());
```

A. `... ...`  
B. `Woof! Meow!`  
C. `Woof! ...`  
D. Compilation error

Answer: ****\_\_****

---

### Q21 (4 pts)

> **Unit 9 — Inheritance**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests understanding of the `super` keyword — calling a superclass constructor and invoking a superclass method from an overriding method.

What is the output of the following code?

```java
public class Vehicle {
    private String type;

    public Vehicle(String type) {
        this.type = type;
    }

    public String getInfo() {
        return "Vehicle: " + type;
    }
}

public class Car extends Vehicle {
    private int doors;

    public Car(String type, int doors) {
        super(type);
        this.doors = doors;
    }

    public String getInfo() {
        return super.getInfo() + ", Doors: " + doors;
    }
}

// In main:
Car c = new Car("Sedan", 4);
System.out.println(c.getInfo());
```

A. `Vehicle: Sedan`  
B. `Vehicle: Sedan, Doors: 4`  
C. `Doors: 4`  
D. Compilation error — `super` cannot be used inside a method

Answer: ****\_\_****

---

### Q22 (4 pts)

> **Unit 10 — Recursion**  
> **Practice 3 — Analyze Code**  
> **Reason:** Tests ability to trace a simple recursive method call, identifying the base case and understanding the call stack.

What is the output of `mystery(4)`?

```java
public static int mystery(int n) {
    if (n == 0)
        return 0;
    return n + mystery(n - 1);
}
```

A. `4`  
B. `10`  
C. `16`  
D. Infinite recursion

Answer: ****\_\_****

---

## Section II: Free Response (15 pts)

The following questions use the AP CSA Free Response format. You are given class context, a method signature, and must write only the method body.

---

### Q23 (5 pts)

> **Unit 6 — Array / Unit 4 — Iteration**  
> **Practice 2 — Develop Code**  
> **Reason:** Tests ability to write a standard array traversal algorithm — a fundamental FRQ pattern. Mirrors the "array/ArrayList algorithms" category on the AP exam.

Consider the following incomplete method in a class `ArrayHelper`. The method should return the index of the largest value in the given array. If there are ties, return the index of the first occurrence.

```java
public class ArrayHelper {
    /** Returns the index of the largest value in arr.
     *  Precondition: arr.length > 0
     */
    public static int indexOfMax(int[] arr) {
        // Write your code here






    }
}
```

---

### Q24 (5 pts)

> **Unit 7 — ArrayList / Unit 4 — Iteration**  
> **Practice 2 — Develop Code**  
> **Reason:** Tests the ability to traverse and conditionally remove elements from an `ArrayList` — a classic AP CSA FRQ pattern that requires careful index management.

Consider the following incomplete method in a class `ListFilter`. The method should remove all strings from the list that have a length less than `minLength`.

```java
import java.util.ArrayList;

public class ListFilter {
    /** Removes all strings from words that have length less than minLength.
     *  The remaining elements retain their relative order.
     *
     *  Example:
     *    words = ["hi", "hello", "ok", "alphabet", "go"]
     *    minLength = 3
     *    After calling filterShortWords(words, 3):
     *    words = ["hello", "alphabet"]
     */
    public static void filterShortWords(ArrayList<String> words, int minLength) {
        // Write your code here






    }
}
```

---

### Q25 (5 pts)

> **Unit 5 — Writing Classes / Unit 9 — Inheritance**  
> **Practice 1 — Design Code**  
> **Reason:** Tests ability to write a subclass with a constructor that calls `super`, override a method, and use inherited methods. Mirrors the AP CSA FRQ class-writing questions.

Consider the following `Shape` class:

```java
public class Shape {
    private String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return 0.0;
    }

    public String toString() {
        return name + " area=" + getArea();
    }
}
```

Write the `Rectangle` class that extends `Shape`. It should:

- Have private instance variables `width` and `height` (type `double`)
- Have a constructor that takes `width` and `height`, and calls the superclass constructor with the name `"Rectangle"`
- Override `getArea()` to return the correct area
- **Do NOT override** `toString()` — the inherited version should work correctly

```java
public class Rectangle extends Shape {
    // Write your code here












}
```

---

## Answer Key (Teacher Use Only)

| Q#  | Answer | Q#  | Answer    |
| --- | ------ | --- | --------- |
| Q1  | C, D   | Q14 | B         |
| Q2  | B      | Q15 | C         |
| Q3  | A      | Q16 | A         |
| Q4  | B      | Q17 | A         |
| Q5  | B      | Q18 | D         |
| Q6  | A      | Q19 | C         |
| Q7  | B      | Q20 | B         |
| Q8  | B      | Q21 | B         |
| Q9  | A      | Q22 | B         |
| Q10 | B      | Q23 | See below |
| Q11 | B      | Q24 | See below |
| Q12 | B      | Q25 | See below |
| Q13 | B      |     |           |

### Explanations for Selected Questions

**Q6:** `s.substring(3, 11)` extracts characters at index 3 through 10 → `"Computer"`. `s.indexOf("Science")` returns 12 (the starting index of `"Science"` in `"AP Computer Science"`).

**Q11:** `i=10` → `10/3=3`, count=1; `i=3` → `3/3=1`, count=2; `i=1` → `1/3=0`, count=3; `i=0` → loop exits. Answer: **B (3)**

**Q22:** `mystery(4)` = 4 + `mystery(3)` = 4 + 3 + `mystery(2)` = 4 + 3 + 2 + `mystery(1)` = 4 + 3 + 2 + 1 + `mystery(0)` = 4 + 3 + 2 + 1 + 0 = **10**.

### Q23 — Sample Answer

```java
public static int indexOfMax(int[] arr) {
    int maxIndex = 0;
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > arr[maxIndex]) {
            maxIndex = i;
        }
    }
    return maxIndex;
}
```

### Q24 — Sample Answer

```java
public static void filterShortWords(ArrayList<String> words, int minLength) {
    int i = 0;
    while (i < words.size()) {
        if (words.get(i).length() < minLength) {
            words.remove(i);
        } else {
            i++;
        }
    }
}
```

### Q25 — Sample Answer

```java
public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        super("Rectangle");
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }
}
```

---

### Scoring Rubric

| Score Range | Assessment                                                                       |
| ----------- | -------------------------------------------------------------------------------- |
| 85–100      | **Excellent** — Strong foundation; ready for intermediate/advanced AP CSA topics |
| 70–84       | **Good** — Solid basics; needs reinforcement in some areas                       |
| 50–69       | **Average** — Understands basic concepts; requires systematic review             |
| 30–49       | **Weak** — Minimal programming experience; needs to start from fundamentals      |
| 0–29        | **Beginner** — No prior Java experience; start from introductory lessons         |

### AP CSA Official Unit Coverage

| Unit                                             | Title                                                             | Questions               | Points |
| ------------------------------------------------ | ----------------------------------------------------------------- | ----------------------- | ------ |
| Unit 1 — Primitive Types                         | Integer division, casting, identifiers                            | Q1, Q2, Q3              | 12     |
| Unit 2 — Using Objects                           | `String` methods, `==` vs `.equals()`, `Math` class               | Q4, Q5, Q6              | 12     |
| Unit 3 — Boolean Expressions and `if` Statements | Compound Boolean logic, `if`/`else if`/`else` chains              | Q7, Q8, Q9              | 12     |
| Unit 4 — Iteration                               | `for` loops, `while` loops, nested loops, loop tracing            | Q10, Q11, Q12, Q23, Q24 | 15+5+5 |
| Unit 5 — Writing Classes                         | Encapsulation, `private`, `static`, `toString()`, class design    | Q13, Q14, Q25           | 8+5    |
| Unit 6 — Array                                   | Indexing, element shifting, traversal algorithms                  | Q15, Q16, Q23           | 8+5    |
| Unit 7 — ArrayList                               | `add`/`remove` operations, index shifting pitfall, filtering      | Q17, Q18, Q24           | 8+5    |
| Unit 8 — 2D Array                                | Enhanced `for` loop traversal, conditional accumulation           | Q19                     | 4      |
| Unit 9 — Inheritance                             | Polymorphism, `super` keyword, method overriding, subclass design | Q20, Q21, Q25           | 9+5    |
| Unit 10 — Recursion                              | Recursive method tracing, base case identification                | Q22                     | 4      |
