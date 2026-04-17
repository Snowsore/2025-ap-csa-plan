# AP Computer Science Principles (AP CSP) — Quick Guide & Sample Questions

> **Updated for 2025–2026** (applies to the May 2026 exam).

## Course Overview

AP Computer Science Principles explores the **big ideas** of computing — creativity, abstraction, data, algorithms, programming, the internet, and the global impact of computing. Unlike AP CSA, it is **not focused on a single programming language** and does not require Java. Students typically use Python, JavaScript, or block-based languages.

---

## Exam Format

| Component                   | Type                   | Details                     | Weight |
| --------------------------- | ---------------------- | --------------------------- | ------ |
| **Create Performance Task** | Through-course project | Program + written responses | 30%    |
| **End-of-Course Exam**      | Multiple Choice        | 70 questions, 120 minutes   | 70%    |

- **Single-select** MCQ: 57 questions
- **Reading-passage-based** MCQ: 5 questions (based on a computing innovation passage)
- **Multi-select** MCQ: 8 questions (select TWO correct answers)
- The exam uses a **reference sheet** with pseudocode conventions

---

## Big Ideas

| Big Idea | Topic                         | Exam Weight |
| -------- | ----------------------------- | ----------- |
| 1        | Creative Development          | 10–13%      |
| 2        | Data                          | 17–22%      |
| 3        | Algorithms and Programming    | 30–35%      |
| 4        | Computer Systems and Networks | 11–15%      |
| 5        | Impact of Computing           | 21–26%      |

---

## AP CSP Pseudocode Reference

The exam uses a standardized pseudocode. Here are the essentials:

### Assignment & Display

```
a ← 5              // Assign value 5 to variable a
DISPLAY(a)          // Output the value of a
```

### Arithmetic

```
a + b       a - b       a * b       a / b       a MOD b
```

### Comparison

```
a = b       a ≠ b       a > b       a < b       a ≥ b       a ≤ b
```

### Boolean Logic

```
NOT condition
condition1 AND condition2
condition1 OR condition2
```

### Conditionals

```
IF (condition)
{
    <block of statements>
}
ELSE
{
    <block of statements>
}
```

### Loops

```
REPEAT n TIMES
{
    <block of statements>
}

REPEAT UNTIL (condition)
{
    <block of statements>
}
```

### Lists (1-indexed!)

```
list ← [10, 20, 30]
list[1]                     // Returns 10 (1-indexed, NOT 0-indexed!)
INSERT(list, i, value)      // Inserts value at index i, shifting elements right
APPEND(list, value)         // Adds value to end of list
REMOVE(list, i)             // Removes element at index i, shifting elements left
LENGTH(list)                // Returns number of elements
FOR EACH item IN list       // Iterates through each element
{
    <block of statements>
}
```

### Procedures (Functions)

```
PROCEDURE name(param1, param2)
{
    <block of statements>
    RETURN value
}
```

### Robot (Grid Navigation)

```
MOVE_FORWARD()              // Move one square forward
ROTATE_LEFT()               // Turn left 90 degrees
ROTATE_RIGHT()              // Turn right 90 degrees
CAN_MOVE(direction)         // Returns true if robot can move in that direction
                            // direction: forward, backward, left, right
```

> **Important**: AP CSP lists are **1-indexed**, not 0-indexed like Java/Python!

---

## Big Idea 1: Creative Development

### Key Concepts

- Program development process: investigate, design, prototype, test
- Collaboration in programming
- Identifying and correcting errors
- Types of errors: **syntax**, **logic**, **runtime**
- Documentation and commenting

### Sample Question 1

A team of students is developing a program. Which of the following is MOST likely to help them identify logic errors?

A) Using a syntax checker  
B) Compiling the program  
C) Testing the program with a variety of inputs  
D) Checking for proper indentation

**Answer: C**

> Logic errors produce incorrect results but don't cause crashes or compile errors. Only **testing with diverse inputs** reveals unexpected behavior.

### Sample Question 2

A student writes a program that is supposed to calculate the average of a list of numbers. The program runs without error but always outputs a number slightly higher than expected. What type of error is this?

A) Syntax error  
B) Runtime error  
C) Logic error  
D) Overflow error

**Answer: C**

> The program runs fine but produces wrong results — this is a **logic error**.

---

## Big Idea 2: Data

### Key Concepts

- Binary and data representation
- Data compression (lossy vs lossless)
- Extracting information from data
- Metadata
- Cleaning data
- Data visualization

### Sample Question 3

How many distinct values can be represented using 6 bits?

A) 6  
B) 12  
C) 32  
D) 64  
E) 128

**Answer: D**

> With $n$ bits, you can represent $2^n$ distinct values. $2^6 = 64$.

### Sample Question 4

A photo editing application offers two save options:

- Option A: Saves the image with no quality loss, file size = 12 MB
- Option B: Saves the image with slight quality reduction, file size = 2 MB

Which of the following best describes Options A and B?

