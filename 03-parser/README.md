# Analiză sintactică 1 - Specificații sintactice

## Intro

- Obiectivul laboratorului este implementarea unui analizor sintactic pentru limbajul CPLang, introdus la laboratorul anterior.
- Descărcați arhiva ce conține scheletul laboratorului.
- Înainte de parcurgerea exercițiilor, citiți cu atenție scheletul de cod. Pentru fiecare exercițiu, găsiți în cod explicații și sugestii de implementare. Umăriți marcajele **`TODOx`** unde `x` este numărul exercițiului. Sursele de interes sunt **`CPLangParser.g4`** și **`Test.java`**.

## Ex. 1. Specificația sintactică a limbajului CPLang

- Completați gramatica din fișierul **`CPLangParser.g4`**, urmărind marcajele **`TODO1`**, astfel încât să puteți analiza programe CPLang. Pentru a testa o anumită regulă sintactică, faceți click dreapta pe numele regulii în specificație și selectați `Test rule`. În panoul din stânga puteți introduce un șir de analizat, iar în panoul din dreapta puteți vizualiza arborele de derivare.
- În final, urmariți marcajele **`TODO1`** din **`Test.java`**.
- În metoda **`main`** se apelează metoda corespunzătoare exercițiului în funcție de parametrul primit din linia de comandă. În cazul de față, parametrul este 1. Fișierul de intrare este `manual.txt`.

## Ex. 2. Extragerea de statistici din cod

- Implementați, folosind ***listeners*** și urmărind marcajele **`TODO2`** din **`Test.java`**, o parcurgerere a arborelui de
derivare, ce afișează:
  - numărul de definiții de **variabile** (atât **globale** cât și ca **parametri formali** ai unor funcții)
  - numărul de **funcții** definite.
- Pentru testare, utilizați 2 ca parametru în linia de comandă.

Pentru următoarea secvență:

    Int a;
    Int b = 1; 
    Bool c = a == b;
    Int increment(Int a) {a + 1};
    print_out(increment(a));

se va afișa:

    Definiții de variabile: 4
    Definiții de funcții: 1

## Ex. 3. Evaluator de expresii aritmetice

- Implementați, folosind ***visitors***, un evaluator de expresii aritmetice simple. Expresiile vor fi separate de `;` și vor conține doar literali numerici întregi, paranteze, și operatorii `+-*/`. **Atenție** la ordinea obișnuită a operațiilor!
- Urmăriți marcajele **`TODO3`** din **`CPLangParser.g4`** și **`Test.java`**.
- Pentru testare, utilizați 3 ca parametru în linia de comandă.  Fișierul de intrare este `input3.txt`, iar referința este `reference3.txt`.

Exemplu de intrare:

    (3*2+6)/2;
    8+8*2;
    1+1/2;

Exemplu de ieșire:

    6
    24
    1
