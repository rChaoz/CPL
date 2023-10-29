class Comparator inherits IO {
    (* Returns -1 if o1 < o2, 1 if o1 > o2, 0 if o1 = o2. *)
    compareTo(o1: Object, o2: Object): Int { 0 };
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
