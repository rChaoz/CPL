class Main inherits IO {
    lists : List;
    looping : Bool <- true;
    somestr : String;

    (* Entry point. *)
    main(): Object {{
        lists <- (new List).init();
        readNewList();
        -- Console mode loop
        let go: Bool <- true in while go loop
            -- Read and parse command from stdin
            let params: List <- (new Str).from({out_string("> "); in_string();}).split(" ") in
            let command: String <- params.at(0).toString() in
            -- Test what command was received
            if command = "help" then {
                out_string("Available commands:\n");
                out_string("help\n\tShows this help info.\n");
                out_string("print [index]\n\tPrints a list.\n\tIf index is not provided, prints all lists using 1-based indices.\n");
                out_string("merge <index-1> <index-2>\n\tMerges 2 lists together, by concatenating the second over the first.\n");
                out_string("\tThe new list will be placed at the end. The old lists will be deleted.\n");
                out_string("filterBy <index> {ProductFilter,RankFilter,SamePriceFilter}\n");
                out_string("\tApply a filter to a given list, eliminating elements from the list.\n");
                out_string("sortBy <index> {PriceComparator,RankComparator,AlphabeticComparator} {ascendent,descendent}\n");
                out_string("\tSorts a given list using a comparator, ascending or descending.\n");
                out_string("\nNote: Use 0-based indices for <index> in commands.\n");
            } else if command = "load" then readNewList()
            else if command = "print" then
                -- If we have no arguments, print all lists
                if params.length() = 1 then
                    if not lists.isEmpty() then printAllLists(lists.base(), 1)
                    else 0 fi
                -- Else, print the indicated list. Str.toInt() handles conversion errors.
                else if params.length() = 2 then printList(params.at(1).str().toInt())
                -- Abort if we receive too many parameters
                else { out_string("Error: too many parameters for print\n"); abort(); } fi fi
            else if command = "merge" then
                -- Ensure we have the right amount of parameters
                if not params.length() = 3 then { out_string("Error: exactly 2 parameters required for merge\n"); abort(); }
                -- Call the merge function
                else mergeLists(params.at(1).str().toInt(), params.at(2).str().toInt()) fi
            else if command = "filterBy" then
                if not params.length() = 3 then { out_string("Error: exactly 2 parameters required for filterBy\n"); abort(); }
                else filterList(params.at(1).str().toInt(), (new Filter).from(params.at(2).toString())) fi
            else if command = "sortBy" then
                if not params.length() = 4 then { out_string("Error: exactly 3 parameters required for sortBy\n"); abort(); }
                else sortList(params.at(1).str().toInt(), (new Comparator).from(params.at(2).toString()), params.at(3).toString() = "descendent") fi
            else {
                out_string("unknown command\n");
                go <- false;
            } fi fi fi fi fi fi
        pool;
    }};

    (* Read a list and add it to the list of lists. *)
    readNewList(): Object { lists.add((new List).from(readList())) };

    (* Recursive function used to read a list from stdin until END. *)
    readList(): ListBase {
        let line: String <- in_string() in
            if line = "END" then new EmptyList
            else readList().cons(
                -- Process object from input line. Start by splitting line by spaces and getting item type
                let items: List <- (new Str).from(line).split(" ") in let type: String <- items.at(0).toString() in
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
                        else if type = "String"   then (new Str).from(line).substr(7, line.length() - 7)
                        else { out_string("Invalid object type: ".concat(type).concat("\n")); abort(); new Stringish; }
                        fi fi fi fi fi fi fi fi fi -- fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi fi ...
                    in
                        -- Finally, initialize the object (if needed) and return it
                        case object of
                            p : Product => p.init(items.at(1).toString(), items.at(2).toString(), items.at(3).str().toInt());
                            r : Rank    => r.init(items.at(1).toString());
                            s : Str     => s;
                        esac
            ) fi
    };

    (* Prints a single list, 1-based index. *)
    printList(index: Int): Object {
        out_string(lists.at(index).toString().concat("\n"))
    };

    (* Prints all lists. Call with l=lists.base(), index=1. *)
    printAllLists(l: ListBase, index: Int): Object {{
        out_int(index);
        out_string(": ");
        out_string(l.head().toString());
        out_string("\n");
        if not l.tail().isEmpty() then printAllLists(l.tail(), index + 1)
        else new Object fi;
    }};

    (* Merge 2 lists at given indices. *)
    mergeLists(i1: Int, i2: Int): Object {
        -- Obtain the 2 sublists
        case lists.at(i1) of l1: List => case lists.at(i2) of l2: List => {
            -- Remove them from main list
            if i1 < i2 then { lists.remove(i2); lists.remove(i1); }
            else { lists.remove(i1); lists.remove(i2); } fi;
            -- Concat second list to first
            l1.concat(l2);
            -- Add the merged list to the end of the list of lists
            lists.add(l1);
        }; esac; esac
    };

    (* Remove all elements that do not match a filter from a list. *)
    filterList(index: Int, filter: Filter): Object {
        case lists.at(index) of l: List => l.filterBy(filter); esac
    };

    (* Sort a list using a given comparator. *)
    sortList(index: Int, comparator: Comparator, reverse: Bool): Object {
        case lists.at(index) of l: List => l.sortBy(comparator, reverse); esac
    };
};
