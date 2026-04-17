# AP Computer Science A (AP CSA) — Quick Guide & Sample Questions

> **Updated for the 2025–2026 Framework** (applies to the May 2026 exam).
> Major restructuring: 10 units → 4 units. Inheritance/polymorphism removed. Text file processing and data sets added.

## Course Overview

AP Computer Science A focuses on **programming in Java**. It covers problem-solving, algorithm design, class creation, and data collections. The 2025–2026 framework restructures the course into **4 large units**, removing inheritance and adding practical topics like text file I/O and working with data sets.

---

## Exam Format (2026)

The exam is fully digital (administered via the **Bluebook app**).

| Section    | Type                             | Questions    | Time       | Weight  |
| ---------- | -------------------------------- | ------------ | ---------- | ------- |
| Section I  | Multiple Choice (4 choices each) | 42 questions | 90 minutes | **55%** |
| Section II | Free Response (FRQ)              | 4 questions  | 90 minutes | **45%** |

**Total exam time**: 3 hours

**Key changes from previous years**:

- MCQ: 5 choices → **4 choices**, question count 40 → **42**
- Weight: 50/50 → **55/45** (MCQ weighs more)
- FRQ typical types: Methods & Control Structures, Class Design, Data Analysis with Array/ArrayList, 2D Array

---

## Units & Topics (2025–2026 New Framework)

| Unit | Topic                     | Exam Weight (MCQ) |
| ---- | ------------------------- | ----------------- |
| 1    | Using Objects and Methods | **15–25%**        |
| 2    | Selection and Iteration   | **25–35%**        |
| 3    | Class Creation            | **10–18%**        |
| 4    | Data Collections          | **30–40%**        |

> Unit 4 (Data Collections) carries the **heaviest weight** on the exam.

### Computational Thinking Practices (MCQ Weight)

The exam also assesses these practices across all units:

| Practice            | Description                                                | MCQ Weight |
| ------------------- | ---------------------------------------------------------- | ---------- |
| **Analyze Code**    | Determine what code does, trace execution, identify errors | **37–53%** |
| **Develop Code**    | Write or complete code to solve a problem                  | **22–38%** |
| **Design Code**     | Choose appropriate data structures and algorithms          | 5–15%      |
| **Document Code**   | Understand and produce comments and documentation          | 2–8%       |
| **Responsible Use** | Ethical and social implications of computing               | 2–8%       |

> "Analyze Code" makes up the largest share of the MCQ — practice tracing code by hand!

---

## Unit 1: Using Objects and Methods (15–25%)

### Key Concepts

- Java basics: variables, data types (`int`, `double`, `boolean`, `String`)
- Expressions, arithmetic operators (`+`, `-`, `*`, `/`, `%`)
- Integer division and type casting
- Creating objects with `new`
- Calling methods on objects
- `String` methods: `length()`, `substring()`, `indexOf()`, `equals()`, `compareTo()`
- `Math` class: `Math.abs()`, `Math.pow()`, `Math.sqrt()`, `Math.random()`
- Wrapper classes: `Integer`, `Double`
- `null` references

### Sample Question 1

```java
int a = 17;
int b = 5;
double c = a / b;
System.out.println(c);
```

**What is printed?**

A) 3.4
B) 3.0
C) 3
D) 3.40

**Answer: B**

> `a / b` performs **integer division** (both are `int`), so `17 / 5 = 3`. The result is then stored in a `double`, becoming `3.0`.

### Sample Question 2

```java
String s = "AP Computer Science";
System.out.println(s.substring(3, 11));
```

**What is printed?**

A) "Compute"
B) "Computer"
C) "Computer "
D) "omputer "

**Answer: B**

> `substring(3, 11)` returns characters at index 3 through 10 (inclusive of start, exclusive of end).
> Index: `A=0, P=1, ' '=2, C=3, o=4, m=5, p=6, u=7, t=8, e=9, r=10, ' '=11`
> Result: `"Computer"`

### Sample Question 3

```java
String a = "Hello";
String b = "Hello";
String c = new String("Hello");

System.out.println(a == b);
System.out.println(a == c);
System.out.println(a.equals(c));
```

**What is printed?**

A) true, true, true
B) true, false, true
C) false, false, true
D) true, false, false

**Answer: B**

