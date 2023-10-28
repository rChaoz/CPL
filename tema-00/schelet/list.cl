class List inherits Stringish {

    (* Obtains the head of this list, if present. *)
    head(): Stringish {{ abort(); self; }};

    (* Obtains the tail of this list, if present. *)
    tail(): List {{ abort(); self; }};

    (* Returns whether the list is empty. *)
    isEmpty(): Bool {{ abort(); false; }};

    (* Returns the length of the list in linear time. *)
    length(): Int {{ abort(); 0; }};

    (* Returns a new list containing all elements of this list plus an additional element. *)
    add(o: Stringish): List {
        (new Cons).init(self, o)
    };

    (* Returns the n-th element of this list in linear time. at(0) = head(). *)
    at(index: Int): Stringish {
        if index = 0 then head()
        else if index < 0 then {
            out_string("Index cannot be negative for List.at()\n");
            abort();
            self;
        } else if isEmpty() then {
            out_string("Index out of bounds\n");
            abort();
            self;
        } else tail().at(index - 1)
        fi fi fi
    };

    (* Converts this List to a string, elements printed head to tail, using the format "[ <value>, <value>, <value> ... ]". *)
    toString(): String {
        "[ ".concat(toStringNoBrackets()).concat(" ]")
    };

    (* Converts this List to string, elements printed head to tail, using the format "<value>, <value>, <value>, ...". *)
    toStringNoBrackets(): String {
        case self of
            emptyList : EmptyList   => "";
            consList  : Cons        =>
                if tail().isEmpty() then head().toString()
                else head().toString().concat(", ").concat(tail().toStringNoBrackets()) fi;
            unused    : Object      => {
                out_string("Error: Unexpected List subclass found: ".concat(self.type_name())
                    .concat("\nInheriting from List is not allowed."));
                self.abort();
                "";
            };
        esac
    };

    merge(other: List): SELF_TYPE {
        self (* TODO *)
    };

    filterBy(): SELF_TYPE {
        self (* TODO *)
    };

    sortBy(): SELF_TYPE {
        self (* TODO *)
    };
};

class Cons inherits List {
    head: Stringish;
    tail: List;

    head(): Stringish { head };
    tail(): List { tail };
    isEmpty(): Bool { false };
    length(): Int { 1 + tail.length() };

    init(tail_: List, head_: Stringish): SELF_TYPE {{
        head <- head_;
        tail <- tail_;
        self;
    }};
};

class EmptyList inherits List {
    isEmpty(): Bool { true };
    length(): Int { 0 };
};
