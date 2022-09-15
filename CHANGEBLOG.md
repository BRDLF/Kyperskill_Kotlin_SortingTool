# Hyperskill_Kotlin_Sorting Tool

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