A) Option A uses lossy compression; Option B uses lossless compression  
B) Option A uses lossless compression; Option B uses lossy compression  
C) Both use lossy compression  
D) Both use lossless compression

**Answer: B**

> **Lossless** = no data lost, larger file. **Lossy** = some data discarded, smaller file.

### Sample Question 5

A data set contains the following information about students: name, grade level, GPA, and number of absences. Which of the following questions could NOT be answered using only this data set?

A) What is the average GPA of 10th graders?  
B) Which grade level has the most absences?  
C) What is the relationship between absences and GPA?  
D) What courses do students with the highest GPAs take?

**Answer: D**

> The data set has no information about **courses**. Therefore, this question cannot be answered.

---

## Big Idea 3: Algorithms and Programming

### Key Concepts

- Variables, assignments, and data types
- Algorithms: sequencing, selection, iteration
- Procedures (functions) and parameters
- Lists and list operations
- Searching: linear search, binary search
- Sorting (conceptual)
- Algorithmic efficiency and reasonable time
- Undecidable problems

### Sample Question 6

```
x ← 10
y ← 5
z ← x + y
x ← z - x
DISPLAY(x)
DISPLAY(y)
DISPLAY(z)
```

What is displayed?

A) 10 5 15  
B) 5 5 15  
C) 15 5 15  
D) 5 10 15

**Answer: B**

> `x = 10, y = 5, z = 10 + 5 = 15, x = 15 - 10 = 5`. Display: `5 5 15`.

### Sample Question 7

```
list ← [3, 7, 1, 9, 4]
result ← list[1]
FOR EACH item IN list
{
    IF (item > result)
    {
        result ← item
    }
}
DISPLAY(result)
```

What is displayed?

A) 1  
B) 3  
C) 9  
D) 4

**Answer: C**

> `result` starts at `list[1] = 3` (1-indexed!). The loop finds the maximum: 3 → 7 → 9. Final: `9`.

### Sample Question 8

A sorted list of 1,000 elements is being searched. How many comparisons does **binary search** need in the worst case?

A) 10  
B) 500  
C) 1,000  
D) 100

**Answer: A**

> Binary search halves the search space each time. Worst case: $\lceil \log_2(1000) \rceil = 10$ comparisons.

### Sample Question 9

Which of the following algorithms has a running time that is considered **unreasonable** (not polynomial time)?

A) Linear search through a list of $n$ items  
B) Binary search through a sorted list of $n$ items  
C) Checking all possible subsets of a set of $n$ items  
D) Sorting a list of $n$ items using merge sort

**Answer: C**

> Checking all subsets takes $2^n$ time — **exponential**. This is unreasonable for large $n$. The others run in polynomial time.

### Sample Question 10

```
PROCEDURE mystery(a, b)
{
    IF (b = 0)
    {
        RETURN a
    }
    ELSE
    {
        RETURN mystery(b, a MOD b)
    }
}

DISPLAY(mystery(12, 8))
```

What is displayed?

A) 0  
B) 2  
C) 4  
D) 8

**Answer: C**

> This is the **Euclidean algorithm** (GCD).
> `mystery(12, 8)` → `mystery(8, 4)` → `mystery(4, 0)` → returns `4`.

---

## Big Idea 4: Computer Systems and Networks

### Key Concepts

- Internet protocols: TCP, IP, HTTP, DNS
- How the internet works: packets, routing
- Fault tolerance and redundancy
- Parallel and distributed computing
- Cybersecurity: encryption, public key, DDoS, phishing

### Sample Question 11

What is the primary purpose of the **Domain Name System (DNS)**?

A) To encrypt data sent over the internet  
B) To translate domain names into IP addresses  
C) To route packets across the network  
D) To break data into smaller packets

**Answer: B**

> DNS translates human-readable names (e.g., `www.google.com`) to IP addresses (e.g., `142.250.80.46`).

### Sample Question 12

The internet was designed to be **fault-tolerant**. Which of the following best describes what this means?

A) No data is ever lost during transmission  
B) The network can function even if some connections fail  
C) All data is encrypted during transmission  
D) Every device on the network has a unique address

**Answer: B**

> Fault tolerance means the network has **redundant paths**, so if one connection fails, data can be rerouted.

### Sample Question 13

In **public key encryption**, a user has a public key and a private key. Which of the following correctly describes how a message is sent securely?

A) The sender encrypts with the sender's private key; the receiver decrypts with the sender's public key  
B) The sender encrypts with the receiver's public key; the receiver decrypts with the receiver's private key  
C) The sender encrypts with the receiver's private key; the receiver decrypts with the receiver's public key  
D) Both parties use the same shared key for encryption and decryption

**Answer: B**

> Public key encryption: encrypt with the **receiver's public key** (anyone can do this), decrypt with the **receiver's private key** (only they have it).

### Sample Question 14

Which of the following is an advantage of **parallel computing**?

