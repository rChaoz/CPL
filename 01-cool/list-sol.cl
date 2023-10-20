(*
    Laborator COOL.
*)

(*
    Exercițiul 1.

    Implementați funcția fibonacci, utilizând atât varianta recursivă,
    cât și cea iterativă.
*)
class Fibo {
    fibo_rec(n : Int) : Int {
        if (n <= 1) then n else fibo_rec(n - 1) + fibo_rec(n - 2) fi
    };

    fibo_iter(n : Int) : Int {
        let a : Int <- 0,
            b : Int <- 1
        in
            {
                while (not (n = 0)) loop
                    let temp : Int <- a
                    in
                        {
                            a <- b;
                            b <- temp + b;
                            n <- n - 1;
                        }
                pool;
                a;
            }
    };
};
    
(*
    Exercițiul 2.

    Pornind de la ierarhia de clase implementată la curs, aferentă listelor
    (găsiți clasele List și Cons mai jos), implementați următoarele funcții
    și testați-le. Este necesară definirea lor în clasa List și supradefinirea
    în clasa Cons.

    * append: întoarce o nouă listă rezultată prin concatenarea listei curente
        (self) cu lista dată ca parametru;
    * reverse: întoarce o nouă listă cu elementele în ordine inversă.

    Observați cerințele de întoarcere a unei *noi* liste. Operațiile
    se realizează nedistructiv, fără a altera listele existente. Amintiți-vă
    cum ați scrie axiomele pentru append și reverse într-un limbaj funcțional.
*)

(*
    Listă omogenă cu elemente de tip Int. Clasa List constituie rădăcina
    ierarhiei de clase reprezentând liste. O variabilă cu tipul static List
    poate desemna atât liste vide, cât și nevide. O instanță cu tipul dinamic
    List desemnează o listă vidă. O instanță cu tipul dinamic Cons denotă
    o listă nevidă.

    Adaptare după arhiva oficială de exemple a limbajului COOL.
*)
class List inherits IO {
    isEmpty() : Bool { true };

    -- 0, deși cod mort, este necesar pentru verificarea tipurilor
    hd() : Int { { abort(); 0; } };

    -- Similar pentru self
    tl() : List { { abort(); self; } };

    cons(h : Int) : Cons {
        new Cons.init(h, self)
    };

    print() : IO { out_string("\n") };

    append(list : List) : List { list };

    reverse() : List { self };

    map(m : Map) : List { self };

    filter(f : Filter) : List { self };
};

(*
    În privința vizibilității, atributele sunt implicit protejate, iar metodele,
    publice.

    Atributele și metodele utilizează spații de nume diferite, motiv pentru care
    hd și tl reprezintă nume atât de atribute, cât și de metode.
*)
class Cons inherits List {
    hd : Int;
    tl : List;

    init(h : Int, t : List) : Cons {
        {
            hd <- h;
            tl <- t;
            self;
        }
    };

    -- Supradefinirea funcțiilor din clasa List
    isEmpty() : Bool { false };

    hd() : Int { hd };

    tl() : List { tl };

    print() : IO {
        {
            out_int(hd);
            out_string(" ");
            -- Mecanismul de dynamic dispatch asigură alegerea implementării
            -- corecte a metodei print.
            tl.print();
        }
    };

    append(list : List) : List {
        tl.append(list).cons(hd)
    };

    reverse() : List {
        tl.reverse().append(new List.cons(hd))
    };

    map(m : Map) : List {
        tl.map(m).cons(m.apply(hd))
    };

    filter(f : Filter) : List {
        let ftl : List <- tl.filter(f)
        in if f.apply(hd) then ftl.cons(hd) else ftl fi
    };
};

(*
    Exercițiul 3.

    Scopul este implementarea unor mecanisme similare funcționalelor
    map și filter din limbajele funcționale. map aplică o funcție pe fiecare
    element, iar filter reține doar elementele care satisfac o anumită condiție.
    Ambele întorc o nouă listă.

    Definiți clasele schelet Map, respectiv Filter, care vor include unica
    metodă apply, având tipul potrivit în fiecare clasă, și implementare
    de formă.

    Pentru a defini o funcție utilă, care adună 1 la fiecare element al listei,
    definiți o subclasă a lui Map, cu implementarea corectă a metodei apply.

    În final, definiți în cadrul ierarhiei List-Cons o metodă map, care primește
    un parametru de tipul Map.

    Definiți o subclasă a subclasei lui Map de mai sus, care, pe lângă
    funcționalitatea existentă, de incrementare cu 1 a fiecărui element,
    contorizează intern și numărul de elemente prelucrate. Utilizați static
    dispatch pentru apelarea metodei de incrementare, deja definită.

    Repetați pentru clasa Filter, cu o implementare la alegere a metodei apply.
*)
class Map {
    apply(n : Int) : Int { { abort(); 0; } };
};

class IncMap inherits Map {
    apply(n : Int) : Int { n + 1 };
};

class CountIncMap inherits IncMap {
    count : Int <- 0;

    apply(n : Int) : Int {
        {
            count <- count + 1;
            self@IncMap.apply(n);  -- static dispatch
        }
    };

    count() : Int { count };
};

class Filter {
    apply(n : Int) : Bool { { abort(); false; } };
};

class ExactFilter inherits Filter {
    apply(n : Int) : Bool { n <= 2 };
};

class Main inherits IO {
    main() : Object {
        let list : List <- new List.cons(1).cons(2).cons(3),
            temp : List <- list
        in
            {
                -- Afișare utilizând o buclă while. Mecanismul de dynamic
                -- dispatch asigură alegerea implementării corecte a metodei
                -- isEmpty, din clasele List, respectiv Cons.
                while (not temp.isEmpty()) loop
                    {
                        out_int(temp.hd());
                        out_string(" ");
                        temp <- temp.tl();
                    }
                pool;

                out_string("\n");

                -- Afișare utilizând metoda din clasele pe liste.
                list.print();

                list.append(list).print();
                list.reverse().print();
                list.map(new IncMap).print();

                let cim : CountIncMap <- new CountIncMap
                in
                    {
                        list.map(cim).print();
                        out_int(cim.count());
                    };

                out_string("\n");

                list.filter(new ExactFilter).print();
            }
    };
};