> `==` compares **references** (memory addresses). `a` and `b` share the same string literal in the string pool → `true`. `c` is a new object → different reference → `false`. `.equals()` compares **content** → `true`.

### Sample Question 4

```java
int x = (int)(Math.random() * 6) + 1;
```

Which of the following best describes the range of values `x` can take?

A) 0 to 5
B) 1 to 6
C) 0 to 6
D) 1 to 7

**Answer: B**

> `Math.random()` returns `[0.0, 1.0)`. Multiply by 6 → `[0.0, 6.0)`. Cast to `int` → `0, 1, 2, 3, 4, 5`. Add 1 → `1, 2, 3, 4, 5, 6`.

---

## Unit 2: Selection and Iteration (25–35%)

### Key Concepts

- Boolean expressions and operators: `&&`, `||`, `!`
- `if`, `else if`, `else`
- Short-circuit evaluation
- De Morgan's Laws: `!(A && B)` ≡ `!A || !B`
- `while` loops, `for` loops
- Nested loops
- String traversal algorithms
- Basic runtime analysis (counting iterations)
- Comparing objects with `.equals()` vs `==`

### Sample Question 5

```java
int x = 7;
int y = 3;

if (x > 5 && y > 5) {
    System.out.println("A");
} else if (x > 5 || y > 5) {
    System.out.println("B");
} else {
    System.out.println("C");
}
```

**What is printed?**

A) A
B) B
C) C
D) AB

**Answer: B**

> `x > 5 && y > 5` → `true && false` → `false`. Move to `else if`: `x > 5 || y > 5` → `true || false` → `true`. Prints `"B"`.

### Sample Question 6

Which of the following is equivalent to `!(a > b && c == d)`?

A) `a > b && c == d`
B) `a <= b && c != d`
C) `a <= b || c != d`
D) `a < b || c != d`

**Answer: C**

> By De Morgan's Law: `!(A && B)` = `!A || !B`.
> `!(a > b)` = `a <= b`, `!(c == d)` = `c != d`.
> Result: `a <= b || c != d`

### Sample Question 7

```java
int sum = 0;
for (int i = 1; i <= 100; i++) {
    if (i % 3 == 0 && i % 5 == 0) {
        sum += i;
    }
}
System.out.println(sum);
```

**What is printed?**

A) 315
B) 330
C) 345
D) 735

**Answer: A**

> Sums all numbers 1–100 divisible by both 3 and 5 (i.e., multiples of 15): `15 + 30 + 45 + 60 + 75 + 90 = 315`.

### Sample Question 8

```java
String s = "abcdefg";
String result = "";
for (int i = s.length() - 1; i >= 0; i--) {
    result += s.substring(i, i + 1);
}
System.out.println(result);
```

**What is printed?**

A) "abcdefg"
B) "gfedcba"
C) "bcdefga"
D) "gfedcb"

**Answer: B**

> The loop traverses the string backwards, building the reversed string: `"gfedcba"`.

### Sample Question 9

How many times does the following loop execute its body?

```java
int count = 0;
for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 5; j++) {
        count++;
    }
}
```

A) 15
B) 50
C) 10
D) 5

**Answer: B**

> Outer loop runs 10 times, inner loop runs 5 times each → `10 × 5 = 50` total iterations.

---

## Unit 3: Class Creation (10–18%)

### Key Concepts

- Abstraction and information hiding
- Designing classes: instance variables, constructors, methods
- `public` vs `private` access modifiers
- Accessor (getter) and mutator (setter) methods
- `this` keyword
- Variable scope (local vs instance)
- `static` methods and variables

### Sample Question 10

```java
public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}

// In main:
BankAccount acct = new BankAccount(100.0);
acct.deposit(50.0);
acct.withdraw(30.0);
acct.withdraw(200.0);
System.out.println(acct.getBalance());
```

**What is printed?**

A) 100.0
B) 120.0
C) -80.0
D) 0.0

**Answer: B**

> Start: 100.0 → deposit 50.0 → 150.0 → withdraw 30.0 → 120.0 → withdraw 200.0 → `200.0 > 120.0`, condition fails, no withdrawal → 120.0

### Sample Question 11

