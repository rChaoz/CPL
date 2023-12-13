class A {
    f(f : Int) : Int {
        let u : Int <- u + z,
            y : Int <- y + u + x + v,
            v : Int
        in
            case y of
                z : Int => x <- x + z + f + u + y;
            esac
    };
    
    g() : A { self };

    z : Int <- x;

    x : Int <- x;
    
    f : Bool;
};