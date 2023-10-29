class Comparator inherits IO {
    (* Returns <0 if o1 < o2, >0 if o1 > o2, 0 if o1 = o2. *)
    compare(o1: Object, o2: Object): Int { 0 };

    (* Generates an error for unknown object *)
    error(o: Object): Int {{
        out_string("Unknown object type found in list for ".concat(type_name()).concat(": ").concat(o.type_name()).concat("\n"));
        abort();
        0;
    }};

    (* Returns the appropriate filter for the given String type. *)
    from(type: String): Comparator {
        if      type = "PriceComparator"      then new PriceComparator
        else if type = "RankComparator"       then new RankComparator
        else if type = "AlphabeticComparator" then new AlphabeticComparator
        else {
            out_string("Unknown comparator type: ".concat(type).concat("\n"));
            abort();
            self;
        } fi fi fi
    };
};

class PriceComparator inherits Comparator {
    compare(o1: Object, o2: Object): Int {
        -- Cast objects to Products and compare them
        case o1 of p1: Product => case o2 of p2: Product => p1.getprice() - p2.getprice();
        -- Handle casting errors
        x: Object => error(x); esac; x: Object => error(x); esac
    };
};

class RankComparator inherits Comparator {
    compare(o1: Object, o2: Object): Int {
        case o1 of r1: Rank => case o2 of r2: Rank => r1.rankValue() - r2.rankValue();
        x: Object => error(x); esac; x: Object => error(x); esac
    };
};

class AlphabeticComparator inherits Comparator {
    compare(o1: Object, o2: Object): Int {
        -- This will be used to turn characters into ints
        let alphabet: Str <- (new Str).from("abcdefghijklmnopqrstuvwxyz") in
        -- Cast objects to strings
        case o1 of s1: Primitive => case o2 of s2: Primitive =>
            -- Loop over the strings character by character
            let max: Int <- if s1.v().length() < s2.v().length() then s1.v().length() else s2.v().length() fi, i: Int <- 0, result: Int <- 0 in {
                while i < max loop
                    -- Compare each character
                    let c1: Int <- alphabet.indexOf(s1.v().substr(i, 1), 0), c2: Int <- alphabet.indexOf(s2.v().substr(i, 1), 0) in
                        if      c1 < c2 then { result <- 0-1; i <- max; }
                        else if c2 < c1 then { result <-   1; i <- max; }
                        else i <- i + 1 fi fi
                pool;
                -- Fallback to comparing lengths
                if result = 0 then s1.v().length() - s2.v().length()
                else result fi;
            };
        -- Handle casting errors
        x: Object => error(x); esac; x: Object => error(x); esac
    };
};

class Filter inherits IO {
    (* Returns true if the object should be kept. *)
    filter(o : Object): Bool { true };

    (* Returns the appropriate filter for the given String type. *)
    from(type: String): Filter {
        if      type = "ProductFilter"   then new ProductFilter
        else if type = "RankFilter"      then new RankFilter
        else if type = "SamePriceFilter" then new SamePriceFilter
        else {
            out_string("Unknown filter type: ".concat(type).concat("\n"));
            abort();
            self;
        } fi fi fi
    };
};

(* Only keeps Products *)
class ProductFilter inherits Filter {
    filter(o: Object): Bool {
        case o of
            p: Product => true;
            x: Object  => false;
        esac
    };
};

(* Only keeps Ranks *)
class RankFilter inherits Filter {
    filter(o: Object): Bool {
        case o of
            r: Rank => true;
            x: Object  => false;
        esac
    };
};

(* Only keeps Products with unchanged price function *)
class SamePriceFilter inherits Filter {
    filter(o: Object): Bool {
        case o of
            p: Product => p@Product.getprice() = p.getprice();
            o: Object  => {
                out_string("Unknown object type found in list for SamePriceFilter: ".concat(o.type_name()).concat("\n"));
                abort();
                false;
            };
        esac
    };
};
