# 🔒 Real-World Hacking Case Studies for Education (교육용 해킹 기술 사례)

> **Purpose**: This document is for educational purposes only. It helps students understand common cybersecurity threats, technical principles, and defense methods.
>
> **주의**: 본 문서는 교육 목적으로만 사용됩니다.

---

## Table of Contents

1. [Key Technical Concepts](#key-technical-concepts)
   - [1–8: Attack Techniques](#1-sql-injection-sql-인젝션)
   - [9: Hash Functions](#9-hash-functions-해시-함수)
   - [10: Key Exchange — Diffie-Hellman](#10-key-exchange--diffie-hellman-디피-헬만-키-교환)
2. [Case 1: 2025 SK Telecom Massive Data Breach](#case-1-2025-sk-telecom-massive-data-breach)
3. [Case 2: 2023 LG U+ User Data Leak](#case-2-2023-lg-u-user-data-leak)
4. [Case 3: 2014 Korean Credit Card Companies Data Breach](#case-3-2014-korean-credit-card-companies-data-breach)
5. [Case 4: 2013 Korea "3·20" Cyber Terror Attack](#case-4-2013-korea-320-cyber-terror-attack)
6. [Case 5: 2011 Nate & Cyworld Data Breach](#case-5-2011-nate--cyworld-data-breach)
7. [Case 6: 2017 WannaCry Global Ransomware](#case-6-2017-wannacry-global-ransomware)
8. [Case 7: 2020 Twitter Celebrity Account Hijacking](#case-7-2020-twitter-celebrity-account-hijacking)
9. [Summary & Discussion](#summary--discussion)

---

## Key Technical Concepts

Before diving into the cases, let's understand the common hacking techniques and security concepts:

### 1. SQL Injection (SQL 인젝션)

**How it works**: Websites use SQL to interact with databases. If a website does not properly filter user input, an attacker can insert malicious SQL code into input fields to directly manipulate the database.

**Example**:

```sql
-- Normal login query:
SELECT * FROM users WHERE username = 'alice' AND password = '123456'

-- Attacker enters username: ' OR '1'='1' --
-- The actual SQL becomes:
SELECT * FROM users WHERE username = '' OR '1'='1' --' AND password = ''
```

> `'1'='1'` is always TRUE, and `--` comments out the password check. The attacker can log in without knowing the password.

**Defense**: Use parameterized queries (Prepared Statements) and strictly validate/sanitize all user inputs.

---

### 2. Phishing (피싱)

**How it works**: The attacker creates a fake website or email that looks nearly identical to the real one, tricking users into entering their credentials.

**Example**:

```
Real URL:     https://www.naver.com
Phishing URL: https://www.naver-login.com  ← Look closely — the domain is different!
```

> An attacker can fully clone a Naver login page's HTML/CSS. Users can hardly tell the difference visually.

**Defense**: Always check the URL carefully, never click suspicious links, enable two-factor authentication (2FA).

---

### 3. Malware / Trojan (악성코드 / 트로이 목마)

**How it works**: Attackers hide malicious code inside seemingly normal programs, documents, or email attachments. Once the user runs it, the malware operates in the background — stealing data or controlling the computer.

**Common distribution methods**:

- Email attachments (e.g., an `.exe` disguised as a PDF)
- Pirated software downloads
- Infected USB drives with autorun

**Technical detail**:

```
Disguised filenames:
  report.pdf.exe        ← Windows hides extensions by default, user only sees "report.pdf"
  게임핵.zip             ← Game hacks often carry trojans (게임핵 = game hack)
```

**Defense**: Never run unknown programs, enable "show file extensions" in Windows, install antivirus software.

---

### 4. DDoS Attack (디도스 공격 / Distributed Denial of Service)

**How it works**: The attacker controls a large number of infected computers (called a "botnet" / 봇넷), and sends a massive flood of requests to a target server simultaneously, overloading it so legitimate users cannot access the service.

**Analogy**: Imagine a restaurant with only 10 tables. Suddenly 10,000 people show up at the same time trying to order. Normal customers simply can't get in.

**Technical detail**:

```
Normal traffic:  ~1,000 requests/sec
DDoS attack:     ~1,000,000 requests/sec  ← Server cannot handle it → crashes
```

**Defense**: Deploy traffic scrubbing systems, use CDN to distribute traffic, configure firewall rules.

---

### 5. Zero-Day Vulnerability (제로데이 취약점)

**How it works**: A security flaw in software that the developer has not yet discovered or patched. Once an attacker finds this type of vulnerability, they can launch an attack with "zero days" of preparation time for defenders.

**Why it's called "Zero-Day"**: The developer has had zero days to fix the vulnerability before it's exploited — meaning they're completely caught off guard.

**Defense**: Keep systems and software updated with the latest patches, use intrusion detection systems (IDS).

---

### 6. Social Engineering (사회공학적 공격)

**How it works**: Instead of attacking technical systems, the attacker exploits human psychology — trust, fear, curiosity — to obtain sensitive information or access.

**Common tactics**:

- Impersonating an IT admin on the phone: _"Your computer has a virus. Please tell me your password so I can fix it."_
- Fake urgent email: _"Your account will be frozen. Verify immediately."_
- Dropping a USB labeled "Salary Report" (급여명세서) in a parking lot — someone will plug it in.

**Defense**: Always verify the identity of who you're talking to, never share sensitive info casually, stay alert.

---

### 7. Credential Stuffing (크리덴셜 스터핑)

**How it works**: Attackers take username/password pairs leaked from Site A and automatically try them on Sites B, C, D, etc. This works because many people reuse the same password across multiple sites.

**Technical detail**:

```
Leaked data from Site A:
  Username: kim_minsu    Password: mypassword123

Attacker automatically tries the same credentials on:
  → Naver      ✓ Success!
  → KakaoTalk  ✓ Success!
  → Gmail      ✗ Failed (different password)
```

**Defense**: Use different passwords for different platforms, use a password manager, enable 2FA.

---

### 8. ARP Spoofing / Man-in-the-Middle Attack (ARP 스푸핑 / 중간자 공격)

**How it works**: On a local network (e.g., school WiFi), the attacker sends forged ARP packets, pretending to be the gateway (router). This lets them intercept all network traffic from other users.

**Analogy**: It's like putting someone else's name on your mailbox — the mail carrier delivers their letters to you.

```
Normal communication:
  Your PC → Router → Internet

After ARP spoofing:
  Your PC → Attacker's PC → Router → Internet
               ↑ Attacker can view and modify your data
```

**Defense**: Use HTTPS for encrypted communication, avoid untrusted WiFi, use a VPN.

---

### 9. Hash Functions (해시 함수)

**What is a Hash?**
A hash function takes any input (a password, a file, a message) and produces a fixed-length output called a **hash value** (also called a "digest" / 해시값). It is a **one-way function** — you can compute the hash from the input, but you **cannot** reverse it to get the original input.

**Key Properties**:
| Property | Meaning |
|---------|---------|
| **Deterministic** | Same input → always same output |
| **Fixed-length output** | No matter the input size, output length is the same |
| **One-way** | Cannot reverse hash → original input |
| **Avalanche effect** | A tiny change in input → completely different output |
| **Collision-resistant** | Extremely hard to find two different inputs that produce the same hash |

**Example using SHA-256**:

```
Input:  "hello"
Output: 2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824

Input:  "hellp"    ← Only 1 letter changed!
Output: 680b19e64060a5fa2527842a1c037de18e2dd5fef9119db60a47c67faa52e4a6
                   ← Completely different hash!
```

**Mathematical Principle**:

A hash function $H$ maps an input $m$ of arbitrary length to a fixed-length output:

$$H: \{0, 1\}^* \rightarrow \{0, 1\}^n$$

For SHA-256, $n = 256$ bits. The function is designed so that:

- **Pre-image resistance**: Given $h$, it is computationally infeasible to find $m$ such that $H(m) = h$
- **Second pre-image resistance**: Given $m_1$, it is infeasible to find $m_2 \neq m_1$ such that $H(m_1) = H(m_2)$
- **Collision resistance**: It is infeasible to find any pair $(m_1, m_2)$ where $m_1 \neq m_2$ and $H(m_1) = H(m_2)$

**Why does this matter in cybersecurity?**

Websites should **never** store your password directly. Instead, they store the hash:

```
You sign up with password: "MySecret123"
Database stores:   hash("MySecret123") = a8f5f167...

When you log in:
  You type "MySecret123" → server computes hash → compares with stored hash
  Match ✓ → Login success

If a hacker steals the database:
  They only see "a8f5f167..." → cannot reverse it to get "MySecret123"
```

**Salt (솔트)**: To prevent attackers from using precomputed tables (rainbow tables / 레인보우 테이블), a random value called "salt" is added before hashing:

$$\text{stored} = H(\text{salt} \| \text{password})$$

```
User A password: "123456"  salt: "x9f2"  →  hash("x9f2" + "123456") = 7a3c91...
User B password: "123456"  salt: "k4m8"  →  hash("k4m8" + "123456") = e2b5f0...
                                              ← Same password, different hashes!
```

**Common Hash Algorithms**:
| Algorithm | Output Length | Status |
|-----------|-------------|--------|
| MD5 | 128 bits | ❌ Broken — collisions found |
| SHA-1 | 160 bits | ⚠️ Deprecated — Google demonstrated collision in 2017 |
| SHA-256 | 256 bits | ✅ Currently secure |
| bcrypt | 184 bits | ✅ Designed for passwords (intentionally slow) |

---

### 10. Key Exchange — Diffie-Hellman (디피-헬만 키 교환)

**The Problem**: How can two people (Alice and Bob) agree on a secret key over a public channel where anyone can eavesdrop?

This is the fundamental problem that the **Diffie-Hellman Key Exchange** (1976) solves. It's used every time you visit an HTTPS website.

**Analogy — The Color Mixing Model**:

```
Imagine colors that can be mixed but NOT unmixed:

1. Alice and Bob publicly agree on a common color:        YELLOW
2. Alice picks a secret color:                            RED    (only she knows)
3. Bob picks a secret color:                              BLUE   (only he knows)
4. Alice mixes YELLOW + RED → sends ORANGE to Bob         (public)
5. Bob mixes YELLOW + BLUE → sends GREEN to Alice         (public)
6. Alice takes GREEN + her secret RED  → BROWN
7. Bob takes ORANGE + his secret BLUE  → BROWN

Both get BROWN — the shared secret!
An eavesdropper sees YELLOW, ORANGE, GREEN but CANNOT figure out BROWN.
```

**Mathematical Principle**:

Alice and Bob publicly agree on two numbers:

- A large prime $p$
- A generator $g$ (a primitive root modulo $p$)

Then:

| Step                     | Alice            | Bob                |
| ------------------------ | ---------------- | ------------------ |
| 1. Choose secret         | Picks secret $a$ | Picks secret $b$   |
| 2. Compute public value  | $A = g^a \mod p$ | $B = g^b \mod p$   |
| 3. Exchange              | Sends $A$ to Bob | Sends $B$ to Alice |
| 4. Compute shared secret | $s = B^a \mod p$ | $s = A^b \mod p$   |

Both Alice and Bob arrive at the **same** shared secret:

$$s = g^{ab} \mod p$$

**Why?**

$$B^a \mod p = (g^b)^a \mod p = g^{ab} \mod p$$
$$A^b \mod p = (g^a)^b \mod p = g^{ab} \mod p$$

**Concrete Example with small numbers** (real systems use numbers with 2048+ bits):

```
Public values:  p = 23,  g = 5

Alice's secret: a = 6
Alice computes: A = 5^6 mod 23 = 15625 mod 23 = 8     → sends 8 to Bob

Bob's secret:   b = 15
Bob computes:   B = 5^15 mod 23 = 30517578125 mod 23 = 19   → sends 19 to Alice

Alice computes: s = 19^6 mod 23  = 47045881 mod 23  = 2
Bob computes:   s = 8^15 mod 23  = 35184372088832 mod 23 = 2

Shared secret:  s = 2  ✓  (Both get the same value!)
```

**Why is it secure?**

An eavesdropper (Eve) sees: $p = 23$, $g = 5$, $A = 8$, $B = 19$

To find the secret, Eve needs to solve the **Discrete Logarithm Problem (이산 로그 문제)**:

$$\text{Given } g^a \mod p = 8, \text{ find } a = ?$$

With small numbers this is easy, but when $p$ is a prime with **600+ digits**, this becomes computationally infeasible — even for the world's fastest supercomputers.

**Where is this used?**

```
When you visit https://www.naver.com:

1. Your browser and Naver's server perform Diffie-Hellman key exchange
2. They agree on a shared secret key
3. All subsequent communication is encrypted with this key (AES)
4. Even if someone intercepts the traffic, they can't decrypt it

This is why the 🔒 lock icon in your browser matters!
```

**Evolution**:
| Algorithm | Year | Status |
|-----------|------|--------|
| Diffie-Hellman (DH) | 1976 | ✅ Still used (with large key sizes) |
| Elliptic Curve DH (ECDH / 타원곡선 DH) | 1985 | ✅ Preferred — same security with smaller keys |
| Post-Quantum (양자내성) | Ongoing | 🔬 Being developed to resist quantum computers |

---

## Case 1: 2025 SK Telecom Massive Data Breach

| Item       | Details                                                       |
| ---------- | ------------------------------------------------------------- |
| **When**   | April 2025                                                    |
| **Victim** | SK Telecom (SK텔레콤) — South Korea's largest telecom carrier |
| **Impact** | USIM-related data of ~23 million users leaked                 |

### What Happened

On April 19, 2025, SK Telecom detected anomalies in its internal servers and discovered that hackers had breached its core systems. The attackers stole massive amounts of USIM (SIM card) data, including:

- **IMSI** (International Mobile Subscriber Identity / 국제모바일가입자식별번호)
- **IMEI** (International Mobile Equipment Identity / 단말기 고유번호)
- **Authentication keys** (인증 키)

This became one of the largest data breaches in Korean telecom history. SK Telecom activated emergency response protocols, reported to the government, and notified affected users. The Ministry of Science and ICT (과학기술정보통신부) launched a comprehensive investigation.

### Technical Breakdown

- **Zero-Day Exploit**: Attackers exploited an unknown vulnerability in SK Telecom's internal systems.
- **APT (Advanced Persistent Threat / 지능형 지속 위협)**: Hackers remained hidden inside the system for an extended period, collecting data before being detected.
- **SIM Cloning Risk**: The leaked USIM data could theoretically be used for "SIM cloning" (유심 복제), allowing attackers to impersonate users' mobile identities.

### Aftermath

- SK Telecom offered free SIM card replacements for all users.
- The Korean government strengthened cybersecurity regulations for the telecom industry.
- Accelerated amendments to Korea's Personal Information Protection Act (개인정보보호법).

### Classroom Discussion

> 💡 What kind of data is stored on a SIM card? Why is a SIM data breach potentially more dangerous than a stolen email password?

---

## Case 2: 2023 LG U+ User Data Leak

| Item       | Details                                                  |
| ---------- | -------------------------------------------------------- |
| **When**   | January 2023                                             |
| **Victim** | LG U+ (LG유플러스) — Korea's 3rd largest telecom carrier |
| **Impact** | Personal information of ~290,000 users leaked            |

### What Happened

In January 2023, LG U+ confirmed a cyberattack that resulted in ~290,000 users' personal data (names, birthdates, phone numbers, etc.) being sold on the dark web. Investigation revealed severe security management failures, including **unencrypted storage of user data** and **lack of security audits**.

### Technical Breakdown

- **Unencrypted Database**: Sensitive user information was stored in **plaintext** rather than being encrypted.
  ```
  Plaintext:   김민수 | 010-1234-5678 | 1995-03-15
  Encrypted:   a4f2c8... | 7e9b1d... | f3a8e2...   ← Even if stolen, data is unreadable
  ```
- **Insufficient Access Control**: Internal systems lacked strict permission management.

### Aftermath

- LG U+ was fined by the Personal Information Protection Commission (개인정보보호위원회).
- Drove security standard upgrades across the Korean telecom industry.

### Classroom Discussion

> 💡 Why should user data in databases be stored encrypted? What's the difference between "plaintext" (평문) and "ciphertext" (암호문)?

---

## Case 3: 2014 Korean Credit Card Companies Data Breach

| Item       | Details                                                                            |
| ---------- | ---------------------------------------------------------------------------------- |
| **When**   | January 2014                                                                       |
| **Victim** | KB Kookmin Card (KB국민카드), Lotte Card (롯데카드), NH Nonghyup Card (NH농협카드) |
| **Impact** | ~20 million users (Korea's total population is ~50 million)                        |

### What Happened

An IT contractor from Korea Credit Bureau (KCB / 한국신용평가), while performing system maintenance for the three credit card companies, used his legitimate access to copy ~20 million users' personal data — including names, national ID numbers, credit card numbers, and addresses — onto a USB drive. He then sold the data to marketing companies.

### Technical Breakdown

- **Insider Threat (내부자 위협)**: A person with legitimate access intentionally steals data. This is one of the hardest threats to defend against.
- **USB Data Exfiltration**: Physical device was used to copy data, bypassing network-level security monitoring entirely.
  ```
  Network firewall: ✓ Active — monitors all network traffic
  USB port:         ✗ Unmonitored — data walks out the door on a thumb drive
  ```
- **Excessive Privileges**: An outsourced contractor had far too much access to sensitive data.

### Aftermath

- CEOs of all three credit card companies resigned.
- Korea implemented comprehensive data security regulations for the financial industry.
- USB port control became a standard security requirement in Korean enterprises.

### Classroom Discussion

> 💡 This case shows that many data breaches don't come from external hackers, but from "insiders." How should companies manage employee data access? What is the "Principle of Least Privilege" (최소 권한 원칙)?

---

## Case 4: 2013 Korea "3·20" Cyber Terror Attack (3·20 사이버테러)

| Item        | Details                                                                                    |
| ----------- | ------------------------------------------------------------------------------------------ |
| **When**    | March 20, 2013                                                                             |
| **Victims** | KBS, MBC, YTN (major TV networks), Shinhan Bank (신한은행), Nonghyup Bank (농협은행), etc. |
| **Impact**  | ~48,000 computers destroyed                                                                |

### What Happened

At 2:00 PM on March 20, 2013, computers at Korea's three major TV networks and several banks simultaneously went dark. Screens displayed a skull image. Hard drive data was overwritten and erased. This attack, known as "3·20 사이버테러," affected approximately 48,000 computers and servers.

### Technical Breakdown

- **Supply Chain Attack (공급망 공격)**: Hackers compromised the internal **patch management server** (소프트웨어 업데이트 서버) and disguised malware as a normal software update, pushing it to all connected computers.

  ```
  Normal update flow:
    Patch Server → pushes "security_update_v2.exe" → All company PCs install it ✓

  Compromised flow:
    Patch Server (hacked) → pushes "malware_disguised_as_update.exe" → All PCs infected ✗
  ```

- **MBR Overwrite**: The malware destroyed the **Master Boot Record (MBR)** of each hard drive, making computers unable to boot.
  ```
  What is MBR (마스터 부트 레코드)?
    The first sector of a hard drive (512 bytes).
    Contains critical information needed to start the operating system.
    If the MBR is overwritten or deleted → the computer cannot start.
  ```
- **Time-Triggered Activation**: The malware was programmed to activate at a specific time (2:00 PM) simultaneously across all infected machines, causing synchronized mass failure.

### Aftermath

- Korea established the Cyber Security Command (사이버안보사령부) for national-level cyber defense.
- Companies began prioritizing the security of internal update systems.

### Classroom Discussion

> 💡 Why did the hackers target the "software update server" instead of attacking each computer individually? Why is this type of attack (supply chain attack) particularly dangerous?

---

## Case 5: 2011 Nate & Cyworld Data Breach

| Item       | Details                                                                     |
| ---------- | --------------------------------------------------------------------------- |
| **When**   | July 2011                                                                   |
| **Victim** | Nate portal & Cyworld social network (SK Communications / SK커뮤니케이션즈) |
| **Impact** | ~35 million users (nearly 70% of Korea's population at the time)            |

### What Happened

In July 2011, hackers breached SK Communications' Nate and Cyworld platforms, stealing personal information of ~35 million users — including usernames, password hashes, real names, phone numbers, emails, and national ID numbers (주민등록번호).

### Technical Breakdown

- **Trojan via Trusted Software**: Hackers compromised the update server of **ESTsoft's ALZip** (a popular free compression tool in Korea, similar to WinRAR). Through this, they distributed a trojan to millions of computers.
- **Supply Chain Attack**: Instead of attacking the target directly, hackers compromised a widely trusted third-party software to reach the target indirectly.
  ```
  Attack chain:
    Hack ALZip update server → Users download "ALZip update" (actually a trojan)
    → Trojan installed on millions of PCs → Use trojan to access Nate/Cyworld servers
  ```

### Aftermath

- This incident was one of the key reasons Korea abolished the requirement to collect national ID numbers (주민등록번호) for website registration, ending part of its "real-name internet" (인터넷 실명제) policy.

### Classroom Discussion

> 💡 At the time, almost all Korean websites required your national ID number to sign up. After this breach, Korea removed that requirement. What does this tell us about the relationship between data collection and data risk?

---

## Case 6: 2017 WannaCry Global Ransomware

| Item       | Details                                      |
| ---------- | -------------------------------------------- |
| **When**   | May 2017                                     |
| **Impact** | 150+ countries, 300,000+ computers worldwide |

### What Happened

On May 12, 2017, the WannaCry ransomware (워너크라이 랜섬웨어) spread explosively across the globe. It exploited a Windows SMB protocol vulnerability called **"EternalBlue"**, spreading automatically without user interaction. Once infected, all files were encrypted and a ransom in Bitcoin was demanded. In Korea, **CGV cinemas** (CGV 영화관), hospitals, and various businesses were affected.

### Technical Breakdown

- **Exploit Used**: Windows SMBv1 remote code execution vulnerability (MS17-010).
- **Worm-like Propagation**: No user click needed — the virus automatically spread across networks.
  ```
  Propagation flow:
    Infected PC A → Scans local network → Finds PC B with port 445 open
    → Exploits EternalBlue vulnerability → Automatically infects PC B → Continues spreading...
  ```
- **File Encryption**: Used RSA + AES encryption algorithms to lock user files. Virtually irreversible without the decryption key.
  ```
  Before infection:                After infection:
    homework.docx                    homework.docx.WNCRY
    family_photo.jpg      →          family_photo.jpg.WNCRY
    project.pptx                     project.pptx.WNCRY
                                     + "Pay $300 in Bitcoin to decrypt"
  ```
- **Bitcoin Ransom**: Used Bitcoin's anonymity to collect payments, making it nearly impossible to trace.

### Aftermath

- Microsoft had released patch MS17-010 **two months before** WannaCry hit — but millions of users hadn't updated.
- Governments worldwide strengthened cybersecurity policies.

### Classroom Discussion

> 💡 Why is keeping your OS updated so important? Microsoft released the fix 2 months before the attack, yet so many systems were unpatched. What does this tell us?

---

## Case 7: 2020 Twitter Celebrity Account Hijacking

| Item         | Details                                                                        |
| ------------ | ------------------------------------------------------------------------------ |
| **When**     | July 2020                                                                      |
| **Victims**  | Twitter accounts of Bill Gates, Elon Musk, Barack Obama, and other celebrities |
| **Attacker** | A 17-year-old teenager from Florida, USA                                       |

### What Happened

On July 15, 2020, multiple high-profile Twitter accounts were hijacked and posted Bitcoin scam messages like: _"Send $1,000 to this address and I'll send $2,000 back."_

Investigation revealed that a **17-year-old** from Florida used **social engineering** — he called Twitter employees pretending to be an IT department staff member and tricked them into giving him access to internal admin tools.

### Technical Breakdown

- **Social Engineering**: Gained internal system access through phone-based deception — no code, no exploits, just manipulation.
  ```
  Attacker's approach:
    1. Research Twitter employees on LinkedIn
    2. Call employee, impersonate IT support
    3. "Hi, this is IT. We need to verify your credentials for a security audit."
    4. Employee provides login → Attacker accesses internal admin panel
    5. Admin panel → can control ANY Twitter account
  ```
- **Internal Admin Tool Abuse**: Once inside the admin panel, the attacker could reset email addresses, disable 2FA, and take over any account.

### Classroom Discussion

> 💡 This case proves: even the strongest technical defenses can't stop the "human" weakness. How did a 17-year-old break into one of the world's biggest tech companies? What does this say about the importance of employee security training?

---

## Summary & Discussion

### Attack Methods Overview

| Attack Method                         | Related Case              | Core Weakness        |
| ------------------------------------- | ------------------------- | -------------------- |
| Zero-Day Exploit (제로데이)           | SK Telecom (2025)         | Software flaws       |
| Insider Theft (내부자 위협)           | Credit Card Breach (2014) | Personnel management |
| Supply Chain Attack (공급망 공격)     | 3·20 Attack, Nate Breach  | Trust chain          |
| Social Engineering (사회공학)         | Twitter Hijacking (2020)  | Human psychology     |
| Worm Virus (웜 바이러스)              | WannaCry (2017)           | Unpatched systems    |
| Poor Data Management                  | LG U+ (2023)              | Security awareness   |
| Credential Stuffing (크리덴셜 스터핑) | Various account breaches  | Password reuse       |

### Security Tips for Students

1. **Password Hygiene**: Use different passwords for different sites. Include letters, numbers, and symbols.
2. **Update Regularly**: Always install OS and software security patches promptly.
3. **Watch for Phishing**: Don't click suspicious links. Always verify URLs.
4. **Enable 2FA**: Turn on two-factor authentication for important accounts.
5. **Avoid Shady Downloads**: Especially game hacks (게임핵), cracked software, and unknown attachments.
6. **Be Careful on Public WiFi**: Don't log into important accounts on public networks.
7. **The White Hat Path (화이트 해커)**: Being interested in hacking is great! Channel it into cybersecurity (사이버보안). Join CTF competitions. Become a legal "white hat hacker."

### Recommended Learning Resources

- **CTF Competitions**: Capture The Flag — cybersecurity contests, great for beginners.
- **KISA** (한국인터넷진흥원 / Korea Internet & Security Agency): Provides cybersecurity education resources for young learners.
- **Hack The Box / TryHackMe**: Legal online platforms to practice hacking skills safely.
- **OverTheWire (Bandit)**: A beginner-friendly wargame to learn Linux and basic security concepts.

---

> ⚠️ **Disclaimer**: All technical explanations in this document are for **educational purposes only**, to help students understand cybersecurity concepts. Any unauthorized computer intrusion is **illegal**. Korea's _Act on Promotion of Information and Communications Network Utilization and Information Protection_ (정보통신망 이용촉진 및 정보보호 등에 관한 법률) has clear legal penalties for such activities.
