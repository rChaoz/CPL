(* List of elements with advanced functions. *)
class List inherits Stringish {
    list: ListBase;

    (* Initializer for List. *)
    init(): SELF_TYPE {{ list <- (new EmptyList).init(self); self; }};

    (* Initializes this list from an existing base. *)
    from(base: ListBase): SELF_TYPE {{ list <- base; list.setOwner(self); self; }};

    (* Returns whether the list is empty. *)
    isEmpty(): Bool { list.isEmpty() };

    (* Returns the length of the list in linear time. *)
    length(): Int { list.length() };

    (* Adds a new element to the front (head) of this list. *)
    push(elem: Stringish): Object { list <- list.cons(elem) };

    (* Removes and returns the front element (head) of this list. *)
    pop(): Stringish {{
        currentIndex <- 0;
        operation <- "pop()";
        let head: Stringish <- list.head() in {
            list <- list.tail();
            head;
        };
    }};

    (* Adds a new element to the back (tail) of this list. *)
    add(elem: Stringish): Object {
        if list.isEmpty() then list <- list.cons(elem)
        else list.add(elem) fi
    };

    (* Returns the n-th element of this list in linear time. at(0) = head(). *)
    at(index: Int): Stringish {{
        currentIndex <- index;
        operation <- "at()";
        if index < 0 then negativeIndex(index)
        else list.at(index) fi;
    }};

    (* Removes the n-th element of this list in linear time. *)
    remove(index: Int): SELF_TYPE {{
        currentIndex <- index;
        operation <- "remove()";
        if index < 0 then negativeIndex(index)
        else if index = 0 then list <- list.tail()
        else list.remove(index) fi fi;
        self;
    }};

    (* Concatenates another List to the end of this List.
    The parameter list must *not* be modified anymore, or else, changes to that list will be reflected in this list.*)
    concat(other: List): SELF_TYPE {{
        if list.isEmpty() then list <- other.base()
        else list.concat(other.base()) fi;
        self;
    }};

    (* Removes all elements from this list for which the filter predicate returns false *)
    filterBy(f: Filter): SELF_TYPE {{
        -- Remove elements from the head first
        while if list.isEmpty() then false else not f.filter(list.head()) fi loop list <- list.tail() pool;
        -- Then remove from the middle as well
        list.filterBy(f);
        self;
    }};

    (* Bubble sorts this list using the given comparator, optionally reversed. *)
    sortBy(c: Comparator, reverse: Bool): SELF_TYPE {{
        let n: Int <- length(), mul: Int <- if reverse then 0-1 else 1 fi in
        while 0 < n loop {
            let item: ListBase <- list, temp: Stringish in
            -- while item and item.tail values exist
            while if item.isEmpty() then false else not item.tail().isEmpty() fi loop {
                if 0 < c.compare(item.head(), item.tail().head()) * mul then
                    -- swap the 2 items
                    case item of cons1: Cons => case item.tail() of cons2: Cons => {
                        temp <- cons1.head();
                        cons1.init(self, cons1.tail(), cons2.head());
                        cons2.init(self, cons2.tail(), temp);
                    } ; esac; esac
                else 0 fi;
                item <- item.tail();
            } pool;
            n <- n - 1;
        } pool;
        self;
    }};

    (* Converts this List to a string, elements printed head to tail, using the format "[ <value>, <value>, <value> ... ]". *)
    toString(): String { "[ ".concat(list.toString()).concat(" ]") };

    (* Returns the inner ListBase. *)
    base(): ListBase { list };


    (* === Private === *)
    currentIndex: Int;
    operation: String;

    (* Displays an index out of bounds error and aborts.*)
    indexOutOfBounds(): Stringish {{
        out_string("Index out of bounds: ");
        out_int(currentIndex);
        out_string(" for ");
        out_string(operation);
        out_string(", list size: ");
        out_int(length());
        out_string(".\n");
        abort();
        self;
    }};

    (* Displays a negative index error and aborts.*)
    negativeIndex(index: Int): Stringish {{
        out_string("Negative index not allowed: ");
        out_int(index);
        out_string("\n");
        abort();
        self;
    }};
};

(* Basic list of items. *)
class ListBase inherits Stringish {
    owner: List;
    -- "Abstract" methods implemented by Cons, EmptyList
    head(): Stringish {{ abort(); self; }};
    tail(): ListBase {{ abort(); self; }};
    isEmpty(): Bool {{ abort(); false; }};
    length(): Int {{ abort(); 0; }};

    add(elem: Stringish): Object { abort() };
    at(index: Int): Stringish {{ abort(); self; }};
    remove(index: Int): Object { abort() };
    concat(other: ListBase): Object { abort() };

    filterBy(f: Filter): Object { abort() };

    (* Returns a new ListBase with the given head and this list as the tail. *)
    cons(elem: Stringish): ListBase { (new Cons).init(owner, self, elem) };

    sortBy(): SELF_TYPE {
        self (* TODO *)
    };

    (* === Private === *)
    setOwner(owner_: List): Object {{
        owner = owner_;
        if not isEmpty() then tail().setOwner(owner) else 0 fi;
    }};
};

class Cons inherits ListBase {
    head: Stringish;
    tail: ListBase;

    init(owner_: List, tail_: ListBase, head_: Stringish): SELF_TYPE {{
        owner <- owner_;
        head <- head_;
        tail <- tail_;
        self;
    }};

    head(): Stringish { head };
    tail(): ListBase { tail };
    isEmpty(): Bool { false };
    length(): Int { 1 + tail.length() };

    add(elem: Stringish): Object {
        if tail.isEmpty() then tail <- tail.cons(elem)
        else tail.add(elem) fi
    };

    at(index: Int): Stringish {
        if index = 0 then head
        else tail.at(index - 1) fi
    };
    
    remove(index: Int): Object {
        if index = 1 then tail <- tail.tail()
        else tail.remove(index - 1) fi
    };

    concat(other: ListBase): Object {
        if tail().isEmpty() then tail <- other
        else tail().concat(other) fi
    };

    filterBy(f: Filter): Object {{
        while if tail.isEmpty() then false else not f.filter(tail.head()) fi loop tail <- tail.tail() pool;
        tail.filterBy(f);
    }};

    toString(): String {
        if tail.isEmpty() then head.toString()
        else head.toString().concat(", ").concat(tail.toString()) fi
    };
};

class EmptyList inherits ListBase {
    init(owner_: List): SELF_TYPE {{ owner <- owner_; self; }};
    head(): Stringish { owner.indexOutOfBounds() };
    tail(): ListBase {{ owner.indexOutOfBounds(); self; }};
    isEmpty(): Bool { true };
    length(): Int { 0 };
    
    at(index: Int): Stringish { owner.indexOutOfBounds() };
    remove(index: Int): Object { owner.indexOutOfBounds() };

    filterBy(f: Filter): Object { 0 };

    toString(): String { "" };
};