A) It guarantees faster execution for all types of problems  
B) It can reduce the time needed to solve problems that can be broken into independent tasks  
C) It eliminates the need for network connections  
D) It uses less total computing resources than sequential computing

**Answer: B**

> Parallel computing speeds up problems that can be **divided into independent subtasks**. It doesn't help all problems and doesn't reduce total resources (actually uses more).

---

## Big Idea 5: Impact of Computing

### Key Concepts

- Digital divide
- Bias in algorithms and data
- Crowdsourcing
- Legal and ethical concerns: intellectual property, copyright, open source
- Privacy and data collection
- Safe computing: phishing, malware, keyloggers

### Sample Question 15

A company uses an algorithm to screen job applications. The algorithm was trained on data from the past 10 years, during which the company hired mostly male employees. What is the most likely concern?

A) The algorithm will be too slow  
B) The algorithm may have a bias against female applicants  
C) The algorithm will not be able to process applications  
D) The algorithm will always select the best candidate

**Answer: B**

> If the training data is biased (mostly male hires), the algorithm will learn and perpetuate that **bias**, unfairly disadvantaging female applicants.

### Sample Question 16

Which of the following is an example of the **digital divide**?

A) Students in a school disagree about which programming language to learn  
B) A rural community has limited access to high-speed internet  
C) A software company chooses to use proprietary software instead of open source  
D) A website requires users to create an account before accessing content

**Answer: B**

> The digital divide refers to **unequal access** to technology and the internet, often affecting rural, low-income, or developing communities.

### Sample Question 17

A social media platform collects users' location data, browsing history, and contacts to personalize advertisements. Which of the following is a privacy concern related to this practice? (Select TWO)

A) Users may not be aware of the extent of data collected about them  
B) The platform may display fewer advertisements than expected  
C) The collected data could be accessed by unauthorized parties through a data breach  
D) The platform's user interface may become more complex

**Answer: A and C**

> Privacy concerns include: (A) lack of transparency about data collection, and (C) risk of data breach exposing personal information.

---

## Create Performance Task (CPT) — Overview

The Create Performance Task is a **through-course project** worth **30%** of your score. You submit:

1. **Program Code** — A program you created that includes:
   - Input from user, device, or file
   - Use of a **list** (or equivalent)
   - A student-developed **procedure** with a parameter
   - An **algorithm** that includes sequencing, selection, and iteration

2. **Video** — Up to 1 minute, showing the program running with input/output

3. **Written Responses** — 2 written questions answered during the exam, based on your Create Task:
   - How the list manages complexity
   - How the procedure/algorithm works

   The Create Task must be submitted **before** the exam date.

### Common Mistakes to Avoid

- Procedure must have a **parameter** that affects its behavior
- List must be essential — not just decorative
- Algorithm must include **all three**: sequencing + selection + iteration
- Don't use APIs that do all the work for you (the algorithm must be yours)

---

## AP CSA vs AP CSP — Comparison (2025–2026)

| Aspect                 | AP CSA (2025–2026)                                                | AP CSP                                   |
| ---------------------- | ----------------------------------------------------------------- | ---------------------------------------- |
| **Language**           | Java only                                                         | Any (Python, JS, pseudocode)             |
| **Focus**              | Programming, class design, data collections                       | Big ideas of computing                   |
| **Units**              | 4 units (Objects, Selection/Iteration, Classes, Data Collections) | 5 Big Ideas                              |
| **Exam**               | 42 MCQ (4 choices) + 4 FRQ                                        | 70 MCQ + Create Task                     |
| **Exam weight**        | MCQ 55% / FRQ 45%                                                 | MCQ 70% / Create Task 30%                |
| **Key changes (2026)** | Inheritance removed; text files & data sets added                 | No major changes                         |
| **Difficulty**         | More technical, code-heavy                                        | More conceptual, broader scope           |
| **Math needed**        | Moderate (logic, arrays, recursion)                               | Light (binary, basic logic)              |
| **Best for**           | Students who want to code deeply                                  | Students exploring CS for the first time |
| **College credit**     | Typically CS1/CS2                                                 | Typically introductory CS                |

---

## Key Tips for the AP CSP Exam

1. **Know the pseudocode** — The reference sheet is provided, but you should be fluent with it.
2. **Lists are 1-indexed** — `list[1]` is the first element, not `list[0]`.
3. **Binary math** — Be comfortable converting between binary and decimal.
4. **Trace code carefully** — Use a table to track variables step by step.
5. **"Reasonable time"** — Polynomial = reasonable; Exponential = unreasonable.
6. **Internet concepts** — Understand TCP/IP, DNS, packets, routing, fault tolerance.
7. **Bias and ethics** — Many questions test your understanding of real-world impacts.
8. **Multi-select questions** — Read all options before choosing. You must get BOTH right.
9. **Create Task** — Start early, make sure your procedure has a parameter and your algorithm uses sequencing + selection + iteration.
10. **Time management** — ~1.7 minutes per MCQ. Don't get stuck on one question.
