Secret Sanata Generator
======================

Java program to take a csv file and create a secret santa list.

Uses objects and adheres to some rules.

Break Down:
======================

FILES

- Main.class

- Person.class

- Demo.csv


SYNOPSIS

This program needs a comma-delimited CSV to the same structure as the demo provided. It then splits this into an arraylist of Person objects. The program goes though each secret santa (giver) sequentially and randomly picks the name of a giver.


RULES

Roughly: x amount of presents per person, depending on age group and not withing the household. 


INPUT

Only the CSV at this stage. Next step of this program is to prompt the user for rules.


OUTPUT

Just on the screen currently, ready to copy paste to excel and tweak from there. 

TO DO

Optimise - runs quickly but code was written as fast as possible. 
Better handling or user experience for when the number of people supplied doesnt work well with the number of presents rules
Means for flexibility, such as changing number of presents, etc.


BUGS

Output needs more testing and randomisation when referencing sections that are already taken. 


AUTHOR

Jesse Wheeler <admin@planned-escape.com>