```java
public class Counter {
    private int count;
    private static int totalCounters = 0;

    public Counter() {
        count = 0;
        totalCounters++;
    }

    public static int getTotalCounters() {
        return totalCounters;
    }
}

// In main:
Counter c1 = new Counter();
Counter c2 = new Counter();
Counter c3 = new Counter();
System.out.println(Counter.getTotalCounters());
```

**What is printed?**

A) 0
B) 1
C) 3
D) An error occurs

**Answer: C**

> `totalCounters` is `static` — shared across all instances. Each `new Counter()` increments it. After 3 objects: `totalCounters = 3`.

---

## Unit 4: Data Collections (30–40%)

This is the **highest-weighted unit** on the exam. It now includes text file processing and data sets — new additions to the 2025–2026 framework.

### Key Concepts

- **Array**: declaration, initialization, traversal, common algorithms
- **2D Array**: row-major traversal, accessing elements
- **ArrayList**: generic, resizable, methods (`add`, `get`, `set`, `remove`, `size`)
- Searching: linear search, binary search
- Sorting: selection sort, insertion sort (conceptual understanding)
- **Recursion**: base case, recursive case, tracing calls
- **NEW — Text file processing**: reading data from text files
- **NEW — Data sets**: analyzing, filtering, and summarizing data
- Ethical considerations related to data collection and use

### Sample Question 12 — Array

```java
int[] arr = {5, 3, 8, 1, 9, 2};
int mystery = arr[0];
for (int i = 1; i < arr.length; i++) {
    if (arr[i] < mystery) {
        mystery = arr[i];
    }
}
System.out.println(mystery);
```

**What is printed?**

A) 9
B) 5
C) 1
D) 2

**Answer: C**

> The code finds the **minimum** value. Starts with `mystery = 5`, updates to `3`, then `1`. Final: `1`.

### Sample Question 13 — ArrayList

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(10);
list.add(20);
list.add(30);
list.add(40);

for (int i = list.size() - 1; i >= 0; i--) {
    if (list.get(i) % 20 == 0) {
        list.remove(i);
    }
}
System.out.println(list);
```

**What is printed?**

A) [10, 30]
B) [10, 20, 30, 40]
C) [30]
D) []

**Answer: A**

> Removes elements divisible by 20. Traversing backwards avoids index-shifting problems. Removes `40` (index 3) and `20` (index 1). Result: `[10, 30]`.

### Sample Question 14 — 2D Array

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

int sum = 0;
for (int r = 0; r < matrix.length; r++) {
    sum += matrix[r][r];
}
System.out.println(sum);
```

**What is printed?**

A) 45
B) 15
C) 12
D) 6

**Answer: B**

> Sums the **diagonal** elements: `matrix[0][0] + matrix[1][1] + matrix[2][2]` = `1 + 5 + 9 = 15`.

### Sample Question 15 — Recursion

```java
public static int mystery(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * mystery(n - 1);
}

System.out.println(mystery(5));
```

**What is printed?**

A) 5
B) 15
C) 120
D) 25

**Answer: C**

> This computes **factorial**: `5! = 5 × 4 × 3 × 2 × 1 = 120`.

### Sample Question 16 — Recursion (Fibonacci)

```java
public static int fib(int n) {
    if (n <= 1) {
        return n;
    }
    return fib(n - 1) + fib(n - 2);
}

System.out.println(fib(6));
```

**What is printed?**

A) 6
B) 8
C) 13
D) 5

**Answer: B**

> Fibonacci sequence: `fib(0)=0, fib(1)=1, fib(2)=1, fib(3)=2, fib(4)=3, fib(5)=5, fib(6)=8`.

### Sample Question 17 — Binary Search

A sorted array has 1024 elements. What is the maximum number of comparisons needed to find an element using binary search?

A) 10
B) 11
C) 512
D) 1024

**Answer: B**

> Binary search halves the search space each step. Worst case: $\lceil \log_2(1024) \rceil + 1 = 10 + 1 = 11$ comparisons (checking the final single-element subarray).

### Sample Question 18 — Text File & Data Analysis (NEW)

Consider a text file `students.txt` with the following format, where each line contains a name and a grade:

```
Alice 92
Bob 85
Carol 78
David 95
Eve 88
```

The following code reads the file and computes the average grade:

```java
Scanner fileScanner = new Scanner(new File("students.txt"));
int total = 0;
int count = 0;

while (fileScanner.hasNext()) {
    String name = fileScanner.next();
    int grade = fileScanner.nextInt();
    total += grade;
    count++;
}
fileScanner.close();

double average = (double) total / count;
System.out.println(average);
```

