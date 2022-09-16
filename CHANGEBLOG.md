# Hyperskill_Kotlin_Sorting Tool

### Stage 5/6: Error!

Huh, alright. I was wondering when I would be asked to actually evaluate arguments and check for errors. I was doing it a bit lazily before.
Admittedly it still feels like I'm only doing the bare minimum now.
If an argument is passed with multiple values like `-sortType invalid byCount natural` it only evaluates the first value `invalid` which will as a result set the sorting type to the default of `NATURAL`
Could theoretically iterate over the passed values and assign the first valid value passed. Like:

Check that `invalid` is a valid value for `-sortType`. If not, check that there are other values passed for the argument. check for the next one (`byCount` in this case) and assign accordingly.
Then, if the list is exhausted with no valid arguments, say something like "Hey guy, you passed like 6 values for -sortType and none of them are valid, what the heck?"
Oh yeah, my earlier code for checking if an argument was at the end of the List was wrong, haha, was using `.size` instead of `.lastIndex`. Oops!

That seems unnecessary though. Since as it functions right now users SHOULDN'T be passing multiple values per parameter.

I wanted to say "I finally sorted out the abstraction so `determineData()` and `determineSort()` are the same function now!" 
But all I really did was move the abstraction into the companion classes for their respective class.

Oh yeah, figured out what companion classes are. Neat, so it's like static. 
Theoretically I could have not used companion classes and just used a function inside the enum without. 
But then when I call it I'd have to call it on an instance like `DataType.WORD.byArgVal(argVal)`.
Actually now that I think of it I could just call it on the instance of `dataType` inside the `Sorter` object... 
Yeahhhhhh, but then without the companion object every instance of `DataType` gets their own copy of the function and I don't want/need that.
I'm actually not sure about the low-level goings-on of it all. Maybe one really is better than the other.

I'll finish by saying. My names could use some work. Having a `DataType` enum and an instance of it, `dataType` might be confusing to someone looking at it for the first time.
But I'm worried about becoming too verbose and making it hard to read because of said verbosity. 


### Stage 4/6: Everything Counts

Alright I'm not feeling nearly as ill today. Though I have found myself distracted with other hobbies, printing, mostly.
I had to rewrite a fair bit of code for this, but as a result everything looks a lot nicer. I feel like I could make the program run in fewer lines of code but would be less easy to read.
That said I KNOW there's a better way to do some of the things I'm trying. 

Enums in particular I feel only faintly familiar with. I'd really like to learn what a companion object is, I see IDEA recommend I use one frequently.
I'm sure it's possible to make both `DataType` and `SortType` children of the same extensible enum class, then turn `determineData()` and `determineSort()` could be a method of the parent class called `determine()` or something. 
Eliminates some redundancy while keeping things easy to read.

Ah well, maybe that's something to do in between stages, or after everything has been done, as a treat. For now, that's all.

Bye-bye!

### Stage 3/6: Sorting it out

Oh man, I am not feeling it today.
I had my COVID-19 booster shot the other day and my head's been in a bit of a fog today as a result.
This was easy enough. I moved around some things for the logic, and started to use enums instead of just strings to handle arguments.

### Stage 2/6: Not just numbers

This wasn't too bad. The hardest challenge for me this Stage was not being at my usual desk without my normal setup.
Being lazy and recording everything as a List<String> instead of a List<Number> worked out, since now we're working with strings.
I feel as though there's a more optimal way to handle args than what I'm doing now. Since right now:
- There's no checks for invalid args provided
- There's no support if someone gives an input like `-dataType word line long`, it'll just read `-dataType word` and act accordingly.

That said, while there may be better ways to handle it, I'm sure there are far worse.

Honestly I didn't even expect to work on this today it kinda happened. So, Yay I was productive by accident!

Oh, also.
I don't like the control logic I have in place right now/feel it can be broken down more or brought together more.
My "when" statements feel a bit clunky right now since they only handle 2 cases. One of those "Could be an if-else" problems.
But I really like "when" so, unless I learn that it's secretly suboptimal, I'm sticking with it.

Ciao,
Abby

### Stage 1/6: Numbers only

I decided to make the changelog for the project an actual changelog, and put my thoughts and rambling here, in the changeblog!
I think it's a fun name, though I'm sure someone will look here instead of seeing the changelog. Sorry!

Easy enough start.
"Take input until EOF" and then "print out data about the data"
I steered from the purpose of the course a bit, by not limiting myself to only reading Ints.
But considering they didn't also give me information on what to do with non-Int input, I don't think it matters.
Plus I'm going to need to read non-Int input soon anyhow, so this is... planning ahead!

A tradeoff though is that if you enter things that are not Ints to the input, they'll be captured and counted as ints for the "there are X numbers" output.

I think it'll be okay.
