class Main inherits IO{
    lists : List;
    looping : Bool <- true;
    somestr : String;

    main():Object {
        while looping loop {
            out_string("Your name: ");
            somestr <- in_string();
            out_string("Hi ".concat(somestr).concat("\n"));
        } pool
    };
};