**What is printed?**

A) 87.0
B) 87.6
C) 88.0
D) 438.0

**Answer: B**

> Total: `92 + 85 + 78 + 95 + 88 = 438`. Count: `5`. Average: `438.0 / 5 = 87.6`.

### Sample Question 19 — Data Filtering (NEW)

```java
ArrayList<Integer> scores = new ArrayList<>();
// scores is populated with: [72, 85, 90, 68, 95, 88, 55, 79]

ArrayList<Integer> passing = new ArrayList<>();
for (int score : scores) {
    if (score >= 80) {
        passing.add(score);
    }
}
System.out.println(passing.size());
```

**What is printed?**

A) 3
B) 4
C) 5
D) 8

**Answer: B**

> Scores ≥ 80: `85, 90, 95, 88` → 4 elements.

---

## Free Response Question (FRQ) — Sample

### Question: WordScrambler

Write a method `scrambleWord` that takes a `String` and returns a new `String` where every pair of adjacent characters is swapped. If the string has an odd number of characters, the last character remains in place.

**Examples**:

- `scrambleWord("Hello")` → `"eHlol"`
- `scrambleWord("ABCD")` → `"BADC"`
- `scrambleWord("A")` → `"A"`

**Solution**:

```java
public static String scrambleWord(String word) {
    String result = "";
    int i = 0;
    while (i < word.length() - 1) {
        result += word.substring(i + 1, i + 2);
        result += word.substring(i, i + 1);
        i += 2;
    }
    if (i < word.length()) {
        result += word.substring(i);
    }
    return result;
}
```

### Question: Data Analysis with File (NEW-style FRQ)

Write a method `countAboveAverage` that takes an `ArrayList<Integer>` of test scores and returns the number of scores that are strictly above the average.

**Solution**:

```java
public static int countAboveAverage(ArrayList<Integer> scores) {
    int total = 0;
    for (int score : scores) {
        total += score;
    }
    double average = (double) total / scores.size();

    int count = 0;
    for (int score : scores) {
        if (score > average) {
            count++;
        }
    }
    return count;
}
```

---

## What's Removed in 2025–2026

The following topics from the old 10-unit framework are **no longer on the AP CSA exam**:

| Removed Topic                     | Old Unit |
| --------------------------------- | -------- |
| Inheritance (`extends`)           | Unit 9   |
| Polymorphism                      | Unit 9   |
| Method overriding (`@Override`)   | Unit 9   |
| `super` keyword                   | Unit 9   |
| Superclass/subclass relationships | Unit 9   |
| Abstract classes and interfaces   | Unit 9   |

> **Important**: If you are using older textbooks or prep materials, skip all inheritance/polymorphism content. It will NOT appear on the 2026 exam.

---

## What's New in 2025–2026

| New Topic                                                        | Unit   |
| ---------------------------------------------------------------- | ------ |
| Text file processing (reading from `.txt` files using `Scanner`) | Unit 4 |
| Data sets — analyzing, filtering, summarizing data               | Unit 4 |
| Ethical considerations in data collection and use                | Unit 4 |

---

## Key Tips for the Exam

1. **Read the question carefully** — now 4 answer choices instead of 5; eliminate wrong answers.
2. **Trace code by hand** — use a table to track variable values step by step.
3. **Watch for off-by-one errors** — arrays are 0-indexed, `substring(a, b)` excludes index `b`.
4. **Integer division truncates** — `7 / 2 = 3`, not `3.5`. Cast to `double` if needed.
5. **`==` vs `.equals()`** — use `.equals()` for `String` and object comparison.
6. **`null` checks** — calling a method on `null` causes `NullPointerException`.
7. **ArrayList removal** — iterate backwards to avoid index shifting issues.
8. **Recursion** — always identify the base case first, then trace the recursive calls.
9. **File I/O (NEW)** — know how to use `Scanner` with `File` to read text data.
10. **Data analysis (NEW)** — practice filtering, counting, averaging data from arrays/ArrayLists.
11. **Time management** — Section I: ~2 min per MCQ. Section II: ~22 min per FRQ.
12. **No inheritance** — don't waste time studying `extends`, `super`, or polymorphism.
