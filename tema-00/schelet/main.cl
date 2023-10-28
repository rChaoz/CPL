class Main inherits IO {
    lists : List;
    looping : Bool <- true;
    somestr : String;

    (* Entry point. *)
    main(): Object {
        out_string(readList().toString())
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
};
