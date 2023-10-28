class Main inherits IO {
    lists : List;
    looping : Bool <- true;
    somestr : String;

    (* Entry point. *)
    main(): Object {{
        lists <- new EmptyList;
        readNewList();
    }};

    (* Read a list and add it to the list of lists. *)
    readNewList(): Object {
        lists <- lists.add(readList())
    };

    (* Recursive function used to read a list from stdin until END. *)
    readList(): List {
        let line: String <- in_string() in
            if line = "END" then new EmptyList
            else readList().add(
                -- Process object from input line. Start by splitting line by spaces and getting item type
                let items: List <- (new Str).from(line).split(" ") in let type: String <- items.head().toString() in
                    -- Then, create the object instance
                    let object: Stringish <-
                        if type = "Soda"          then new Soda
                        else if type = "Coffee"   then new Coffee
                        else if type = "Laptop"   then new Laptop
                        else if type = "Router"   then new Router
                        else if type = "Private"  then new Private
                        else if type = "Corporal" then new Corporal
                        else if type = "Sergent"  then new Sergent
                        else if type = "Officer"  then new Officer
                        else { out_string("Invalid product type: ".concat(type)); abort(); new Stringish; }
                        fi fi fi fi fi fi fi fi -- fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi
                    in
                        -- Finally, initialize the object and return it
                        case object of
                            p : Product => p.init(items.at(1).toString(), items.at(2).toString(), items.at(3).str().toInt());
                            r : Rank    => r.init(items.at(1).toString());
                        esac
            ) fi
    };

    (* Prints a single list, 1-based index. *)
    printList(index: Int): Object {
        out_string(lists.at(index - 1).toString().concat("\n"))
    };

    (* Prints all lists. Call with l=lists, index=1. *)
    printAllLists(l: List, index: Int): Object {{
        out_int(index);
        out_string(": ");
        out_string(l.head().toString());
        out_string("\n");
        if not l.tail().isEmpty() then printAllLists(l.tail(), index + 1)
        else new Object fi;
    }};
};
